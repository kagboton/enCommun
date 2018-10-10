package beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Competence {

    @Id
    @Column(name = "INTITULE_COMPETENCE")
    private String intituleC;

    @Column(name = "DESCRIPTION_COMPETENCE", nullable = false)
    private String descriptionC;

    @ManyToMany(mappedBy = "competences")
    private List<Projet> projets;

    @OneToMany(mappedBy = "competence")
    private List<CompetenceMembre> competenceMembres;

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

    public List<Projet> projetsAssocies() {
        return projets;
    }

    public void associerProjet(Projet projet) {
        this.projets.add(projet);
    }

    public List<CompetenceMembre> getCompetenceMembres() {
        return competenceMembres;
    }

    public void setCompetenceMembres(List<CompetenceMembre> competenceMembres) {
        this.competenceMembres = competenceMembres;
    }
}
