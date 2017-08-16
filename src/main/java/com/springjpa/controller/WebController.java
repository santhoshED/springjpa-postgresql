package com.springjpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;

import com.springjpa.model.Customer;
import com.springjpa.repo.CustomerRepository;

@RestController
public class WebController {
    @Autowired
    CustomerRepository repository;
    @RequestMapping("/save")
    public String process(){
        repository.save(new Customer("Jack", "Smith"));
        repository.save(new Customer("Adam", "Johnson"));
        repository.save(new Customer("Kim", "Smith"));
        repository.save(new Customer("David", "Williams"));
        repository.save(new Customer("Peter", "Davis"));
        return "Done";
    }


    @RequestMapping("/findall")
    public String findAll(){
        String result = "<html>";

        for(Customer cust : repository.findAll()){
            result += "<div>" + cust.toString() + "</div>";
        }

        return result + "</html>";
    }

    @RequestMapping("/findbyid")
    public String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findOne(id).toString();
        return result;
    }

    @RequestMapping("/findbylastname")
    public String fetchDataByLastName(@RequestParam("lastname") String lastName){
        String result = "<html>";

        for(Customer cust: repository.findByLastName(lastName)){
            result += "<div>" + cust.toString() + "</div>";
        }

        return result + "</html>";
    }
    @RequestMapping(value="/addPerson",method=RequestMethod.GET)
    public String addPerson(){
        String result = "<html>First Name: <input/>" +
                "Last Name: <input/></html>";
        return result;
    }
    @RequestMapping(value="/addPerson",method=RequestMethod.POST)
    public String addPerson(@ModelAttribute String data ){

        return data;
    }
}