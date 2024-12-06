package repository.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import repository.ICustomerRepository;

public class CustomerRepository implements ICustomerRepository{
    private Connection connection;

    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Customer> get() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM tb_m_customer";
        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setTelephone(resultSet.getString(4));
                customer.setAddress(resultSet.getString(5));
                customers.add(customer);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Boolean post(Customer customer) {
        String queryCheck = "SELECT * FROM tb_m_customer WHERE telephone = ?";
        String queryInsert = "INSERT INTO tb_m_customer (first_name, last_name, telephone, address) VALUE (?, ?, ?, ?)";
        try {
            PreparedStatement checkStatement = connection.prepareStatement(queryCheck);
            checkStatement.setString(1, customer.getTelephone());

            ResultSet resultSet = checkStatement.executeQuery();

            // gabisa di rollback auto increment jadi lakukan pengecekan 
            if (resultSet.next()) {
                System.out.println("Duplicate telephone number");
                return false;
            } else {
                PreparedStatement insertStatement = connection.prepareStatement(queryInsert);
                //preparedStatement.setInt(1, customer.getId());
                insertStatement.setString(1,customer.getFirstName());
                insertStatement.setString(2,customer.getLastName());
                insertStatement.setString(3,customer.getTelephone());
                insertStatement.setString(4,customer.getAddress());
                Integer result = insertStatement.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Customer customer) {
        String query = "UPDATE tb_m_customer SET first_name = ?, last_name = ?, telephone = ?, address = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,customer.getFirstName());
            preparedStatement.setString(2,customer.getLastName());
            preparedStatement.setString(3,customer.getTelephone());
            preparedStatement.setString(4,customer.getAddress());
            preparedStatement.setInt(5, customer.getId());
            Integer result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Customer customer) {
        String query = "DELETE FROM tb_m_customer WHERE id = ?";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(query);
            deleteStatement.setInt(1, customer.getId());
            Integer result = deleteStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Customer> getByTelephone(String telephone) {
        String query = "SELECT * FROM tb_m_customer WHERE telephone = ?";
        List<Customer> customers = new ArrayList<>();

        try {
            PreparedStatement getIdStatement = connection.prepareStatement(query);
            getIdStatement.setString(1, telephone);

            ResultSet resultSet = getIdStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setTelephone(resultSet.getString(4));
                customer.setAddress(resultSet.getString(5));
                customers.add(customer);
            } else {
                System.out.println("Not found telephone");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> getById(Integer id) {
        String query = "SELECT * FROM tb_m_customer WHERE id = ?";
        List<Customer> customers = new ArrayList<>();

        try {
            PreparedStatement getIdStatement = connection.prepareStatement(query);
            getIdStatement.setInt(1, id);

            ResultSet resultSet = getIdStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setTelephone(resultSet.getString(4));
                customer.setAddress(resultSet.getString(5));
                customers.add(customer);
            } else {
                System.out.println("ID not found");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }    


}
