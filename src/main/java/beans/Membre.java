package beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Membre {

    //Attributs
    @Size(min = 4)
    @NotNull
    @NotBlank
    private String login;
    @NotNull
    @NotBlank
    private String motDePasse;

    private String surnom;

    private List<Projet> projetsParticites; //un membre peut participer à un ou plusieurs projets
    private List<Projet> projetsDiriges; //un membre peut être responsable d'un ou de plusieurs projets


    private List<CompetenceMembre> competencesMembres; //un membre possede une ou plusieurs competences

    /**
     * Constructeur par défaut
     */
    public Membre() {
    }

    /**
     * Constructeur avec des paramètres
     * @param login
     * @param motDePasse
     * @param surnom
     */
    public Membre(String login, String motDePasse, String surnom) {
        this.login = login;
        this.motDePasse = motDePasse;
        this.surnom = surnom;
        projetsDiriges = new ArrayList<Projet>();
        projetsParticites = new ArrayList<Projet>();
        competencesMembres = new ArrayList<CompetenceMembre>();
    }

    //Methods

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public List<Projet> listerProjetsDiriges() {
        return projetsDiriges;
    }

    public void dirigerProjet(Projet projet){
        projetsDiriges.add(projet);
    }

    public void participerProjet(Projet projet){
        projetsParticites.add(projet);
    }
    public List<Projet> listerProjetsParticipes() {
        return projetsParticites;
    }

    public List<CompetenceMembre> getCompetencesMembres() {
        return competencesMembres;
    }

    public void setCompetencesMembres(List<CompetenceMembre> competencesMembres) {
        this.competencesMembres = competencesMembres;
    }
}