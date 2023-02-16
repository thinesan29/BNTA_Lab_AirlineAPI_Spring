package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;


    //Display all available flights
//    @GetMapping
//    public ResponseEntity<List<Flight>> getAllFlights(){
//        List<Flight> flights = flightService.getAllFlights();
//        return new ResponseEntity<>(flights, HttpStatus.OK);
//    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable long id){
        Optional<Flight> flight = flightService.getFlightById(id);
        if (flight.isPresent()){
            return new ResponseEntity<>(flight.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody Flight flight){
        flightService.addNewFlight(flight);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    // Book passenger on a flight
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Flight> addPassengerToFlight(@PathVariable Long id, @RequestBody Passenger passenger){
        Optional<Flight> flight = flightService.getFlightById(id);
        if (flight.isPresent()){
            Flight updatedFlight = flightService.addPassengerToFlight(flight.get(), passenger);
            return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancelFlight(@PathVariable Long id){
        flightService.cancelFlight(id);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    // ------------------EXTENSION -----------------------------------------------


    // Filter flights by destination

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam(required = false) String destination){
        List<Flight> flights;
        if (destination == null){
            flights = flightService.getAllFlights();
        } else {
            flights = flightService.filterFlightsByDestination(destination);
        }
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }




}
