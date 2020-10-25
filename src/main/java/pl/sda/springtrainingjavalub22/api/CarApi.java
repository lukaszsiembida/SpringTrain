package pl.sda.springtrainingjavalub22.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import pl.sda.springtrainingjavalub22.api.model.SearchParams;
import pl.sda.springtrainingjavalub22.domain.car.Car;
import pl.sda.springtrainingjavalub22.domain.car.CarService;

import java.util.List;
import java.util.stream.Collectors;

//statyczna walidacja danych w spring

@RestController
@AllArgsConstructor
@RequestMapping("/api/car")
public class CarApi {

    private CarService carService;
    private Validator validator;

    @GetMapping
    public List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/search")
    public List<Car> getByParams(@RequestBody SearchParams searchParams) {
        return carService.findByParams(searchParams);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getOne(@PathVariable Long carId) {
        return carService.getCarById(carId).map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createCar(@RequestBody Car car, BindingResult bindingResult) {
        validator.validate(car, bindingResult);
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().
                    map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        carService.create(car);
        return ResponseEntity.status(201).build();
    }

   /*wersja walidacji w kontrolerze
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createCar(@RequestBody @Valid Car car, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().
                    map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        carService.create(car);
        return ResponseEntity.status(201).build();
    }*/

    @PutMapping
    public void updateCar(@RequestBody Car car) {
        carService.update(car);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@RequestParam Long carId) {
        carService.delete(carId);
    }
}
