package servlets;

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

@WebServlet(name = "Inscription", urlPatterns = "/inscription")
public class Inscription extends HttpServlet {

    @Autowired
    private IFacade facade; //Injection du service Facade

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/vues/inscription.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupération des identifiants d'inscription
        String login = req.getParameter("login");
        String mdp = req.getParameter("mdp");
        String surnom = req.getParameter("surnom");

        Boolean inscription = facade.inscription(login, mdp, surnom);

        if (inscription){
            req.getSession().setAttribute("mCourant", facade.findMemberByLogin(login));
            this.getServletContext().getRequestDispatcher("/WEB-INF/vues/dashboard.jsp").forward(req, resp);
        }else {
            String erreurInscription = "Login déjà pris";
            req.setAttribute("erreurInscription", erreurInscription );
            this.getServletContext().getRequestDispatcher("/WEB-INF/vues/inscription.jsp").forward(req, resp);
        }

    }


}
