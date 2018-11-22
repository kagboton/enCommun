package services;

import beans.Competence;
import beans.CompetenceMembre;
import beans.Membre;
import beans.Projet;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.*;
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
        Membre m = this.chercherMembre(login, motDePasse);
        if(m!=null)
            membresConnectes.add(m);
        return m;
    }

    @Override
    public Membre findMemberByLogin(String login) {
        return em.find(Membre.class, login);
    }


    @Override
    public Membre chercherMembre(String login, String mdp){
        /*
            1. Soit on utilise un entityGraph pour recupérer toutes les collections attachées à l'entité membre (ce qui est fait ici).  Dans le class Membre, on n'effectue aucune règle
            2. Soit on utilise un fetch eager associé à un fetchmode subselect dans la classe Membre sur les attributs à récupérer en même temps que le membre. L'entity manager sait alors qu'il doit tout récupérer et aller chercher les éléments quand on le lui demande. On a plus besoin d'utiliser entityGraph.
            3. Soit on effectue la requete en commentaire (join fetch) sans l'entityGraph et sans le fetch eager dans Membre
            4. Soit on fait une requete normal mais on applique uen opération sur les élément associé à Memebre qu'on veut récupérer
        */

        /*EntityGraph<Membre> entityGraph = em.createEntityGraph(Membre.class);
        entityGraph.addSubgraph("projetsParticites");
        entityGraph.addSubgraph("projetsDiriges");*/

        Query q = em.createQuery("select m from Membre m where m.login=:login and  m.motDePasse=:mdp");
        // "join fecth m.projetsParticites  join fetch m.projetsDiriges"
        q.setParameter("login", login);
        q.setParameter("mdp", mdp);
        //q.setHint("javax.persistence.loadgraph", entityGraph);

        try{
            Membre m = (Membre) q.getSingleResult();
            //m.getProjetsDiriges().size(); //opération qu'on peut faire pour attacher l'em. (methode 4)
            //m.getProjetsParticites().size();
            return m;
        }catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
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



    //TODO Ajouter des competences au projet créé.... pareil que l'ajout mais en fait met à jour juste les competences

    //TODO Supprimer un projet
}
