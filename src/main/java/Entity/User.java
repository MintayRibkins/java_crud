package Entity;

import java.sql.*;

import db.DBUtils;

/**
 * User entity model used by API and JDBC layer.
 */
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String address;

    /**
     * Creates user entity instance.
     *
     * @param id user identifier
     * @param name user name
     * @param surname user surname
     * @param email user email
     * @param address user address
     */
    public User(int id, String name, String surname, String email, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
    }

    /**
     * Returns text representation for logs and debug output.
     *
     * @return user as string
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    /**
     * @return user identifier
     */
    public int getId() {
        return id;
    }

    /**
     * @param id user identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return user surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname user surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return user address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address user address
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
