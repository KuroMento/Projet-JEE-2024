package fr.cyu.jee.controller;

import fr.cyu.jee.model.Subject;
import fr.cyu.jee.repository.SubjectRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet that returns the list of available subjects.
 */
@Controller
public class SubjectController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Subject> subjectArrayList = (ArrayList<Subject>) subjectRepository.findAll();
        req.setAttribute("subjectList",subjectArrayList);
        req.getRequestDispatcher("/WEB-INF/subject.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Autowired
    private SubjectRepository subjectRepository;
}
