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

@WebServlet(name = "Deconnexion", urlPatterns = "/deconnexion")
public class Deconnexion extends HttpServlet {

    Membre membreCourant = null;
    Boolean deconnecte = false;

    @Autowired
    private IFacade facade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("mCourant") != null){
            membreCourant = (Membre) req.getSession().getAttribute("mCourant");
            String login = membreCourant.getLogin();
            deconnecte = facade.deconnexion(login);
            req.getSession().invalidate();
            this.getServletContext().getRequestDispatcher("/WEB-INF/vues/accueil.jsp").forward(req, resp);
        }else{
            String erreurDeconexion = "Aucun membre connecté";
            req.setAttribute("erreurDeconexion", erreurDeconexion );
            this.getServletContext().getRequestDispatcher("/WEB-INF/vues/accueil.jspp").forward(req, resp);
        }


    }
}
