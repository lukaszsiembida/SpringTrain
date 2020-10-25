package pl.sda.springtrainingjavalub22.web;

import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.springtrainingjavalub22.api.model.SearchParams;
import pl.sda.springtrainingjavalub22.domain.car.Car;
import pl.sda.springtrainingjavalub22.domain.car.CarService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("mvc/car")
public class CarController {

    private CarService carService;  // wstrzyknięcie za pomocą konstruktora

    @GetMapping
    ModelAndView displayCarsPage(){
        ModelAndView mav = new ModelAndView("cars.html");
        mav.addObject("cars", carService.getAll());
        mav.addObject("todayDate", LocalDate.now());
        mav.addObject("params", new SearchParams());

        return mav;
    }

    @PostMapping("/search")
    ModelAndView handleCarFiltering(@ModelAttribute("params") SearchParams params){
        ModelAndView mav = new ModelAndView("cars.html");
        mav.addObject("cars", carService.searchByParams(params));
        mav.addObject("todayDate", LocalDate.now());
        mav.addObject("params", params);
        return mav;
    }

    @GetMapping("/add")
    ModelAndView displayAddCarPage(){
        ModelAndView mav = new ModelAndView("addCar.html");
        mav.addObject("car", new Car()); /*wyświetlenie formularza car z th:object*/
        return mav;
    }

    @GetMapping("/edit/{id}")
    ModelAndView displayAddCarPage(@PathVariable Long id){
        Optional<Car> car = carService.getCarById(id);
        ModelAndView mav = new ModelAndView();
        if(car.isPresent()){
            mav.addObject("car", car.get());  /*wyświetlenie formularza car z th:object*/
            mav.setViewName("addCar.html");
        } else {
            mav.addObject("message", String.format("Samochód z id %d nie istnieje", id));
            mav.setViewName("error.html");
        }
        return mav;
    }

    @PostMapping("/addOrEdit")
    String handleAddCar(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
         List<String> globalErrors = bindingResult.getGlobalErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
         model.addAttribute("globalErrors", globalErrors);

            return "addCar.html";
        }

        if(car.getId() != null){
            carService.update(car);
        } else {
            carService.create(car);
        }
        return "redirect:/mvc/car"; /*przekierowanie stringa za pomocą redirect*/
    }

    @GetMapping("/delete/{id}")
    String handledeleteCar(@PathVariable Long id){
        carService.delete(id);
        return "redirect:/mvc/car";
    }
}
