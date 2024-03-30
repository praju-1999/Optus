package com.cowdungpaints.controllers;

import com.cowdungpaints.entities.Address;
import com.cowdungpaints.entities.Customer;
import com.cowdungpaints.entities.Order;
import com.cowdungpaints.entities.OrderDetails;
import com.cowdungpaints.repositories.AddressRepository;
import com.cowdungpaints.repositories.CustomerRepository;
import com.cowdungpaints.repositories.OrderDetailsRepository;
import com.cowdungpaints.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/order")
public class OrderController {

	private Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@PostMapping("/new")
	public Order saveOrder(@RequestBody Order order) {
		logger.info("saveOrder method invoked with request data::{}", order);
		return orderRepository.save(order);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
		Order orders = orderRepository.findById(id).get();
		Address address = orders.getAddress();
		orders.setAddress(address);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@PostMapping("/address/add")
	public ResponseEntity<Address> addAddress(@RequestBody Address input) {
		Address savedAddress = addressRepository.save(input);
		return new ResponseEntity<>(savedAddress, HttpStatus.OK);
	}

	@PostMapping("/customer/add")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer input) {
		Customer customer = customerRepository.save(input);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PostMapping("/orderdetails/add")
	public ResponseEntity<OrderDetails> addOrderDetails(@RequestBody OrderDetails input) {
		OrderDetails orderDetails = orderDetailsRepository.save(input);
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }
}

