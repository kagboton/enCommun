package controleurs;


import beans.Membre;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import services.IFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes("loginCourant")
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
     * @param model
     * @return la jsp de connexion
     */

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

        if (facade.estConnecte((String)session.getAttribute("loginCourant"))) {
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

        Membre m = facade.inscription(membre.getLogin(), membre.getMotDePasse(), membre.getSurnom()); //inscription d'un nouveau membre

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
    public String deconnexion(@SessionAttribute(value = "loginCourant", required = false) String log, WebRequest request, SessionStatus sessionStatus, Model model){

        if(facade.estConnecte(log)){ //si le membre est bien connecte
            Boolean deconnecte = facade.deconnexion(log); // deconnexion du membre
            if (deconnecte){//la déconnexion s'est bien passée
                sessionStatus.setComplete();
                request.removeAttribute(log, WebRequest.SCOPE_SESSION);
                model.addAttribute("okDeconnexion", "Deconnexion effectuée avec succès");
                return "accueil";

            }else{//la deconnexion s'est pas bien passée
                model.addAttribute("erreurDeconnexion", "Deconnexion impossible");
                return "accueil";
            }

        }else{//Si le membre n'est pas connecté
            model.addAttribute("erreurDeconnexion", "Deconnexion impossible");
            return "accueil";
        }


    }







}
