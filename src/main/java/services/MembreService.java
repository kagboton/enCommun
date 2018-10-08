package services;

import beans.Membre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class MembreService implements IMembreService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Membre findMemberByLogin(String login) {
        return em.find(Membre.class, login);
    }

    @Transactional
    @Override
    public void createMember(Membre m) {
        em.persist(m);
    }
}
