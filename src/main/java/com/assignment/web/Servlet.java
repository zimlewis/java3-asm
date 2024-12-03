package com.assignment.web;

import com.assignment.dao.CategoryDAO;
import com.assignment.dao.NewsletterDAO;
import com.assignment.dao.UserDAO;
import com.assignment.entity.Category;
import com.assignment.dao.NewsDAO;
import com.assignment.entity.News;
import com.assignment.entity.Newsletter;
import com.assignment.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Servlets
 */
@WebServlet(urlPatterns = {"/", "/news/*", "/category/*", "/user", "/newsletter", "/latest", "/most-viewed", "/dashboard", "/login", "/register"})
public class Servlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "";
        NewsDAO newsDAO = new NewsDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        NewsletterDAO newsletterDAO = new NewsletterDAO();
        UserDAO userDAO = new UserDAO();

        List<Category> categories = categoryDAO.findAll();
        request.setAttribute("categories", categories);

        if (request.getServletPath().equals("/")){
            List<News> newsInHomeList = newsDAO.findHomePageNews();


            request.setAttribute("newsList", newsInHomeList);
            page = "/views/news-list.jsp";
        }
        else if (request.getServletPath().equals("/newsletter")){
            page = "/views/newsletter.jsp";
        }
        else if (request.getServletPath().contains("/category")){
            String id = request.getPathInfo().substring(1);

            List<News> newsInCategory = newsDAO.findNewsByCategory(id);
            Category category = categoryDAO.find(id);

            request.setAttribute("message", category.getName());
            request.setAttribute("newsList", newsInCategory);
            page = "/views/news-list.jsp";
        }
        else if (request.getServletPath().contains("/news")){
            String id = request.getPathInfo().substring(1);

            News news = newsDAO.find(id);
            Category category = categoryDAO.find(news.getCategoryId());
            List<News> newsInCategory = newsDAO.findNewsByCategory(category.getId());

            request.setAttribute("newsInCategory", newsInCategory);
            request.setAttribute("category", category);
            request.setAttribute("news", news);
            page = "/views/news.jsp";
        } else if (request.getServletPath().equals("/latest")) {
            List<News> latestNews = newsDAO.findFiveLatestPost();

            request.setAttribute("newsList", latestNews);
            page = "/views/news-list.jsp";
        } else if (request.getServletPath().equals("/most-viewed")) {
            List<News> mostViewed = newsDAO.findMostView();

            request.setAttribute("newsList", mostViewed);

            page = "/views/news-list.jsp";
        } else if (request.getServletPath().equals("/dashboard")) {
            HttpSession session = request.getSession(false);
            User user = validateSession(session);
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            if (user.getRole()){
                request.setAttribute("newsList", newsDAO.findAll());
                request.setAttribute("userList", userDAO.findAll());
                request.setAttribute("categoryList", categoryDAO.findAll());
                request.setAttribute("newsletterList", newsletterDAO.findAll());
            }
            else {
                request.setAttribute("newsList", newsDAO.findNewsByUserId(user.getId()));
            }

            request.setAttribute("user", user);
            request.setAttribute("hideSideBar", true);
            page = "/views/dashboard.jsp";
        } else if (request.getServletPath().equals("/login")) {
            HttpSession session = request.getSession(false);
            User user = validateSession(session);
            if (user != null) {
                response.sendRedirect(request.getContextPath() + "/dashboard");
                return;
            }
            page = "/views/components/login.jsp";
        }

        request.setAttribute("page", page);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    User validateSession(HttpSession session) {
        UserDAO userDAO = new UserDAO();
        User user = null;
        if (session.getAttribute("user") != null) {
            user = userDAO.find(session.getAttribute("user").toString());
        }

        return user;
    }


}