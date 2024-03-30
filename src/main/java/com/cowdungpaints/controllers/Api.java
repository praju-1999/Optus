package com.cowdungpaints.controllers;

import com.cowdungpaints.entities.Address;
import com.cowdungpaints.entities.Customer;
import com.cowdungpaints.entities.Order;
import com.cowdungpaints.entities.OrderDetails;
import com.cowdungpaints.repositories.AddressRepository;
import com.cowdungpaints.repositories.CustomerRepository;
import com.cowdungpaints.repositories.OrderDetailsRepository;
import com.cowdungpaints.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class Api {

    private OrderRepository orderRepository;
    private AddressRepository addressRepository;
    private CustomerRepository customerRepository;
    private OrderDetailsRepository orderDetailsRepository;

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setOrderDetailsRepository(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public ResponseEntity<Order> saveOrder(Order order) {
        orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    public ResponseEntity<Order> getOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Address> addAddress(Address address) {
        addressRepository.save(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    public ResponseEntity<Customer> addCustomer(Customer customer) {
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    public ResponseEntity<OrderDetails> addOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
        return new ResponseEntity<>(orderDetails, HttpStatus.CREATED);
    }

    public ResponseEntity<String> uploadFile(MultipartFile file) {
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }

    @Test
    public void testAddOrderDetails() {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(1L);
        orderDetails.setProduct("Paint");
        orderDetails.setQuantity(5);

        ResponseEntity<OrderDetails> response = addOrderDetails(orderDetails);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(orderDetails, response.getBody());
    }
}





