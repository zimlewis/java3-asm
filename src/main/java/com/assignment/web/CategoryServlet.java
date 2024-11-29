package com.assignment.web;

import com.assignment.dao.CategoryDAO;
import com.assignment.entity.Category;
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

@WebServlet(urlPatterns = {"/category/create", "/category/update", "/category/delete"})
public class CategoryServlet extends HttpServlet {
    CategoryDAO dao = new CategoryDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/category/create":{
                create(req, resp);
                break;
            }
            case "/category/update":{
                update(req, resp);
                break;
            }
            case "/category/delete":{
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

            Category category = new Category();
            BeanUtils.populate(category, req.getParameterMap());

            dao.create(category);
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

            Category category = new Category();
            BeanUtils.populate(category, req.getParameterMap());

            dao.update(category);
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

            Category category = new Category();
            BeanUtils.populate(category, req.getParameterMap());

            dao.delete(category.getId());
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
