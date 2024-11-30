package com.assignment.web;

import com.assignment.dao.NewsDAO;
import com.assignment.dao.UserDAO;
import com.assignment.entity.News;
import com.assignment.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import java.util.UUID;

import java.io.IOException;
import java.util.Date;


@WebServlet(urlPatterns = {"/news/create", "/news/update", "/news/delete", "/news/post"})
@MultipartConfig()
public class NewsServlet extends HttpServlet {
    NewsDAO dao = new NewsDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/news/create":{
                create(req, resp);
                break;
            }
            case "/news/update":{
                update(req, resp);
                break;
            }
            case "/news/delete":{
                delete(req, resp);
                break;
            }
            case "/news/post":{
                postNews(req, resp);
                break;
            }
        }
    }

    void postNews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDAO userDAO = new UserDAO();
        User user = null;
        if (session.getAttribute("user") != null) {
            user = userDAO.find(session.getAttribute("user").toString());
        }


        if (user == null) {
            String referer = req.getHeader("referer");
            resp.sendRedirect(referer);
            return;
        }

        try {
            Part photo = req.getPart("image");
            String fileType = photo.getContentType().replace("image/", ".");
            String photoName = UUID.randomUUID().toString() + fileType;
            String path = "/static/news/images/" + photoName;
            String fileName = req.getServletContext().getRealPath(path);
            photo.write(fileName);

            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);

            News news = new News();
            BeanUtils.populate(news, req.getParameterMap());

            news.setImage(photoName);
            news.setAuthor(user.getId());

            dao.postNews(news);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            String referer = req.getHeader("referer");
            resp.sendRedirect(referer);
        }
    }

    void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Part photo = req.getPart("image");
            String fileType = photo.getContentType().replace("image/", ".");
            String photoName = UUID.randomUUID().toString() + fileType;
            String path = "/static/news/images/" + photoName;
            String fileName = req.getServletContext().getRealPath(path);
            photo.write(fileName);

            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);

            News news = new News();
            BeanUtils.populate(news, req.getParameterMap());

            news.setImage(photoName);

            dao.create(news);
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
            Part photo = req.getPart("image");
            String fileType = photo.getContentType().replace("image/", ".");
            String photoName = UUID.randomUUID().toString() + fileType;
            String path = "/static/news/images/" + photoName;
            String fileName = req.getServletContext().getRealPath(path);
            photo.write(fileName);

            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);

            News news = new News();
            BeanUtils.populate(news, req.getParameterMap());

            news.setImage(photoName);

            dao.update(news);
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

            News news = new News();
            BeanUtils.populate(news, req.getParameterMap());

            dao.delete(news.getId());
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
