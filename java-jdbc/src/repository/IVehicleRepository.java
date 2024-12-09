package repository;

import java.util.List;
import model.Vehicle;

public interface IVehicleRepository{
    List <Vehicle> getAll();
    Boolean post(Vehicle vehicle);
    Boolean updateAvailability(Vehicle vehicle);
    Boolean delete(Vehicle vehicle);
    List <Vehicle> getAvailableProduct();
}