package com.amazon.AmazonDataBase.Controller;

import com.amazon.AmazonDataBase.Exception.CustomerNotFoundException;
import com.amazon.AmazonDataBase.Model.Customer;
import com.amazon.AmazonDataBase.RequestDTO.CustomerRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.CustomerResponseDto;
import com.amazon.AmazonDataBase.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping ("/customer")
public class CustomerController
{
    @Autowired
    CustomerService customerService;


//    @PostMapping("/addCustomer")
//    public ResponseEntity<String> addCustomer (@RequestBody CustomerRequestDto customerRequestDto)
//    {
//        customerService.addCustomer(customerRequestDto);
//
//        return new ResponseEntity<>(customerRequestDto.getName() +" registered!!", HttpStatus.CREATED);
//    }
//
//    @GetMapping ("/getCustomerById")
//    public ResponseEntity<String> getCustomerById (@RequestParam("id") int customerId)
//    {
//        try {
//            customerService.getCustomerById (customerId);
//            return new ResponseEntity<>("We find your id" + customerId, HttpStatus.ACCEPTED);
//        }
//        catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
// welcome greeting in mail and mobile
@PostMapping("/add")
public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
    CustomerResponseDto customerResponseDto;
    try{
        customerResponseDto =customerService.addCustomer(customerRequestDto);
    }catch ( SQLException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.ALREADY_REPORTED);
    }
    return new ResponseEntity<>(customerResponseDto,HttpStatus.CREATED);
}

    @GetMapping("/getCustomerByMobile")
    public ResponseEntity getCustomerByMobile(@RequestParam String mobileNo){
        CustomerResponseDto customer;
        try{
            customer = customerService.getCustomerByMobile(mobileNo);
            return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity<>(new Customer(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCustomerByEmail")
    public ResponseEntity getCustomerByEmail(@RequestParam String email){
        CustomerResponseDto customer;
        try{
            customer = customerService.getCustomerByEmail(email);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity<>("Customer Not Found from this email!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity getAllCustomer(){
        List<CustomerResponseDto> customers;

        try{
            customers = customerService.getAllCustomer();
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
    }

    // goodBye greeting in mail and mobile
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") int customerId){
        List<String> customerInfo;
        try{
            customerInfo = customerService.deleteCustomer(customerId);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("ThankYou visit Again",HttpStatus.ACCEPTED);
    }
}
