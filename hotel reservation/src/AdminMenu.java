import api.AdminResource;
import model.*;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
//Admin Menu
public class AdminMenu {

    public static void startAdminMenu() {
        //for input
        Scanner input = new Scanner(System.in);
        String adminoption;

        do {
            //display the admin options available
            printAdminMenu();
            //enter the choice
            adminoption = input.nextLine();
            //switch to the entered option
            switch (adminoption) {

                case "1":
                    // to see all customers list
                    seeAllCustomers();
                    break;

                case "2":
                    //to see all the rooms
                    seeAllRooms();
                    break;

                case "3":
                    // to see all reservations made
                    seeAllReservations();
                    break;

                case "4":
                    //to add new room
                    addARoom();
                    break;

                case "5":
                    // return to main menu
                    return;

                default:
                    System.out.println("Please enter a number between 1 and 5.\n");
            }

        } while (!adminoption.equals("5"));
    }

    private static void printAdminMenu() {
        System.out.println("\n-----------ADMIN MENU-------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.print("Please select a number: ");
    }
    //to see all customers
    private static void seeAllCustomers() {
        Collection<Customer> customers = AdminResource.getInstance().getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(System.out::println);
        }
    }
    //to see all rooms
    private static void seeAllRooms() {
        Collection<IRoom> rooms = AdminResource.getInstance().getAllRooms();
        //if thereis no room
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            rooms.forEach(System.out::println);
        }
    }
    //to check all the reservations made
    private static void seeAllReservations() {
        AdminResource.getInstance().displayAllReservations();
    }
    //to add new room
    private static void addARoom() {
        Scanner scanner = new Scanner(System.in);
        List<IRoom> roomsToAdd = new ArrayList<>();
        boolean addMoreRooms = true;

        while (addMoreRooms) {
            try {
                // enter room number
                System.out.print("Enter room number: ");
                String roomNumber = scanner.nextLine();

                //enter price for that room
                System.out.print("Enter price per night: ");
                double price = Double.parseDouble(scanner.nextLine());

                // single or double room
                System.out.print("Enter room type (1 for SINGLE, 2 for DOUBLE): ");
                String typeInput = scanner.nextLine();

                RoomType roomType;
                if (typeInput.equals("1")) {
                    roomType = RoomType.SINGLE;
                } else if (typeInput.equals("2")) {
                    roomType = RoomType.DOUBLE;
                } else {
                    System.out.println("Invalid room type. Please try again.");
                    continue;
                }

                // Create room object
                IRoom room;
                if (price == 0) {
                    room = new FreeRoom(roomNumber, roomType);
                } else {
                    room = new Room(roomNumber, price, roomType);
                }

                roomsToAdd.add(room);

                // if admin wants add room
                System.out.print("Do you want to add another room? (y/n): ");
                String more = scanner.nextLine();

                if (!more.equalsIgnoreCase("y")) {
                    addMoreRooms = false;
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        // to add rooms using AdminResource
        AdminResource.getInstance().addRoom(roomsToAdd);
        System.out.println("Room added successfully!!!");
    }

}
