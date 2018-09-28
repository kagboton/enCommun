package modele.facade;

import modele.beans.Competence;
import modele.beans.Membre;
import modele.beans.Projet;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class Facade implements IFacade {


    private List<Membre> membresInscrits;
    private List<Membre> membresConnectes;


    //Quelques membres
        Membre membre1 = new  Membre("toto", "123", "toto");
        Membre membre2 = new  Membre("tata", "123", "tata");
        Membre membre3 = new Membre("tutu", "123", "tutu");

    //Quelques competences
        Competence competence1 = new Competence("java", "Java developpeur");
        Competence competence2 =new Competence("css", "web designer");
        Competence competence3 = new Competence("photoshop", "graphiste");
        Competence competence4 = new Competence("html", "web developpeur");
        Competence competence5 = new Competence("python", "graphiste");

    //Quelques projets
        Projet projet1 = new Projet("Creation du jeu takin", "Projet de creation du jeu takin");
        Projet projet2 = new Projet("Web browser", "Projet de developpement d'un moteur de recherche");
        Projet projet3 = new Projet("Apollon 3", "Allons tous sur la lune");


    /**
     * Constructeur par défaut
    public Facade() {
        this.initialisation(); // Pas besoin si on met le @PostConstruct
    }*/

    @Override
    public boolean inscription(String login, String mdp, String surnom) {
        //Verifions si le login n'est pas déja pris
        for(Membre m : membresInscrits){
            if(m.getLogin().equals(login)){
                return false;
            }
        }
        //Si je suis ici c'est que le login n'est pas déjà pris
        membresInscrits.add(new Membre(login, mdp, surnom));
        //Je connecte directement le membre
        membresConnectes.add(new Membre(login, mdp, surnom));

        return true;
    }

    @Override
    public boolean connexion(String login, String mdp) {
        //Verifions que le couple login et mot de passe existe
        for(Membre m: membresInscrits){
            if(m.getLogin().equals(login) && m.getMotDePasse().equals(mdp)){
                membresConnectes.add(m);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deconnexion(String login) {
        return false;
    }


    @Override
    public Membre findMemberByLogin(String login) {
        for(Membre m : membresConnectes){
            if(login.equals(m.getLogin())){
                return m;
            }
        }
        return null;
    }

    /**
     * Methode d'initialisation des listes avec les objets créés
     */
    @PostConstruct
    private void initialisation(){
        //Initialisation
        membresInscrits = new ArrayList<>();
        membresConnectes = new ArrayList<>();

        //Ajout des membres inscrits
        membresInscrits.add(membre1);
        membresInscrits.add(membre2);
        membresInscrits.add(membre3);

    }
}
