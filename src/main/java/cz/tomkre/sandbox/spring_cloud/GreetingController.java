package cz.tomkre.sandbox.spring_cloud;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/greetings")
public class GreetingController {

    @GetMapping("en")
    public Greeting getGreeting() {
        return new Greeting("en", "Good morning");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Greeting createGreeting(@RequestBody Greeting greeting) {
        return greeting;
    }

}
