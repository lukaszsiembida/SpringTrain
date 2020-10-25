package pl.sda.springtrainingjavalub22.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.springtrainingjavalub22.domain.car.Car;
import pl.sda.springtrainingjavalub22.domain.car.CarService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class MainPageContoller {

    private CarService carService;

    @GetMapping("/")
    ModelAndView displayMainPage() {
        List<String> manufacturers = carService.getAll().stream().map(Car::getManufacturer)
                .distinct().collect(Collectors.toList());

        ModelAndView mav = new ModelAndView();
        mav.addObject("date", LocalDate.now().toString());
        mav.addObject("manufacturers", manufacturers);

        mav.setViewName("main.html");
        return mav;
    }
}
