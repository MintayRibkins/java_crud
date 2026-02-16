package Service;

import Entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import db.CRUDUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Main Spring Boot application with HTTP endpoints for User CRUD and CSV import.
 */
@SpringBootApplication
@RestController
public class CrudApplication {
    /**
     * Application entry point.
     *
     * @param args startup arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    /**
     * @param name    params from request
     * @param surname params from request
     * @return list of users
     */
    @GetMapping("/api/get_all_users")
    public String users(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "surname", defaultValue = "") String surname
    ) {
        List<User> allUsers = CRUDUtils.getUsersByNameAndSurname(name, surname);
        return allUsers.toString();
    }

    /**
     * Adds one user to database.
     *
     * @param name user name
     * @param surname user surname
     * @param email user email
     * @param address user address
     * @return operation result text
     */
    @PostMapping("/api/add_user")
    public String addUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address
    ) {
        try {
            CRUDUtils.addUser(new User(0, name, surname, email, address));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "User: " + "###" + name + "###" + "###" + surname + "###" + " added successfully";
    }

    /**
     * Removes user by id.
     *
     * @param id user id
     * @return operation result text
     */
    @DeleteMapping("/api/remove_user")
    public String removeUser(@RequestParam int id) {
        boolean isUserRemoved = CRUDUtils.removeUser(id);

        if (isUserRemoved) {
            return "User with id " + id + " removed successfully";
        }
        return "User with id " + id + " was not found";
    }

    /**
     * Updates user by id.
     * Current implementation updates only name and surname in DB layer.
     *
     * @param id user id
     * @param name user name
     * @param surname user surname
     * @param email user email (currently not used in DB update)
     * @param address user address (currently not used in DB update)
     * @return operation result text
     */
    @PutMapping("/api/update_user")
    public String updateUser(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String email,
            @RequestParam String address
    ) {
        boolean isUserUpdated = CRUDUtils.updateUser(id, name, surname);
        return isUserUpdated ? "User with id " + id + " updated successfully" : "User with id " + id + " was not found";
    }

    /**
     * Imports users from CSV file.
     * CSV is expected to have header and 4 columns in each row:
     * name,surname,email,address
     *
     * @param file CSV file from multipart/form-data request
     * @return import result text with added rows count
     */
    @PostMapping("/api/import_users_csv")
    public String importUsersCsv(@RequestParam("file") MultipartFile file) {
        int addedUsersCount = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            reader.readLine();//header
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                CRUDUtils.addUser(new User(0, values[0], values[1], values[2], values[3]));
                addedUsersCount++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return "CSV imported. Added: " + addedUsersCount;
    }

}
