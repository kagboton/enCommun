package services;

import beans.Membre;

public interface IFacade {

    /**
     * Inscription d'un nouveau membre
     * @param login
     * @param mdp
     * @param surnom
     * @return un nouveau membre si l'inscription s'est bien passé et null si le login est déjà pris
     */
    public Membre inscription(String login, String mdp, String surnom);

    /**
     * Connexion d'un membre existant
     * @param login
     * @param mdp
     * @return le membre si les identifiants sont correctes et null sinon
     */
    public Membre connexion(String login, String mdp);

    /**
     * Déconnexion d'un membre connecté
     * @param login
     * @return
     */
    public boolean deconnexion(String login);

    /**
     * Retrouver un membre à partir de son login
     * @param login
     * @return l'objet Membre correspondant ou null si le login n'existe pas
     */
    public Membre findMemberByLogin(String login);

    /**
     * Savoir si un utilisateur est connecté ou pas
     * @param login
     * @return true si le membre est connecté, false sinon
     */
    public boolean estConnecte(String login);

}