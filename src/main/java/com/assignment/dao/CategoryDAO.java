package com.assignment.dao;

import com.assignment.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.assignment.utils.JDBC;


public class CategoryDAO implements DAO<Category> {
    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();

        String query = "SELECT * FROM [dbo].[Category]";

        try (ResultSet resultSet = JDBC.executeQuery(query)){
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getString("Id"),
                        resultSet.getString("Name")
                );

                categories.add(category);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public Category find(String id) {
        String query = "SELECT * FROM [dbo].[Category] WHERE [Id] = ?";

        try (ResultSet resultSet = JDBC.executeQuery(query, id)) {
            if (resultSet.next()) {
                Category category = new Category(
                        resultSet.getString("Id"),
                        resultSet.getString("Name")
                );

                return category;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void create(Category category) {
        String id = category.getId();
        String name = category.getName();

        String query = "INSERT INTO [dbo].[Category] ([Id], [Name]) VALUES (?, ?)";

        try {
            JDBC.executeUpdate(query, id, name);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Category category) {
        String id = category.getId();
        String name = category.getName();

        String query = "UPDATE [dbo].[Category] SET [Name] = ? WHERE [Id] = ?";

        try {
            JDBC.executeUpdate(query, name, id);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM [dbo].[Category] WHERE [Id] = ?";

        try {
            JDBC.executeUpdate(query, id);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
