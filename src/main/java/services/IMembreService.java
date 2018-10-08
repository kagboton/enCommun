package services;

import beans.Membre;

public interface IMembreService{

    /**
     * Retrouver dans la base un membre qui tente de se connecter
     * @param login
     * @return un objet Membre si le login existe, null sinon
     */
    public Membre findMemberByLogin(String login);

    /**
     * Inscrire un nouveau membre dans la base de données
     * @param m
     */
    public void createMember(Membre m);
}
