package beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PROJET")
    private int id;

    @Column(name = "INTITULE_PROJET")
    private String intituleP;

    @Column(name = "DESCRIPTION_PROJET")
    private String descriptionP;

    @ManyToMany
    @JoinTable(name = "MEMBRE_PROJET", joinColumns = {@JoinColumn(name = "PROJET_ID")},
            inverseJoinColumns = {@JoinColumn(name = "MEMBRE_ID")})
    private Collection<Membre> membres; //un projet reçoit la contribution de plusieurs membres

    @ManyToOne
    @JoinColumn(name = "RESPONSABLE_ID")
    private Membre responsable; //un projet est dirigé par un seul responsable

    @ManyToMany
    @JoinTable(name = "COMPETENCE_PROJET", joinColumns = {@JoinColumn(name = "PROJET_ID")},
            inverseJoinColumns = {@JoinColumn(name = "COMPETENCE_ID")})
    private Collection<Competence> competences; //un projet necessite une ou plusieurs competences

    /**
     * Constructeur par défaut
     */
    public Projet() {
    }

    /**
     * Constructeur avec les parametres
     * @param intituleP
     * @param descriptionP
     */
    public Projet(String intituleP, String descriptionP) {
        this.intituleP = intituleP;
        this.descriptionP = descriptionP;
        membres = new ArrayList<Membre>();
        competences = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntituleP() {
        return intituleP;
    }

    public void setIntituleP(String intituleP) {
        this.intituleP = intituleP;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }


    public Collection<Membre> listerMembres() {
        return membres;
    }

    public Membre getResponsable() {
        return responsable;
    }

    public void changerResponsable(Membre responsable) {
        this.responsable = responsable;
        this.responsable.dirigerProjet(this);
    }

    public void ajouterMembre(Membre participant){
        participant.participerProjet(this);
        this.membres.add(participant);
    }

    public Collection<Competence> listerCompetencesProjet() {
        return competences;
    }

    public void ajouterCompetenceProjet(Competence competence) {
        competence.associerProjet(this);
        this.competences.add(competence);
    }

}
