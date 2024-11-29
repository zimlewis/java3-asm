package com.assignment.web;

import com.assignment.dao.CategoryDAO;
import com.assignment.dao.NewsletterDAO;
import com.assignment.dao.UserDAO;
import com.assignment.entity.Category;
import com.assignment.dao.NewsDAO;
import com.assignment.entity.News;
import com.assignment.entity.Newsletter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Servlets
 */
@WebServlet(urlPatterns = {"/", "/news/*", "/category/*", "/user", "/newsletter", "/latest", "/most-viewed"})
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


            request.setAttribute("newsInHomeList", newsInHomeList);
            page = "/views/home.jsp";
        }
        else if (request.getServletPath().equals("/newsletter")){
            page = "/views/newsletter.jsp";
        }
        else if (request.getServletPath().contains("/category")){
            String id = request.getPathInfo().substring(1);

            List<News> newsInCategory = newsDAO.findNewsByCategory(id);
            Category category = categoryDAO.find(id);

            request.setAttribute("category", category);
            request.setAttribute("newsInCategory", newsInCategory);
            page = "/views/category.jsp";
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

            request.setAttribute("latestNews", latestNews);
            page = "/views/latest.jsp";
        } else if (request.getServletPath().equals("/most-viewed")) {
            List<News> mostViewed = newsDAO.findMostView();

            request.setAttribute("mostViewed", mostViewed);
            page = "/views/most-viewed.jsp";
        }


        request.setAttribute("page", page);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}