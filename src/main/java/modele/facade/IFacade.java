package modele.facade;

public interface IFacade {

    /**
     * Inscription d'un nouveau membre
     * @param login
     * @param mdp
     * @param surnom
     * @return
     */
    public boolean inscription(String login, String mdp, String surnom);

    /**
     * Connexion d'un membre existant
     * @param login
     * @param mdp
     * @return
     */
    public boolean connexion(String login, String mdp);

    /**
     * Déconnexion d'un membre connecté
     * @param login
     * @return
     */
    public boolean deconnexion(String login);

}
