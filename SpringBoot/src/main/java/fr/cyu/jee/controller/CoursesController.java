package fr.cyu.jee.controller;

import fr.cyu.jee.model.*;
import fr.cyu.jee.repository.StudentRepository;
import fr.cyu.jee.repository.TeacherRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

/**
 * This servlet send the according courses to a teacher or a student
 */
@Controller
public class CoursesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object loggedUser = req.getSession().getAttribute("loggedUser");
        User user = (User) loggedUser;
        if(user.getPermissions() == Permissions.STUDENT) {
            Student student = getStudent(user.getIdentification());
            req.setAttribute("courses",student.getCourses());
        }
        if(user.getPermissions() == Permissions.TEACHER) {
            Teacher teacher = getTeacher(user.getIdentification());
            req.setAttribute("courses",teacher.getCourses());
        }
        req.getRequestDispatcher("WEB-INF/course.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Autowired
    private StudentRepository studentRepository;

    /**
     *
     * @param id student's id
     * @return the logged student
     */
    public Student getStudent(String id){
        Optional<Student> studentById = studentRepository.findById(id);
        Student student = studentById.get();
        return student;
    }

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     *
     * @param id teacher's id
     * @return the logged teacher
     */
    public Teacher getTeacher(String id){
        Optional<Teacher> teacherById = teacherRepository.findById(id);
        Teacher teacher = teacherById.get();
        return teacher;
    }
}
