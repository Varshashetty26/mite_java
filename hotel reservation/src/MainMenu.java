import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
//main menu
public class MainMenu {
    //scanner for the user input
    private static final Scanner user_input = new Scanner(System.in);
    private static final HotelResource hotelResource = HotelResource.getInstance();
    public static void main(String[] args) {
        String choices;
        do {
            printMainMenu();
            choices = user_input.nextLine();
            // switch to make the choice
            switch (choices) {
                case "1":
                    // to find & reserve room
                    findAndReserveRoom();
                    break;

                case "2":
                    // to see reservations made
                    seeMyReservations();
                    break;

                case "3":
                    // to create new account
                    createAccount();
                    break;

                case "4":
                    // Open admin menu
                    AdminMenu.startAdminMenu();
                    break;

                case "5":
                    //to exit the application
                    exitApplication();
                    break;

                default:
                    System.out.println("Please enter a number between 1 and 5.\n");
            }

        }
        //to show the main menu options
        while (!choices.equals("5"));
    }
    //to display the menu for the user
    private static void printMainMenu() {
        System.out.println("\n---------MAIN MENU-----------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin menu");
        System.out.println("5. Exit");
        System.out.print("Please select a number: ");
    }
    // to find and reserve the room
    private static void findAndReserveRoom() {
        try {
            System.out.println("Enter Check-In Date (mm/dd/yyyy):");
            Date checkIn = getInputDate();

            System.out.println("Enter Check-Out Date (mm/dd/yyyy):");
            Date checkOut = getInputDate();

            // to make sure check-out is after check-in
            if (checkOut.before(checkIn) || checkOut.equals(checkIn)) {
                System.out.println("Check-out date must be after check-in date.");
                return;
            }

            // Search for rooms
            Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);
            //if there is no room for the given date
            if (availableRooms.isEmpty()) {
                System.out.println("No rooms available for these dates.");
                return;
            }
            //show the available rooms
            System.out.println("Available Rooms:");
            for (IRoom room : availableRooms) {
                System.out.println(room);
            }
            //enter email
            System.out.println("Enter email :");
            String email = user_input.nextLine();
            //check if account is created
            Customer customer = hotelResource.getCustomer(email);
            //if there is account
            if (customer == null) {
                System.out.println("No customer found!\n Please create an account first.");
                return;
            }
            //select the room
            System.out.println("Enter room number you want to reserve:");
            String roomNumber = user_input.nextLine();

            IRoom room = hotelResource.getRoom(roomNumber);
            if (room == null) {
                System.out.println("Room not found!");
                return;
            }

            Reservation reservation = hotelResource.bookARoom(
                    email,
                    room,
                    checkIn,
                    checkOut
            );

            System.out.println("Reservation successful:");
            System.out.println(reservation);

        } catch (Exception e) {
            System.out.println("Invalid input!!\n Try again.");
        }
    }
    // to get the input date
    private static Date getInputDate() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.setLenient(false);

        while (true) {
            try {
                return formatter.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use mm/dd/yyyy:");
            }
        }
    }
    // to check the reservation made
    private static void seeMyReservations() {
        //check for room reservation using email
        System.out.println("Enter email :");
        String email = user_input.nextLine();
        Customer customer = hotelResource.getCustomer(email);
        //if no account is found
        if (customer == null) {
            System.out.println("No customer found with this email.\n Please create an account first!!");
            return;
        }

        Collection<Reservation> reservations = hotelResource.getCustomerReservations(email);
        //if no reservation made
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("You have no reservations!!");
        } else {
            // if there is reservation,show resevation details
            System.out.println("Your Reservations:");
            for (Reservation reservation : reservations)
                System.out.println(reservation);
        }
    }
    //to create new account
    private static void createAccount() {
        //enter the email
        System.out.println("Enter Email :");
        String email = user_input.nextLine();
        //enter name
        System.out.println("Enter First Name:");
        String firstName = user_input.nextLine();

        System.out.println("Enter Last Name:");
        String lastName = user_input.nextLine();
        try {
            //if correct input is given
            hotelResource.createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully!!!");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    //to exit from the application
    private static void exitApplication() {
        System.out.println("----------------------------------EXIT-------------------------------------------");
        System.exit(0);
    }


}
