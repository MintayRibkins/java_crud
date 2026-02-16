package db;

import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

    private static String INSERT_USER = "INSERT INTO users (name, surname, email, address) VALUES (?, ?, ?, ?)";

    public static List<User> getAllUsers(String query) {
        List<User> all_users = new ArrayList<User>();
        try (Connection connection = DBUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
//                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String address = rs.getString("address");

                all_users.add(new User(name, surname, email, address));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } finally {

        }
        return all_users;
    }

    public static List<User> addUser(User user) {
        List<User> all_users = new ArrayList<User>();
        try (Connection connection = DBUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAddress());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
        }
        return all_users;
    }

    public static List<User> getUsersByNameAndSurname(String name, String surname) {
        List<User> all_users = new ArrayList<User>();
        String sqlQuery = "SELECT * FROM users WHERE 1=1";

        if (!name.isEmpty()) {
            sqlQuery = sqlQuery + " AND name = ?";
        }
        if (!surname.isEmpty()) {
            sqlQuery = sqlQuery + " AND surname = ?";
        }

        try (Connection connection = DBUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            int parameterIndex = 1;

            if (!name.isEmpty()) {
                preparedStatement.setString(parameterIndex, name);
                parameterIndex = parameterIndex + 1;
            }
            if (!surname.isEmpty()) {
                preparedStatement.setString(parameterIndex, surname);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userName = resultSet.getString("name");
                String userSurname = resultSet.getString("surname");
                String userEmail = resultSet.getString("email");
                String userAddress = resultSet.getString("address");

                all_users.add(new User(userName, userSurname, userEmail, userAddress));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return all_users;
    }
}
