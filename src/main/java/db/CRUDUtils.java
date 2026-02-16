package db;

import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class with basic JDBC operations for User entity.
 */
public class CRUDUtils {

    private static String INSERT_USER = "INSERT INTO users (name, surname, email, address) VALUES (?, ?, ?, ?)";
    private static String DELETE_USER = "DELETE FROM users WHERE id = ?";

    /**
     * Executes custom query and maps result set to list of users.
     *
     * @param query select query
     * @return users list
     */
    public static List<User> getAllUsers(String query) {
        List<User> all_users = new ArrayList<User>();
        try (Connection connection = DBUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String address = rs.getString("address");

                all_users.add(new User(id, name, surname, email, address));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } finally {

        }
        return all_users;
    }

    /**
     * Inserts one user row into users table.
     *
     * @param user user data
     * @return currently returns empty list
     */
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

    /**
     * Loads users by optional name and surname filters.
     *
     * @param name optional name filter
     * @param surname optional surname filter
     * @return filtered users list
     */
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
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                String userSurname = resultSet.getString("surname");
                String userEmail = resultSet.getString("email");
                String userAddress = resultSet.getString("address");

                all_users.add(new User(id, userName, userSurname, userEmail, userAddress));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return all_users;
    }

    /**
     * Removes user by id.
     *
     * @param id user id
     * @return true if user was removed
     */
    public static boolean removeUser(int id) {
        try (Connection connection = DBUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1, id);
            int deletedRowsCount = preparedStatement.executeUpdate();
            return deletedRowsCount > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Updates user name and surname by id.
     *
     * @param id user id
     * @param name new name
     * @param surname new surname
     * @return true if user was updated
     */
    public static boolean updateUser(int id, String name, String surname) {
        try (Connection connection = DBUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ?, surname = ? WHERE id = ?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, id);
            int updatedRowsCount = preparedStatement.executeUpdate();
            return updatedRowsCount > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        } finally {

        }
    }
}
