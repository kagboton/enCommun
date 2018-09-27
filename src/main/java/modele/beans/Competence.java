package modele.beans;

import java.util.ArrayList;
import java.util.List;

public class Competence {
    private String intituleC;
    private String descriptionC;
    private List<Projet> projets;
    private List<CompetenceMembre> competenceMembres;

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
