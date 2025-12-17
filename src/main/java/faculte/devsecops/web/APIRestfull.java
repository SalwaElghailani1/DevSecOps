package faculte.devsecops.web;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class APIRestfull {
    @GetMapping("/hello")
    public String hello() {

        return "Hello DevOps 🚀 Spring Boot is running!";
    }
    @GetMapping("/test")
    public String test() {

        return "test jenkins !!!";
    }
}
