package com.example.foodTruck;

import com.example.foodTruck.dao.FoodTruckDAOImpl;
import com.example.foodTruck.model.FoodTruckDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FoodTruckApplication {

	public static void main(String[] args) throws Exception {

		List<FoodTruckDetails> trucks = new FoodTruckDAOImpl().getTrucks();
		final ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(trucks));
		System.exit(0);
		SpringApplication.run(FoodTruckApplication.class, args);

	}

}
