package controleurs;


import beans.Membre;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import services.IFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
    @GetMapping(value = "/connexion")
    public String connexion(Model model){
        model.addAttribute("membre", new Membre()); //pour faire fonctionner les tags spring
        return "connexion";
    }

    /**
     * Connexion d'un membre existant avec validation de saisie
     * @return la jsp dashboard
     */

    /**
     * Connexion d'un membre existant avec validation de saisie
     * @param membre l'objet membre utilisé pour récupérer les inputs
     * @param result contient les eventuelles erreurs
     * @param model le modèle au sens Spring
     * @return
     */
    @PostMapping(value="/connexion")
    public String handleConnexion(
            @ModelAttribute("membre")
            @Valid Membre membre,
            BindingResult result,
            Model model){

        if(result.hasErrors())
            return "connexion";

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
     * Accès à la page d'inscpription
     * @return la jsp d'inscription
     */
    @GetMapping(value = "/inscription")
    public String inscription(){
        return "inscription";
    }

    /**
     * Inscription d'un nouveau membre
     * @return la jsp dashboard
     */
    @PostMapping(value="/inscription")
    public String handleInscription(){

        //todo
        return "dashboard";
    }







}
