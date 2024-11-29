package com.assignment.web;

import com.assignment.dao.UserDAO;
import com.assignment.entity.News;
import com.assignment.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = {"/user/create", "/user/update", "/user/delete"})
public class UserServlet extends HttpServlet {
    UserDAO dao = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/user/create":{
                create(req, resp);
                break;
            }
            case "/user/update":{
                update(req, resp);
                break;
            }
            case "/user/delete":{
                delete(req, resp);
                break;
            }
        }
    }

    void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);

            User user = new User();
            BeanUtils.populate(user, req.getParameterMap());

            dao.create(user);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            String referer = req.getHeader("referer");
            resp.sendRedirect(referer);
        }
    }

    void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);

            User user = new User();
            BeanUtils.populate(user, req.getParameterMap());

            dao.update(user);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            String referer = req.getHeader("referer");
            resp.sendRedirect(referer);
        }
    }

    void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);

            User user = new User();
            BeanUtils.populate(user, req.getParameterMap());

            dao.delete(user.getId());
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
