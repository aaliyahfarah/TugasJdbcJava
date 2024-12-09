package repository.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Vehicle;
import repository.IVehicleRepository;

public class VehicleRepository implements IVehicleRepository{
    private Connection connection;

    public VehicleRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * " +
                        "FROM tb_m_vehicle v ";
        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getString(1));
                vehicle.setAddedAt(resultSet.getString(2));
                vehicle.setIsAvailable(resultSet.getBoolean(3));
                vehicle.setTypeId(resultSet.getInt(4));
                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public Boolean post(Vehicle vehicle) {
        String queryCheck = "SELECT * FROM tb_m_vehicle WHERE id = ?";
        //INSERT INTO tb_m_vehicle (id, added_at, tb_m_type_id) VALUES
        String queryInsert = "INSERT INTO tb_m_vehicle (id, added_at, tb_m_type_id) VALUE (?, ?, ?)";
        try {
            PreparedStatement checkStatement = connection.prepareStatement(queryCheck);
            checkStatement.setString(1, vehicle.getId());

            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Duplicate Id");
                return false;
            } else {
                PreparedStatement insertStatement = connection.prepareStatement(queryInsert);
                insertStatement.setString(1,vehicle.getId());
                insertStatement.setString(2,vehicle.getAddedAt());
                insertStatement.setInt(3,vehicle.getTypeId());
                Integer result = insertStatement.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateAvailability(Vehicle vehicle) {
        String query = "UPDATE tb_m_vehicle SET is_available = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1,vehicle.getIsAvailable());
            preparedStatement.setString(2, vehicle.getId());
            Integer result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Vehicle vehicle) {
        String query = "DELETE FROM tb_m_vehicle WHERE id = ?";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(query);
            deleteStatement.setString(1, vehicle.getId());
            Integer result = deleteStatement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Vehicle> getAvailableProduct() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT v.id, v.added_at, t.name " +
                        "FROM tb_m_vehicle v " +
                        "JOIN tb_m_type t ON t.id = v.tb_m_type_id " +
                        "WHERE v.is_available = 0 ";
        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getString(1));
                vehicle.setAddedAt(resultSet.getString(2));
                vehicle.setTypeName(resultSet.getString(3));
                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return vehicles;
    }
}
