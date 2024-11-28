package fr.cyu.jee.controller;

import fr.cyu.jee.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String categorie = req.getParameter("categorie");

        User loggedUser = (User) req.getSession().getAttribute("loggedUser");

        if( action != null ){
            if( action.equals("login")) {
                req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            } else if (action.equals("logout")) {
                req.getSession().setAttribute("loggedUser",null);
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }

        if(categorie != null) {
            if (categorie.equals("subject")) {
                req.getRequestDispatcher("/SubjectController").forward(req, resp);
            }
            if (categorie.equals("course")) {
                req.getRequestDispatcher("/CoursesController").forward(req, resp);
            }
            if (categorie.equals("user")) {
                req.getRequestDispatcher("/UserController").forward(req, resp);
            }
            if (categorie.equals("grade")) {
                req.getRequestDispatcher("/GradesController").forward(req, resp);
            }
        }
        else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
