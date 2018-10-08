package services;

import beans.Membre;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class Facade implements IFacade {

    @PersistenceContext
    private EntityManager em;


    private List<Membre> membresInscrits;
    private List<Membre> membresConnectes;

    /**
     * Methode d'initialisation des listes avec les objets créés
     */
    @PostConstruct
    private void initialisation(){
        //Initialisation
        membresInscrits = new ArrayList<>();
        membresConnectes = new ArrayList<>();

        //Recupération des membres inscrits depuis la bdd
        Query q = em.createQuery("select m from Membre m");
        membresInscrits = q.getResultList();

    }

    @Override
    public Membre connexion(String login, String motDePasse) {
        for(Membre m: membresInscrits){
            if(m.getLogin().equals(login) && m.getMotDePasse().equals(motDePasse)){
                membresConnectes.add(m);
                return m;            }
        }
        return null;
    }

    @Override
    public Membre findMemberByLogin(String login) {
        return em.find(Membre.class, login);
    }

    @Transactional
    @Override
    public Membre inscription(String login, String mdp, String surnom) {
        //Verifions si le login n'est pas déja pris
        for(Membre m : membresInscrits){
            if(m.getLogin().equals(login)){
                return null;
            }
        }

        //Si je suis ici c'est que le login n'est pas déjà pris
        Membre m = new Membre(login, mdp, surnom);
        membresInscrits.add(m);
        em.persist(m);
        membresConnectes.add(m); //connexion direct du membre

        return m;
    }

    @Override
    public boolean deconnexion(String login) {
        if(estConnecte(login)){
            Membre m = findMemberByLogin(login); //Récuperer le membre
            membresConnectes.remove(m); //Le remove de la liste membresConncetes
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean estConnecte(String login) {
        for(Membre m : membresConnectes){
            if(login.equals(m.getLogin())){
                return true;
            }
        }
        return false;
    }
}
