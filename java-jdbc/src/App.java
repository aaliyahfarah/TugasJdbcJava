import controller.CustomerController;

public class App {
    
    public static void main(String[] args) throws Exception {
        CustomerController customerController = new CustomerController();
        // customerController.get();
        customerController.post();
        //customerController.update();
        // customerController.delete();
        //customerController.getByTelephone();
        //customerController.getById();
    }
}
