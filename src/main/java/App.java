import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DataManager dm = new DataManager();

        // 1. Prompt for new shipper and insert
        System.out.println("ADD NEW SHIPPER");
        System.out.print("Enter company name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        int newId = dm.insertShipper(name, phone);
        System.out.println("New shipper inserted with ID: " + newId);
        System.out.println();

        // 2. Display all shippers
        System.out.println("ALL SHIPPERS AFTER INSERT");
        printShippers(dm.getAllShippers());

        // 3. Prompt to change phone
        System.out.println("\nUPDATE SHIPPER PHONE");
        System.out.print("Enter the ID of the shipper to update: ");
        int updateId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the new phone number: ");
        String newPhone = scanner.nextLine();

        dm.updateShipperPhone(updateId, newPhone);

        // 4. Display all shippers again
        System.out.println("\nALL SHIPPERS AFTER UPDATE");
        printShippers(dm.getAllShippers());

        // 5. Prompt to delete a shipper
        System.out.println("\nDELETE SHIPPER");
        System.out.println("Do NOT delete shippers 1, 2, or 3.");
        System.out.print("Enter the ID of the shipper to delete: ");
        int deleteId = Integer.parseInt(scanner.nextLine());

        if (deleteId <= 3) {
            System.out.println("You are not allowed to delete shippers 1–3.");
        } else {
            dm.deleteShipper(deleteId);
            System.out.println("Shipper with ID " + deleteId + " deleted.");
        }

        // 6. Display all shippers one last time
        System.out.println("\nALL SHIPPERS AFTER DELETE");
        printShippers(dm.getAllShippers());

        scanner.close();
    }

    private static void printShippers(List<Shipper> shippers) {
        for (Shipper s : shippers) {
            System.out.println(s);
        }
    }
}