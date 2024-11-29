package com.assignment.dao;

import com.assignment.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.assignment.utils.JDBC;

public class UserDAO implements DAO<User> {
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[User]";

        try (ResultSet resultSet = JDBC.executeQuery(query)) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("Id"),
                        resultSet.getString("Fullname"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getBoolean("Role"),
                        resultSet.getBoolean("Gender"),
                        resultSet.getDate("Birthday")
                );

                users.add(user);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return users;
    }

    @Override
    public User find(String id) {

        String query = "SELECT * FROM [dbo].[User] WHERE [Id] = ?";

        try (ResultSet resultSet = JDBC.executeQuery(query, id)){

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getString("Id"),
                        resultSet.getString("Fullname"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getBoolean("Role"),
                        resultSet.getBoolean("Gender"),
                        resultSet.getDate("Birthday")
                );

                return user;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void create(User user) {
        String id = user.getId();
        String fullname = user.getFullname();
        String password = user.getPassword();
        String email = user.getEmail();
        String phone = user.getPhone();
        Boolean role = user.getRole();
        Boolean gender = user.getGender();
        Date birthday = user.getBirthday();

        String query = "INSERT INTO [dbo].[User] ([Id], [Fullname], [Password], [Email], [PhoneNumber], [Role], [Gender], [Birthday]) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            JDBC.executeUpdate(query, id, fullname, password, email, phone, role, gender, birthday);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        String id = user.getId();
        String fullname = user.getFullname();
        String password = user.getPassword();
        String email = user.getEmail();
        String phone = user.getPhone();
        Boolean role = user.getRole();
        Boolean gender = user.getGender();
        Date birthday = user.getBirthday();

        String query = "update [dbo].[User] set [Fullname] = ?, [Password] = ?, [Email] = ?, [PhoneNumber] = ?, [Role] = ?, [Gender] = ?, [Birthday] = ? where [Id] = ?";
        try {
            JDBC.executeUpdate(query, fullname, password, email, phone, role, gender, birthday, id);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM [dbo].[User] WHERE [Id] = ?";

        try {
            JDBC.executeUpdate(query, id);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public User checkLogin(User user) {

        String query = "EXECUTE dbo.ConfirmAuthentication ? , ?";

        try (ResultSet resultSet = JDBC.executeQuery(query, user.getEmail(), user.getPassword())) {

            if (resultSet.next()) {
                user.setId(resultSet.getString("Id"));
                user.setFullname(resultSet.getString("Fullname"));
                user.setPassword(resultSet.getString("Password"));
                user.setEmail(resultSet.getString("Email"));
                user.setPhone(resultSet.getString("PhoneNumber"));
                user.setRole(resultSet.getBoolean("Role"));
                user.setGender(resultSet.getBoolean("Gender"));
                user.setBirthday(resultSet.getDate("Birthday"));

                return user;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
