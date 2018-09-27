package modele.beans;

public class CompetenceMembre {

    private int niveau;
    private String commentaire;

    private Membre membre;
    private Competence competence;

    public CompetenceMembre(int niveau, String commentaire) {
        this.niveau = niveau;
        this.commentaire = commentaire;
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
