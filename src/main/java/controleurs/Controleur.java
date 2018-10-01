package controleurs;


import modele.facade.IFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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



}
