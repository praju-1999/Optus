package com.cowdungpaints.controllers;

import com.cowdungpaints.dto.OrderPaymentRequestDTO;
import com.cowdungpaints.entities.*;
import com.cowdungpaints.repositories.*;
import com.cowdungpaints.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@RestController
@RequestMapping("/order")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/new")
	public ResponseEntity<?> saveOrder(@RequestBody OrderPaymentRequestDTO requestDTO) {
		try {
			if (requestDTO == null || requestDTO.getOrder() == null || requestDTO.getPaymentRequest() == null) {
				return new ResponseEntity<>("Payment is Successfully", HttpStatus.BAD_REQUEST);
			}

			Order order = requestDTO.getOrder();
			PaymentRequest paymentRequest = requestDTO.getPaymentRequest();

			Order savedOrder = orderRepository.save(order);

			Payment payment = paymentService.processPayment(savedOrder, paymentRequest);

			if (payment != null && payment.isSuccessful()) {
				return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Payment failed for the order", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error("An error occurred while processing the order: {}", e.getMessage(), e);
			return new ResponseEntity<>("Internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
		return orderRepository.findById(id)
				.map(order -> {
					Address address = order.getAddress();
					order.setAddress(address);
					return new ResponseEntity<>(order, HttpStatus.OK);
				})
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
		if (file.isEmpty()) {
			return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
		}

		try {
			byte[] bytes = file.getBytes();
			String filePath = "/uploads/" + file.getOriginalFilename();
			try (OutputStream outputStream = Files.newOutputStream(new File(filePath).toPath())) {
				outputStream.write(bytes);
			}

			return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
		} catch (IOException e) {
			logger.error("Failed to upload file", e);
			return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private class ErrorResponse {
		public ErrorResponse(String paymentFailed, String paymentIsNull) {
		}
	}
}




