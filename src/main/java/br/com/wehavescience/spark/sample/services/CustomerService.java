package br.com.wehavescience.spark.sample.services;

import br.com.wehavescience.spark.sample.model.Customer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Gabriel Francisco <gabfssilva@gmail.com>
 */
public class CustomerService {
    private Set<Customer> customerList;

    public CustomerService(){
        customerList = new HashSet<>();

        customerList.add(new Customer(1, "Gabriel", "Systems Analyst", 21));
        customerList.add(new Customer(2, "João", "Systems Analyst", 22));
        customerList.add(new Customer(3, "Bruno", "Systems Analyst", 20));
    }

    public void save(Customer customer){
        customerList.add(customer);
    }

    public Customer remove(Customer customer){
        customerList.remove(customer);
        return customer;
    }

    public Customer remove(int id){
        Customer customer = findById(id);
        customerList.remove(customer);
        return customer;
    }

    public Set<Customer> findAll(){
        return customerList;
    }

    public Customer findById(int id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }}
