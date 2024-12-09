import controller.CustomerController;
import controller.VehicleController;

public class App {
    
    public static void main(String[] args) throws Exception {
        CustomerController customerController = new CustomerController();
        customerController.get();
        customerController.post();
        customerController.update();
        customerController.delete();
        customerController.getByTelephone();
        customerController.getById();

        VehicleController vehicleController = new VehicleController();
        vehicleController.getAll();
        vehicleController.post();
        vehicleController.updateAvailability();
        vehicleController.delete();
        vehicleController.getAvailableProduct();
    }
}
