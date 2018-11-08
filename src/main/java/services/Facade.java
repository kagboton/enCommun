package services;

import beans.Competence;
import beans.CompetenceMembre;
import beans.Membre;
import beans.Projet;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class Facade implements IFacade {

    @PersistenceContext
    private EntityManager em;

    Query q;


    private Collection<Membre> membresInscrits;
    private Collection<Membre> membresConnectes;
    private Collection<Competence> competences;
    private Collection<Projet> allProjects;
    private Map<String, Collection<Projet>> projetsMembre;
    private Map<String, Collection<CompetenceMembre>> competencesMembre;
    private Collection<CompetenceMembre> competenceMembreList;

    /**
     * Methode d'initialisation des listes avec les objets créés
     */
    @PostConstruct
    private void initialisation(){

        //Initialisation
        membresInscrits = new ArrayList<>();
        membresConnectes = new ArrayList<>();
        competences = new ArrayList<>();
        competencesMembre = new HashMap<>();
        competenceMembreList =  new ArrayList<>();
        projetsMembre = new HashMap<>();
        allProjects = new ArrayList<>();

        //Recupération des membres inscrits depuis la bdd
        q = em.createQuery("select m from Membre m");
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

    @Override
    public Collection<Competence> getAllCompetences(){
        //Recupération des compétences depuis la bdd
        q = em.createQuery("select c from Competence c");
        competences = q.getResultList();
        return competences;
    }

    @Override
    public Competence findCompetenceByIntitule(String intitule) {
        return em.find(Competence.class, intitule);
    }

    @Transactional
    @Override
    public void ajouterCompetenceMembre(int niveau, String commentaire, String login, String intituleC) {
        CompetenceMembre competenceMembre = new CompetenceMembre(niveau,commentaire);//Nouvelle competence membre
        Membre membreCourant = this.findMemberByLogin(login);//Récupération de l'instance du membre courant
        Competence competenceChoisie = this.findCompetenceByIntitule(intituleC); //Récupération de l'instance de la competence choisie

        //Mise à jour du membre et de la competence choisie
        competenceMembre.setMembre(membreCourant);
        competenceMembre.setCompetence(competenceChoisie);

        //Persistence des informations
        competenceMembreList.add(competenceMembre);
        competencesMembre.put(login, competenceMembreList);
        em.persist(competenceMembre);
    }

    public Collection<CompetenceMembre> getCompetenceMembreListByMemberLogin(String login){

        q = em.createQuery("select m.competencesMembres from Membre m where m.login like :login");
        q.setParameter("login", login);

       competenceMembreList = q.getResultList();

        return  competenceMembreList;
    }


    @Override
    @Transactional
    public boolean supprimerCompetenceMembre(int id) {
        //je recupère la competence membre dont l'id est passée en paramètre
        CompetenceMembre competenceMembre = em.find(CompetenceMembre.class, id);

        if(competenceMembre != null){
            em.remove(competenceMembre);
            return true;
        }else {
            return false;
        }
    }


    @Override
    @Transactional
    public void ajouterProjet(String intitule, String description, String login) {
        Projet projet = new Projet(intitule, description); //Nouveau projet
        Membre membreCourant = this.findMemberByLogin(login);//Récupération de l'instance du membre courant

        //Mise à jour du responsable du projet
        projet.changerResponsable(membreCourant);
        projet.ajouterMembre(membreCourant);

        //Persistence des informations
        allProjects.add(projet);
        projetsMembre.put(login, allProjects);
        em.persist(projet);

    }

    @Override
    public Collection<Projet> getMemberProjectsListByMemberLogin(String login) {
        q = em.createQuery("select p from Projet p where p.responsable.login like :login");
        q.setParameter("login", login);

        allProjects = q.getResultList();

        return  allProjects;
    }



    //TODO Ajouter des competences au projet créé.... pareil que l'ajout mais en f  ait met à jour juste les competences

    //TODO Supprimer un projet
}
