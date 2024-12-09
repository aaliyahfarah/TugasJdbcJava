package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.Vehicle;
import repository.implement.VehicleRepository;
import utils.DbConnection;

public class VehicleController {
    VehicleRepository vehicleRepository = new VehicleRepository(DbConnection.getConnection());

    public void getAll(){
        System.out.println("Get All Products");
        for(Vehicle vehicle : vehicleRepository.getAll()){
            System.out.println("ID : " + vehicle.getId());
            System.out.println("Added at : " + vehicle.getAddedAt());
            System.out.println("Available : " + vehicle.getIsAvailable());
            System.out.println("Type : " + vehicle.getTypeId());
            System.out.println("\n");
        }
    }

    public void post(){
        Vehicle vehicle = new Vehicle();
        vehicle.setId("2-48823328");
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        vehicle.setAddedAt(currentDate);
        vehicle.setTypeId(2);

        Boolean result = vehicleRepository.post(vehicle);

        if(result){
            System.out.println("Insert data success");
        }
    }

    public void updateAvailability() {
        Vehicle vehicle = new Vehicle();
        vehicle.setIsAvailable(false);
        vehicle.setId("1-72393282");

        Boolean result = vehicleRepository.updateAvailability(vehicle);

        if(result){
            System.out.println("Update data success");
        }
    }

    public void delete() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId("2-48823328");

        Boolean result = vehicleRepository.delete(vehicle);

        if(result){
            System.out.println("Delete data success");
        }
    }

    public void getAvailableProduct(){
        System.out.println("Get Available Products");
        for(Vehicle vehicle : vehicleRepository.getAvailableProduct()){
            System.out.println("ID : " + vehicle.getId());
            System.out.println("Type : " + vehicle.getTypeName());
            System.out.println("\n");
        }
    }
}
