package com.assignment.dao;

import com.assignment.entity.Newsletter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.assignment.utils.JDBC;

public class NewsletterDAO implements DAO<Newsletter> {
    @Override
    public List<Newsletter> findAll() {
        List<Newsletter> newsletters = new ArrayList<>();

        String query = "SELECT * FROM [dbo].[Newsletter]";

        try (ResultSet resultSet = JDBC.executeQuery(query)){
            while (resultSet.next()) {
                Newsletter newsletter = new Newsletter(
                        resultSet.getString("Email"),
                        resultSet.getBoolean("Enable")
                );
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newsletters;
    }

    @Override
    public Newsletter find(String email) {
        String query = "SELECT * FROM [dbo].[Newsletter] WHERE [Email] = ?";

        try (ResultSet resultSet = JDBC.executeQuery(query, email)) {
            if (resultSet.next()) {
                Newsletter newsletter = new Newsletter(
                        resultSet.getString("Email"),
                        resultSet.getBoolean("Enable")
                );

                return newsletter;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void create(Newsletter newsletter) {
        String email = newsletter.getEmail();
        Boolean enable = newsletter.getEnable();

        String query = "INSERT INTO [dbo].[Newsletter] ([Email], [Enable]) VALUES (?, ?)";

        try {
            JDBC.executeUpdate(query, email, enable);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Newsletter newsletter) {
        String email = newsletter.getEmail();
        Boolean enable = newsletter.getEnable();

        String query = "UPDATE [dbo].[Newsletter] SET [Enable] = ? WHERE [Email] = ?";

        try {
            JDBC.executeUpdate(query, enable, email);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String email) {
        String query = "DELETE FROM [dbo].[Newsletter] WHERE [Email] = ?";

        try {
            JDBC.executeUpdate(query, email);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
