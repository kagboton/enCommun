package services;

import beans.Competence;
import beans.CompetenceMembre;
import beans.Membre;

import java.util.List;

public interface IFacade {

    /**
     * Retrouver dans la base un membre à partir de son login
     * @param login
     * @return un objet Membre si le login existe, null sinon
     */
    public Membre findMemberByLogin(String login);


    /**
     * Connexion d'un membre existant dans la base de données
     * @param login
     * @param motDePasse
     * @return un objet Membre si le membre existe(les identifiants sont correctes), null sinon
     */
    public Membre connexion(String login, String motDePasse);

    /**
     * Inscription d'un nouveau membre
     * @param login
     * @param mdp
     * @param surnom
     * @return un objet Membre si l'inscription s'est bien passée et null si le login est déjà pris
     */
    public Membre inscription(String login, String mdp, String surnom);


    /**
     * Déconnexion d'un membre connecté
     * @param login
     * @return
     */
    public boolean deconnexion(String login);

    /**
     * Savoir si un utilisateur est connecté ou pas
     * @param login
     * @return true si le membre est connecté, false sinon
     */
    public boolean estConnecte(String login);

    /**
     *
     * @return toutes les competences de la bdd
     */
    public List<Competence> getAllCompetences();


    /**
     * Méthode pour ajouter une competence à un membre
     * @param niveau
     * @param commentaire
     * @param login,
     * @param intituleC
     */
    public void ajouterCompetenceMembre(int niveau, String commentaire, String login, String intituleC);


    public Competence findCompetenceByIntitule(String intitule);

    public List<CompetenceMembre> getCompetenceMembreListByMemberLogin(String login);

    /**
     * Méthode pour supprimer une comptence membre du membre courant
     * @param id
     */
    public boolean supprimerCompetenceMembre(int id);

}
