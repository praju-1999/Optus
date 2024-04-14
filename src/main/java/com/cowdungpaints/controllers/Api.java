import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.cowdungpaints.repositories.AddressRepository;
import com.cowdungpaints.repositories.CustomerRepository;
import com.cowdungpaints.repositories.OrderDetailsRepository;
import com.cowdungpaints.repositories.OrderRepository;
import com.cowdungpaints.entities.Address;
import com.cowdungpaints.entities.Customer;
import com.cowdungpaints.entities.Order;
import com.cowdungpaints.entities.OrderDetails;

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
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String directoryPath = "/uploads/";
            Path path = Paths.get(directoryPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            String filePath = directoryPath + fileName;
            try (OutputStream outputStream = new FileOutputStream(new File(filePath))) {
                outputStream.write(file.getBytes());
            }

            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public OrderDetailsRepository getOrderDetailsRepository() {
        return orderDetailsRepository;
    }
}




