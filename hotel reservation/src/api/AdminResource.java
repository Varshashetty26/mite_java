package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    //static reference
    private static AdminResource adminResource = null;
    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();
    private AdminResource() {}
    public static AdminResource getInstance() {
        if (adminResource == null) {
            adminResource = new AdminResource();
        }
        return adminResource;
    }
    // to get customer using email
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }
    //to add multiple rooms
    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }
    //to get all room
    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }
    //to get all customers
    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    //to display all reservation
    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
