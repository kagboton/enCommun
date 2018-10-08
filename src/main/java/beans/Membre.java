package beans;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Membre {

    @Id
    @Column(name = "LOGIN")
    @Size(min = 5, message = "Au - 5 caractères")
    @NotNull
    @NotBlank
    private String login;

    @Column(name = "MOTDEPASSE", nullable = false)
    @NotNull
    @NotBlank
    private String motDePasse;

    @Column(name = "SURNOM")
    private String surnom;

    @ManyToMany(mappedBy = "membres")
    private List<Projet> projetsParticites; //un membre peut participer à un ou plusieurs projets

    @OneToMany(mappedBy = "responsable")
    private List<Projet> projetsDiriges; //un membre peut être responsable d'un ou de plusieurs projets

    @OneToMany(mappedBy = "membre")
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
