package Service;

import Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import db.CRUDUtils;
import tools.jackson.databind.ObjectMapper;

import java.io.PrintStream;
import java.util.List;

@SpringBootApplication
@RestController
public class CrudApplication {
    @Autowired
    private ObjectMapper objectMapper;

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    @GetMapping("/api/get_all_users")
    public String users(@RequestParam(value = "name", defaultValue = "") String name) {
        String request = "SELECT * FROM users";
        List<User> allUsers = null;
        if (name.isEmpty()) {
            allUsers = CRUDUtils.getAllUsers(request);
        } else {
            allUsers = CRUDUtils.getAllUsers(request + name);
        }

//        List<User> allUsers = CRUDUtils.getAllUsers("SELECT * FROM users");
//        System.out.println(allUsers);

        String json = objectMapper.writeValueAsString(allUsers);
        return json;
    }

    @PostMapping("/api/add_user")
    public String addUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address
    ) {
        try {
            CRUDUtils.addUser(new User(name, surname, email, address));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "User added successfully";
    }
}
