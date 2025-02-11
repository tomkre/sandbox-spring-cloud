package cz.tomkre.sandbox.spring_cloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api")
public class ApiController {

    @GetMapping("info")
    public Map<String, ?> info() {
        return Map.of(
            "message", "Tom is great!"
        );
    }

}
