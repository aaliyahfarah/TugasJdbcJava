package controller;

import model.Customer;
import repository.implement.CustomerRepository;
import utils.DbConnection;

public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository(DbConnection.getConnection());

    public void get(){
        for(Customer customer : customerRepository.get()){
            System.out.println("ID : " + customer.getId());
            System.out.println("Name : " + customer.getFirstName() + " "+ customer.getLastName());
            System.out.println("Telephone : " + customer.getTelephone());
            System.out.println("Address : " + customer.getAddress());
            System.out.println("\n");
        }
    }

    public void post(){
        Customer customer = new Customer();
        //customer.setId(0);
        customer.setFirstName("Aaliyah");
        customer.setLastName("Farah");
        customer.setTelephone("+62312345078");
        customer.setAddress("Bekasi");

        Boolean result = customerRepository.post(customer);

        if(result){
            System.out.println("Insert data success");
        }

    }

    public void update(){
        Customer customer = new Customer();
        customer.setFirstName("jane");
        customer.setLastName("miller");
        customer.setTelephone("08113456780");
        customer.setAddress("bekasi");
        customer.setId(3);

        Boolean result = customerRepository.update(customer);

        if(result){
            System.out.println("Update data success");
        }
    }

    public void delete(){
        Customer customer = new Customer();
        customer.setId(12);

        Boolean result = customerRepository.delete(customer);

        if(result){
            System.out.println("Delete data success");
        }
    }

    public void getByTelephone(){
        String inputTelephone = "+628123456789";

        for(Customer customer : customerRepository.getByTelephone(inputTelephone)){
            System.out.println("ID : " + customer.getId());
            System.out.println("Name : " + customer.getFirstName() + " "+ customer.getLastName());
            System.out.println("Telephone : " + customer.getTelephone());
            System.out.println("Address : " + customer.getAddress());
            System.out.println("\n");
        }
    }

    public void getById(){
        Integer inputId = 9;

        for(Customer customer : customerRepository.getById(inputId)){
            System.out.println("ID : " + customer.getId());
            System.out.println("Name : " + customer.getFirstName() + " "+ customer.getLastName());
            System.out.println("Telephone : " + customer.getTelephone());
            System.out.println("Address : " + customer.getAddress());
            System.out.println("\n");
        }
    }
}
