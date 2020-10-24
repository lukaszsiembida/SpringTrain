package pl.sda.springtrainingjavalub22.api.training;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class HelloWorldApi {

    @GetMapping(path = "/hello",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String helloWorld(@RequestParam(name = "firstName"/*, required = true*/) String firstName) {
        return "Hello " + firstName + " from my first api";
    }

    @PostMapping("/hello")
    public String createCar(@RequestBody NewCar newCar) {
        return String.format("Car %s %s created",
                newCar.getManufacturer(), newCar.getModel());
    }
   /* JSON W METODZIE POST http://localhost:8080/api/car
   {
        "manufacturer": "Ford",
            "model": "Focus",
            "yearOfProduction": "2020",
            "options": ["electric windows", "clima"]
    }
    Response: Car Ford Focus created
    */
}
