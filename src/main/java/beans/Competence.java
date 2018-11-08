package beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
public class Competence {

    @Id
    @Column(name = "INTITULE_COMPETENCE")
    private String intituleC;

    @Column(name = "DESCRIPTION_COMPETENCE", nullable = false)
    private String descriptionC;

    @ManyToMany(mappedBy = "competences")
    private Collection<Projet> projets;

    @OneToMany(mappedBy = "competence")
    private Collection<CompetenceMembre> competenceMembres;

    public Competence() {
    }

    public Competence(String intituleC, String descriptionC) {
        this.intituleC = intituleC;
        this.descriptionC = descriptionC;
        projets = new ArrayList<>();
        competenceMembres = new ArrayList<>();
    }


    public String getIntituleC() {
        return intituleC;
    }

    public void setIntituleC(String intituleC) {
        this.intituleC = intituleC;
    }

    public String getDescriptionC() {
        return descriptionC;
    }

    public void setDescriptionC(String descriptionC) {
        this.descriptionC = descriptionC;
    }

    public Collection<Projet> projetsAssocies() {
        return projets;
    }

    public void associerProjet(Projet projet) {
        this.projets.add(projet);
    }

    public Collection<CompetenceMembre> getCompetenceMembres() {
        return competenceMembres;
    }

    public void setCompetenceMembres(Collection<CompetenceMembre> competenceMembres) {
        this.competenceMembres = competenceMembres;
    }
}
