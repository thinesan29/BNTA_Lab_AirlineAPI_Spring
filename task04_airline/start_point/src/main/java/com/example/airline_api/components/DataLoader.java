package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public DataLoader(){

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // British Airway
        Flight britishAirway = new Flight("New York", 1000,"12/05/23","Morning");
        flightRepository.save(britishAirway);

        Passenger passenger1 = new Passenger("Ed", "ed@email.com");
        passenger1.getFlights().add(britishAirway);
        passengerRepository.save(passenger1);

        Passenger passenger2 = new Passenger("Colin", "colin@email.com");
        passenger1.getFlights().add(britishAirway);
        passengerRepository.save(passenger2);

        // American Airline
        Flight americanAirline = new Flight("Paris", 2000,"11/04/23","Evening");
        flightRepository.save(americanAirline);

        Passenger passenger3 = new Passenger("Richard", "richard@email.com");
        passenger1.getFlights().add(americanAirline);
        passengerRepository.save(passenger3);

        Passenger passenger4 = new Passenger("Phil", "phil@email.com");
        passenger1.getFlights().add(americanAirline);
        passengerRepository.save(passenger4);


        // Qatar Airway
        Flight qatarAirway = new Flight("Doha", 1500,"15/06/23","Evening");
        flightRepository.save(qatarAirway);

        Passenger passenger5 = new Passenger("Zsolt", "zsolt@email.com");
        passenger1.getFlights().add(qatarAirway);
        passengerRepository.save(passenger5);

        Passenger passenger6 = new Passenger("Eoan", "eoan@email.com");
        passenger1.getFlights().add(qatarAirway);
        passengerRepository.save(passenger6);

    }
}
