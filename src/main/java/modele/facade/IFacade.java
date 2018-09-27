package modele.facade;

import modele.beans.Membre;

public interface IFacade {

    /**
     * Inscription d'un nouveau membre
     * @param login
     * @param mdp
     * @param surnom
     * @return true si l'inscription s'est bien passé et false si le login est déjà pris
     */
    public boolean inscription(String login, String mdp, String surnom);

    /**
     * Connexion d'un membre existant
     * @param login
     * @param mdp
     * @return true si les identifiants sont correctes et false sinon
     */
    public boolean connexion(String login, String mdp);

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

}
