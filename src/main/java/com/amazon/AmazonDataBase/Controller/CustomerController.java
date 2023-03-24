package com.amazon.AmazonDataBase.Controller;

import com.amazon.AmazonDataBase.RequestDTO.CustomerRequestDto;
import com.amazon.AmazonDataBase.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/customer")
public class CustomerController
{
    @Autowired
    CustomerService customerService;


    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer (@RequestBody CustomerRequestDto customerRequestDto)
    {
        customerService.addCustomer(customerRequestDto);

        return new ResponseEntity<>(customerRequestDto.getName() +" registered!!", HttpStatus.CREATED);
    }

    @GetMapping ("/getCustomerById")
    public ResponseEntity<String> getCustomerById (@RequestParam("id") int customerId)
    {
        try {
            customerService.getCustomerById (customerId);
            return new ResponseEntity<>("We find your id" + customerId, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
