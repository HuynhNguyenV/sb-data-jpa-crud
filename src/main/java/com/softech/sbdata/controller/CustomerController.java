package com.softech.sbdata.controller;

import com.softech.sbdata.entities.Customer;
import com.softech.sbdata.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = {"/", "/customer-list"})
    public String listCustomer(Model model,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort){
        Sort sortable = null;
        if (sort.equals("ASC")){
            sortable = Sort.by("id").ascending();
        }

        if (sort.equals("DESC")){
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortable);
        model.addAttribute("listCustomer", customerRepository.findCustomers(pageable));
        return "customer-list";
    }

    @RequestMapping("/customer-save")
    public String insertCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "customer-save";
    }

    @RequestMapping("customer-view/{id}")
    public String viewCustomer(@PathVariable int id, Model model){
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            model.addAttribute("customer", customer.get());
        }
        return "customer-view";
    }

    @RequestMapping("customer-update/{id}")
    public String updateCustomer(@PathVariable int id, Model model){
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            model.addAttribute("customer", customer.get());
        }
        return "customer-update";
    }

    @RequestMapping("/saveCustomer")
    public String doSaveCustomer(@ModelAttribute("Customer") Customer customer, Model model){
        customerRepository.save(customer);
        model.addAttribute("listCustomer", customerRepository.findAll());
        return "customer-list";
    }

    @RequestMapping("/updateCustomer")
    public String doUpdateCustomer(@ModelAttribute("Customer") Customer customer, Model model){
        customerRepository.save(customer);
        model.addAttribute("listCustomer", customerRepository.findAll());
        return "customer-list";
    }

    @RequestMapping("/customerDelete/{id}")
    public String doDeleteCustomer(@PathVariable int id, Model model){
        customerRepository.deleteById(id);
        model.addAttribute("listCustomer", customerRepository.findAll());
        return "customer-list";
    }

}
