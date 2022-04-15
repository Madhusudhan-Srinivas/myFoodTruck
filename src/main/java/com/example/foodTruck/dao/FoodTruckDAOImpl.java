package com.example.foodTruck.dao;

import com.example.foodTruck.model.FoodTruckDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FoodTruckDAOImpl implements FoodTruckDAO{

    Logger logger = LogManager.getLogger(FoodTruckDAOImpl.class);

    @Override
    public List<FoodTruckDetails> getTrucks() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity;
        UriComponentsBuilder builder;
        List<FoodTruckDetails> foodTruckList =  new ArrayList<>();
        try {
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            entity = new HttpEntity<>(headers);

            builder = UriComponentsBuilder.fromHttpUrl("http://data.sfgov.org/resource/bbb8-hzi6.json");

            logger.debug("API url {}", builder.toUriString());

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<FoodTruckDetails[]> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    FoodTruckDetails[].class);

            foodTruckList = Arrays.asList(response.getBody());
        } catch(Exception ex) {
            logger.error("Cannot access food truck details", ex);
            throw new RuntimeException(ex);
        }

        return foodTruckList.stream().filter(data-> dateFilter(data.getStart24(), data.getEnd24())).collect(Collectors.toList());
    }

    public boolean dateFilter(String start24, String end24) {

        int start = Integer.parseInt(start24.substring(0, 2));
        int end = Integer.parseInt(end24.substring(0, 2));

        boolean status = false;
        Calendar cal = Calendar.getInstance();        //Create Calendar-Object
        cal.setTime(new Date());                      //Set the Calendar to now
        if(cal.get(Calendar.MINUTE) >=30){            //Rounding off hours by 30 minutes
            cal.add(Calendar.HOUR_OF_DAY,1);
        }
        int hour = cal.get(Calendar.HOUR_OF_DAY);     //Get the hour from the calendar
        if(hour <= end && hour >= start)              // Check if hour is between 8 am and 11pm
        {
            status = true;
        }
        return status;
    }
}
