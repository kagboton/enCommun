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

@WebServlet(name = "Connexion", urlPatterns = "/connexion")
public class Connexion extends HttpServlet {

    @Autowired
    private IFacade facade; //Injection du service Facade

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/vues/connexion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Récupération des identifiants de connexion
        String login = req.getParameter("login");
        String mdp = req.getParameter("mdp");

        //Connexion
        Boolean connecte = facade.connexion(login, mdp);

        if(connecte){
            req.getSession().setAttribute("mCourant", facade.findMemberByLogin(login));
            this.getServletContext().getRequestDispatcher("/WEB-INF/vues/dashboard.jsp").forward(req, resp);
        }else {
            String erreurConnexion = "Les identifiants incorrectes";
            req.setAttribute("erreurConnexion",erreurConnexion );
            this.getServletContext().getRequestDispatcher("/WEB-INF/vues/connexion.jsp").forward(req, resp);
        }
    }


}
