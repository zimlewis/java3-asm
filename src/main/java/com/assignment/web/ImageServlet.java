package com.assignment.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/get-image/*")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String imageName = null;
        if (pathInfo != null && pathInfo.length() > 1) {
            imageName = pathInfo.substring(1);
        }

        String path = "/static/news/images/" + imageName;
        String fileName = req.getServletContext().getRealPath(path);

        File file = new File(fileName);

        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        resp.setContentType(getServletContext().getMimeType(imageName));
        resp.setContentLength((int) file.length());
        // Stream the image
        try (FileInputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = resp.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
