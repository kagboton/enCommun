package controleurs;


import beans.Competence;
import beans.CompetenceMembre;
import beans.Membre;
import beans.Projet;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import services.IFacade;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@SessionAttributes({"loginCourant"})
@RequestMapping("/")
public class Controleur {

    @Autowired
    private IFacade facade;

    /**
     * Accès à la page d'accueil
     * @return la jsp d'accueil
     */
    @GetMapping(value = "/")
    public String accueil(){
        return "accueil";
    }

    /**
     * Accès à la page de connexion avec validation de saisies
     * @param session
     * @param model
     * @return la jsp de connexion si l'utilisateur n'est pas connecté et la jsp tableau de bord s'il est déjà connecté
     */
    @GetMapping(value = "/connexion")
    public String connexion(HttpSession session,
            Model model){

       if ((String)session.getAttribute("loginCourant") != null) {
           return "dashboard";
       }

        model.addAttribute("membre", new Membre()); //pour faire fonctionner les tags spring
        return "connexion";
    }

    /**
     * Connexion d'un membre existant avec validation de saisie
     * @param membre l'objet membre utilisé pour récupérer les inputs
     * @param result contient les eventuelles erreurs
     * @param model le modèle au sens Spring
     * @return la jsp (dashboard) du tableau de bord du membre
     */
    @PostMapping(value="/connexion")
    public String handleConnexion(
            @ModelAttribute("membre")
            @Valid Membre membre,
            BindingResult result,
            Model model){

        if(result.hasErrors()){//S'il y a une erreur lors de la connexion
            model.addAttribute("erreurConnexion", result);
            return "connexion";
        }

        Membre m = facade.connexion(membre.getLogin(), membre.getMotDePasse()); //connexion du membre

        if(m != null){ //si le membre existe
            model.addAttribute("mCourant", m); //on ajoute le membre courant
            model.addAttribute("loginCourant", m.getLogin()); //on met le login du membre courant en session
            return "dashboard";
        }else {//si le membre n'hesiste pas
            result.addError(
                    new ObjectError("membre", "Utilisateur inconnu !"));//on ajoute une erreur de niveau objet
            return "connexion";
        }

    }

    /**
     * Accès à la page d'inscription avec validation de saisies
     * @param session
     * @param model
     * @return la jsp d'inscription
     */
    @GetMapping(value = "/inscription")
    public String inscription(
            HttpSession session,
            Model model) {

        if ((String)session.getAttribute("loginCourant") != null) {
            return "dashboard";
        }
        model.addAttribute("membre", new Membre()); //pour faire fonctionner les tags spring
        return "inscription";
    }

    /**
     * Inscription d'un membre existant avec validation de saisie
     * @param membre l'objet membre utilisé pour récupérer les inputs
     * @param result contient les eventuelles erreurs
     * @param model le modèle au sens Spring
     * @return la jsp (dashboard) du tableau de bord du membre
     */
    @PostMapping(value="/inscription")
    public String handleInscription(
            @ModelAttribute("membre")
            @Valid Membre membre,
            BindingResult result,
            Model model ) {

        if (result.hasErrors()){
            model.addAttribute("erreurInscription", result);
            return "inscription";
        }

        Membre m = facade.inscription(membre.getLogin(), membre.getMotDePasse(), membre.getSurnom()); //création d'un nouveau membre et persistance du nouveau membre dans la bdd

        if(m != null){
            model.addAttribute("mCourant", m); //on ajoute le membre courant
            model.addAttribute("loginCourant", m.getLogin()); //on met le login du membre courant en session
            return "dashboard";
        }else {
            result.addError(
                    new ObjectError("membre", "Utilisateur déja existant !"));//on ajoute une erreur de niveau objet
            return "inscription";
        }
    }

    /**
     * Accéder au dashboard du membre connecté
     * @param session
     * @return
     */
    @GetMapping(value = "/dashboard")
    public String dashboard(HttpSession session){

        if ((String)session.getAttribute("loginCourant") != null) {
            return "dashboard";
        }
        return "accueil";
    }


    /**
     * Déconnexion d'un membre connecté
     * @param log
     * @param request
     * @param sessionStatus
     * @param model
     * @return
     */
    @GetMapping(value = "/deconnexion")
    public String deconnexion(@SessionAttribute(value = "loginCourant", required = false) String log,
                              HttpSession session,
                              WebRequest request,
                              SessionStatus sessionStatus,
                              Model model){

        if((String)session.getAttribute("loginCourant") != null){ //si le membre est bien connecte
            boolean deconnecte = facade.deconnexion(log);
            if (deconnecte){
                sessionStatus.setComplete();
                request.removeAttribute(log, WebRequest.SCOPE_SESSION);
                model.addAttribute("okDeconnexion", "Deconnexion effectuée avec succès");
                return "accueil";
            }else {
                model.addAttribute("erreurDeconnexion", "Deconnexion impossible");
                return "accueil";
            }

        }else{//Si le membre n'est pas connecté
            model.addAttribute("erreurDeconnexion", "Deconnexion impossible");
            return "accueil";
        }

    }

    /**
     * Accès à la page de toutes les competences qui existent
     * @return la jsp comprtences
     */
    @GetMapping(value = "/competences")
     public String listerCompetences(Model model){
        Collection<Competence> competences = facade.getAllCompetences();
        model.addAttribute("competences", competences);
        return "competences";
    }

    /**
     * Accès à la page de toutes les competences du membre courant
     * @return la jsp mesCompetences
     */
    @GetMapping(value = "/mesCompetences")
    public String listerCompetencesMembre(@SessionAttribute(value = "loginCourant", required = false) String log,
                                          Model model){

        if( facade.getCompetenceMembreListByMemberLogin(log).isEmpty()){
            model.addAttribute("mesCompetences", facade.getCompetenceMembreListByMemberLogin(log));
            model.addAttribute("messageKO", "Aucune compétences !");
            return "mesCompetences";
        }else {
            model.addAttribute("mesCompetences", facade.getCompetenceMembreListByMemberLogin(log));
            return "mesCompetences";
        }

    }


    /**
     * Accés à la page d'ajout d'une competence au membre courant
     * @param model
     * @return
     */
    @GetMapping(value = "/ajouterCompetence")
    public String ajouterCompetence(Model model){
        Collection<Competence> competences = facade.getAllCompetences(); //Liste de tous les membres à envoyer à la jsp
        model.addAttribute("competences", competences);
        model.addAttribute("competenceMembre", new CompetenceMembre()); //Pour faire fonctionner les tags spring
        return "ajouterCompetence";
    }

    /**
     * Ajouter une competence au membre courant
     * @param competenceMembre
     * @param request
     * @param result
     * @param model
     * @return la jsp des competence du membre si tout se passe bien
     */
    @PostMapping(value = "/ajouterCompetence")
    public String handleAjouterCompetence(@ModelAttribute("competenceMembre") @Valid CompetenceMembre competenceMembre,
                                          @SessionAttribute(value = "loginCourant", required = false) String log,
                                          HttpSession session,
                                          WebRequest request,
                                          BindingResult result,
                                          Model model){

        if (result.hasErrors()){
            //Envoi des erreurs
            model.addAttribute("message", "Ajout impossible !");
            model.addAttribute("competences", facade.getAllCompetences());
            result.addError(
                    new ObjectError("competenceMembre", "Ajout impossible!"));
            return "ajouterCompetence";
        }

        String intituleC = request.getParameter("intituleC");

        facade.ajouterCompetenceMembre(
                (Integer)competenceMembre.getNiveau(),
                competenceMembre.getCommentaire(),
                log,
                intituleC);
        model.addAttribute("message", "L'ajout s'est bien passé !");
        model.addAttribute("mesCompetences", facade.getCompetenceMembreListByMemberLogin(log)); //envoyer les competences du membre

        return "mesCompetences";

    }

    @GetMapping(value = "/supprimerCompetenceMembre")
    public String supprimerCompetenceMembre(@ModelAttribute("competenceMembre") @Valid CompetenceMembre competenceMembre,
                                            @SessionAttribute(value = "loginCourant", required = false) String log,
                                            @RequestParam(value = "id") int idCompetenceMembre,
                                            HttpSession session,
                                            WebRequest request,
                                            BindingResult result,
                                            Model model){

        boolean delete = facade.supprimerCompetenceMembre(idCompetenceMembre);

        if(delete){
            model.addAttribute("message", "Compétence membre bien supprimée");
            model.addAttribute("mesCompetences", facade.getCompetenceMembreListByMemberLogin(log)); //envoyer les competences du membre
            return "mesCompetences";
        }else {
            model.addAttribute("messageKO", "Aucune compétence membre supprimée");
            model.addAttribute("mesCompetences", facade.getCompetenceMembreListByMemberLogin(log)); //envoyer les competences du membre
            return "mesCompetences";

        }

    }


    /**
     * Accés à la page d'ajout d'un nouveau projet
     * @param model
     * @return
     */
    @GetMapping(value = "/ajouterProjet")
    public String ajouterProjet(Model model, @SessionAttribute(value = "loginCourant", required = false) String log){
        model.addAttribute("projet", new Projet()); //pour faire fonctionner les tags spring
        model.addAttribute("mesCompetences", facade.getCompetenceMembreListByMemberLogin(log)); //envoyer les competences du membre
       return  "ajouterProjet";
    }

    @PostMapping(value = "/ajouterProjet")
    public String handleAjouterProjet(@ModelAttribute("projet") @Valid Projet projet,
                                      @SessionAttribute(value = "loginCourant", required = false) String log,
                                      HttpSession session,
                                      WebRequest request,
                                      BindingResult result,
                                      Model model){

        if (result.hasErrors()){
            //Envoi des erreurs
            model.addAttribute("message", "Ajout impossible !");
            model.addAttribute("mesCompetences", facade.getCompetenceMembreListByMemberLogin(log)); //envoyer les competences du membre
            result.addError(
                    new ObjectError("projet", "Ajout impossible!"));
            return "ajouterProjet";
        }

        String intituleP = request.getParameter("intituleP");
        String descriptionP = request.getParameter("descriptionP");

        facade.ajouterProjet(intituleP, descriptionP, log);
        model.addAttribute("message", "L'ajout s'est bien passé !");
        model.addAttribute("mesProjets", facade.getMemberProjectsListByMemberLogin(log)); //envoyer les competences du membre

        return  "mesProjets";
    }

    /**
     * Accès à la page de tous les projets du membre courant
     * @return la jsp mesProjets
     */
    @GetMapping(value = "/mesProjets")
    public String listerProjetsMembre(@SessionAttribute(value = "loginCourant", required = false) String log,
                                          Model model){

        if( facade.getMemberProjectsListByMemberLogin(log).isEmpty()){
            model.addAttribute("messageKO", "Aucun projet !");
            return "mesProjets";
        }else {
            model.addAttribute("mesProjets", facade.getMemberProjectsListByMemberLogin(log));
            return "mesProjets";
        }

    }

}
