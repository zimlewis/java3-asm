package com.assignment.web;

import com.assignment.dao.UserDAO;
import com.assignment.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = {"/auth", "/auth/login", "/auth/logout"})
public class AuthenticationServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getServletPath().equals("/auth/login")) {
            try {
                DateTimeConverter dtc = new DateConverter(new Date());
                dtc.setPattern("yyyy-MM-dd");
                ConvertUtils.register(dtc, Date.class);

                User user = new User();
                BeanUtils.populate(user, req.getParameterMap());

                user = userDAO.checkLogin(user);

                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user.getId());
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            finally {
                String referer = req.getHeader("referer");
                resp.sendRedirect(referer);
            }
        }
    }
}
