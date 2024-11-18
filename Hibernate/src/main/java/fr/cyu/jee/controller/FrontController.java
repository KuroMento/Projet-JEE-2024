package fr.cyu.jee.controller;

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
        if (action != null && action.equals("login")){
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
        if(categorie != null) {
            if (categorie.equals("subject")) {
                req.getRequestDispatcher("/WEB-INF/subject.jsp").forward(req, resp);
            }
            if (categorie.equals("class")) {
                req.getRequestDispatcher("/WEB-INF/class.jsp").forward(req, resp);
            }
            if (categorie.equals("user")) {
                req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
            }
            if (categorie.equals("grade")) {
                req.getRequestDispatcher("/WEB-INF/grade.jsp").forward(req, resp);
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
