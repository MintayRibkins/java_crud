package Service;

import Entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import db.CRUDUtils;

import java.util.List;

/**
 *
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

    @DeleteMapping("/api/remove_user")
    public String removeUser(@RequestParam int id) {
        boolean isUserRemoved = CRUDUtils.removeUser(id);

        if (isUserRemoved) {
            return "User with id " + id + " removed successfully";
        }
        return "User with id " + id + " was not found";
    }
}
