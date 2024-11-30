package com.assignment.dao;

import com.assignment.entity.News;

import java.awt.image.RescaleOp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.assignment.utils.JDBC;

public class NewsDAO implements DAO<News> {
    @Override
    public List<News> findAll() {
        List<News> newsList = new ArrayList<>();
        String sql = "select * from [dbo].[News]";

        try (ResultSet resultSet = JDBC.executeQuery(sql)){
            while (resultSet.next()) {
                News news = new News(
                        resultSet.getString("Id"),
                        resultSet.getString("Title"),
                        resultSet.getString("content"),
                        resultSet.getString("Image"),
                        resultSet.getDate("PostedDate"),
                        resultSet.getString("Author"),
                        resultSet.getInt("ViewCount"),
                        resultSet.getString("CategoryId"),
                        resultSet.getBoolean("Home")
                );

                newsList.add(news);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newsList;
    }

    @Override
    public News find(String id) {
        String query = "select * from [dbo].[News] where [Id] = ?";

        try (ResultSet resultSet = JDBC.executeQuery(query, id)){
            if (resultSet.next()) {
                News news = new News(
                        resultSet.getString("Id"),
                        resultSet.getString("Title"),
                        resultSet.getString("content"),
                        resultSet.getString("Image"),
                        resultSet.getDate("PostedDate"),
                        resultSet.getString("Author"),
                        resultSet.getInt("ViewCount"),
                        resultSet.getString("CategoryId"),
                        resultSet.getBoolean("Home")
                );

                return news;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void postNews(News news) {
        String id = String.valueOf(findAll().size());
        String title = news.getTitle();
        String content = news.getContent();
        String image = news.getImage();
        Date postedDate = new Date();
        String author = news.getAuthor();
        Integer viewCount = 0;
        String categoryId = news.getCategoryId();
        Boolean home = false;

        String query = "INSERT INTO [dbo].[News] ([Id], [Title], [Content], [Image], [PostedDate], [Author], [ViewCount], [CategoryId], [Home]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            JDBC.executeUpdate(query, id, title, content, image, postedDate, author, viewCount, categoryId, home);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(News news) {
        String id = news.getId();
        String title = news.getTitle();
        String content = news.getContent();
        String image = news.getImage();
        Date postedDate = news.getPostedDate();
        String author = news.getAuthor();
        Integer viewCount = news.getViewCount();
        String categoryId = news.getCategoryId();
        Boolean home = news.getHome();

        String query = "INSERT INTO [dbo].[News] ([Id], [Title], [Content], [Image], [PostedDate], [Author], [ViewCount], [CategoryId], [Home]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            JDBC.executeUpdate(query, id, title, content, image, postedDate, author, viewCount, categoryId, home);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(News news) {
        String id = news.getId();
        String title = news.getTitle();
        String content = news.getContent();
        String image = news.getImage();
        Date postedDate = news.getPostedDate();
        String author = news.getAuthor();
        Integer viewCount = news.getViewCount();
        String categoryId = news.getCategoryId();
        Boolean home = news.getHome();

        String query = "UPDATE [dbo].[News] set [Title] = ?, [Content] = ?, [Image] = ?, [PostedDate] = ?, [Author] = ?, [ViewCount] = ?, [CategoryId] = ?, [Home] = ? WHERE [Id] = ?";
        try{
            JDBC.executeUpdate(query, title, content, image, postedDate, author, viewCount, categoryId, home, id);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM [dbo].[News] WHERE [Id] = ?";

        try {
            JDBC.executeUpdate(query, id);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<News> findMostView() {
        List<News> newsList = new ArrayList<>();
        String sql = "EXECUTE dbo.GetFiveMostViewsNews";

        try (ResultSet resultSet = JDBC.executeQuery(sql)){
            while (resultSet.next()) {
                News news = new News(
                        resultSet.getString("Id"),
                        resultSet.getString("Title"),
                        resultSet.getString("content"),
                        resultSet.getString("Image"),
                        resultSet.getDate("PostedDate"),
                        resultSet.getString("Author"),
                        resultSet.getInt("ViewCount"),
                        resultSet.getString("CategoryId"),
                        resultSet.getBoolean("Home")
                );

                newsList.add(news);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newsList;
    }

    public List<News> findNewsByCategory(String categoryId) {
        List<News> newsList = new ArrayList<>();
        String sql = "EXECUTE dbo.FindNewsByCategory ?";

        try (ResultSet resultSet = JDBC.executeQuery(sql, categoryId)){
            while (resultSet.next()) {
                News news = new News(
                        resultSet.getString("Id"),
                        resultSet.getString("Title"),
                        resultSet.getString("content"),
                        resultSet.getString("Image"),
                        resultSet.getDate("PostedDate"),
                        resultSet.getString("Author"),
                        resultSet.getInt("ViewCount"),
                        resultSet.getString("CategoryId"),
                        resultSet.getBoolean("Home")
                );

                newsList.add(news);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newsList;
    }

    public List<News> findFiveLatestPost() {
        List<News> newsList = new ArrayList<>();
        String sql = "EXECUTE dbo.FindFiveLatestUploaded ";

        try (ResultSet resultSet = JDBC.executeQuery(sql)){
            while (resultSet.next()) {
                News news = new News(
                        resultSet.getString("Id"),
                        resultSet.getString("Title"),
                        resultSet.getString("content"),
                        resultSet.getString("Image"),
                        resultSet.getDate("PostedDate"),
                        resultSet.getString("Author"),
                        resultSet.getInt("ViewCount"),
                        resultSet.getString("CategoryId"),
                        resultSet.getBoolean("Home")
                );

                newsList.add(news);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newsList;
    }


    public List<News> findHomePageNews() {
        List<News> newsList = new ArrayList<>();
        String sql = "select * from [dbo].[News] where [Home] = 1";

        try (ResultSet resultSet = JDBC.executeQuery(sql)){
            while (resultSet.next()) {
                News news = new News(
                        resultSet.getString("Id"),
                        resultSet.getString("Title"),
                        resultSet.getString("content"),
                        resultSet.getString("Image"),
                        resultSet.getDate("PostedDate"),
                        resultSet.getString("Author"),
                        resultSet.getInt("ViewCount"),
                        resultSet.getString("CategoryId"),
                        resultSet.getBoolean("Home")
                );

                newsList.add(news);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newsList;
    }

    public List<News> findNewsByUserId(String userId) {
        String query = "EXECUTE dbo.FindNewsByUser ?";
        List<News> newsList = new ArrayList<>();

        try (ResultSet resultSet = JDBC.executeQuery(query, userId)){
            while (resultSet.next()) {
                News news = new News(
                        resultSet.getString("Id"),
                        resultSet.getString("Title"),
                        resultSet.getString("content"),
                        resultSet.getString("Image"),
                        resultSet.getDate("PostedDate"),
                        resultSet.getString("Author"),
                        resultSet.getInt("ViewCount"),
                        resultSet.getString("CategoryId"),
                        resultSet.getBoolean("Home")
                );

                newsList.add(news);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newsList;
    }
}
