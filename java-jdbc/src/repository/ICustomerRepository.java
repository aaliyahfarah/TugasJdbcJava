package repository;

import java.util.List;
import model.Customer;

public interface ICustomerRepository {
    List <Customer> get();
    Boolean post(Customer customer);
    Boolean update(Customer customer);
    Boolean delete(Customer customer);
    List <Customer> getByTelephone(String telephone);
    List <Customer> getById(Integer id);
}
