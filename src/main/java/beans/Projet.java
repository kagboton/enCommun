package beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Membre> membres; //un projet reçoit la contribution de plusieurs membres

    @ManyToOne
    @JoinColumn(name = "MEMBRE_ID")
    private Membre responsable; //un projet est dirigé par un seul responsable

    @ManyToMany
    @JoinTable(name = "COMPETENCE_PROJET", joinColumns = {@JoinColumn(name = "PROJET_ID")},
            inverseJoinColumns = {@JoinColumn(name = "COMPETENCE_ID")})
    private List<Competence> competences; //un projet necessite une ou plusieurs competences

    public Projet(String intituleP, String descriptionP) {
        this.intituleP = intituleP;
        this.descriptionP = descriptionP;
        membres = new ArrayList<Membre>();
        competences = new ArrayList<>();
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


    public List<Membre> listerMembres() {
        return membres;
    }

    public void ajouterMembre(Membre membre) {
        this.membres.add(membre);
    }

    public Membre getResponsable() {
        return responsable;
    }

    public void changerResponsable(Membre responsable) {
        this.responsable = responsable;
        this.responsable.dirigerProjet(this);
    }

    public void ajouterUnMembre(Membre participant){
        participant.participerProjet(this);
        this.membres.add(participant);

    }

    public List<Competence> listerCompetencesProjet() {
        return competences;
    }

    public void ajouterCompetenceProjet(Competence competence) {
        competence.associerProjet(this);
        this.competences.add(competence);
    }

}
