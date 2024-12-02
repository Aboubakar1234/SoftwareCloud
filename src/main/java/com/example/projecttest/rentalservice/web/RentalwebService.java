package com.example.projecttest.rentalservice.web;

import com.example.projecttest.rentalservice.data.Car;
import com.example.projecttest.rentalservice.data.Dates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
public class RentalwebService {
    List<Car> cars = new ArrayList<Car>();

    Logger logger = LoggerFactory.getLogger(RentalwebService.class);


    public RentalwebService(){
        Car car = new Car("11AA22",1000);
        cars.add(car);
        car =  new Car("22BB33",3000);
        cars.add(car);
    }

    @GetMapping("/cars")
    public List<Car> getCars(){
        return cars;
    }

    @GetMapping("cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car getCarByPlateNumber(@PathVariable("plateNumber")String plateNumber) throws Exception{
        return cars.stream().filter(car->car.getPlateNumber().equals(plateNumber)).findFirst().orElse(null);
    }

    @PutMapping(value = "/cars/{plateNumber}")
    public void rent(@PathVariable("plateNumber") String plateNumber,
                     @RequestParam(value="rent",required=true)boolean rent,
                     @RequestBody Dates dates
                    ) throws CarNotFoundException {

        logger.info("Plate number: "+plateNumber);
        logger.info("Rent: "+rent);
        logger.info("Dates: "+dates);

        Car car = cars.stream().filter(aCar -> aCar.getPlateNumber().equals(plateNumber)).findFirst().orElse(null);
        //Car car = getCarByPlateNumber(plateNumber);
        if(car != null){
            if(rent == true){
                car.setRented(true);
                car.getDates().add(dates);
            } else {
                car.setRented(false);
            }
        } else {
            logger.error("Car not found: " + plateNumber);
            throw new CarNotFoundException(plateNumber);
        }

    }






}
