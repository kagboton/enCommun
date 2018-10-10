package beans;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CompetenceMembre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_COMPETENCE_MEMBRE")
    private int id;

    @Column(name = "NIVEAU")
    private int niveau;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "MEMBRE_ID")
    private Membre membre;

    @ManyToOne
    @JoinColumn(name = "COMPETENCE_ID")
    private Competence competence;

    public CompetenceMembre() {
    }

    public CompetenceMembre(int niveau, String commentaire) {
        this.niveau = niveau;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }
}
