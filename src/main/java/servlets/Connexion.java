package servlets;

import modele.beans.Membre;
import modele.facade.IFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Connexion", urlPatterns = {"/connexion", "/dashboard"})
public class Connexion extends HttpServlet {

    Membre membreCourant = null;
    Boolean connecte = false;

    @Autowired
    private IFacade facade; //Injection du service Facade

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("mCourant") != null ){//Si l'utilisateur est déjà connecté, je le renvoi vers son dashboard
            this.getServletContext().getRequestDispatcher("/WEB-INF/vues/dashboard.jsp").forward(req, resp);
        }else {//sinon
            this.getServletContext().getRequestDispatcher("/WEB-INF/vues/connexion.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupération des identifiants de connexion
        String login = req.getParameter("login");
        String mdp = req.getParameter("mdp");

        //Vérifier si le membre n'est pas déja connecté
        if(facade.estConnecte(login)){
            String erreurDejaConnecte = "Membre déjà connecté";
            req.setAttribute("erreurDejaConnecte", erreurDejaConnecte );
            this.getServletContext().getRequestDispatcher("/WEB-INF/vues/connexion.jsp").forward(req, resp);
        }else {
            //Connexion
            connecte = facade.connexion(login, mdp);

            if(connecte){
                membreCourant = facade.findMemberByLogin(login);
                req.getSession().setAttribute("mCourant", membreCourant);
                this.getServletContext().getRequestDispatcher("/WEB-INF/vues/dashboard.jsp").forward(req, resp);
            }else {
                String erreurConnexion = "Les identifiants incorrectes";
                req.setAttribute("erreurConnexion",erreurConnexion );
                this.getServletContext().getRequestDispatcher("/WEB-INF/vues/connexion.jsp").forward(req, resp);
            }
        }


    }


}
