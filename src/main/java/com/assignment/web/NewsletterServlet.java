package com.assignment.web;


import com.assignment.dao.NewsletterDAO;
import com.assignment.entity.Category;
import com.assignment.entity.Newsletter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import com.assignment.entity.Mailer;

import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = {"/newsletter/create", "/newsletter/update", "/newsletter/delete"})
public class NewsletterServlet extends HttpServlet {
    NewsletterDAO dao = new NewsletterDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/newsletter/create":{
                create(req, resp);
                break;
            }
            case "/newsletter/update":{
                update(req, resp);
                break;
            }
            case "/newsletter/delete":{
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

            Newsletter newsletter = new Newsletter();
            BeanUtils.populate(newsletter, req.getParameterMap());

            dao.create(newsletter);

            String from = "ductats00511@fpt.edu.vn"; // Predefined service address
            String subject = "Subscription Successful";
            String body = "<h1>Thank you for subscribing!</h1><p>We’re excited to have you on board.</p>";

            String to = newsletter.getEmail();

            int error = Mailer.send(from, to, subject, body);

            if (error < 0) {
                req.setAttribute("newsletterMessage", "Error sending the email. Please try again.");
            } else {
                req.setAttribute("newsletterMessage", "Subscription email sent successfully!");
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

    void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DateTimeConverter dtc = new DateConverter(new Date());
            dtc.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dtc, Date.class);

            Newsletter newsletter = new Newsletter();
            BeanUtils.populate(newsletter, req.getParameterMap());

            dao.update(newsletter);


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

            Newsletter newsletter = new Newsletter();
            BeanUtils.populate(newsletter, req.getParameterMap());

            dao.delete(newsletter.getEmail());
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
