package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    //static references
    private static HotelResource hotelResource = null;
    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();
    private HotelResource() {}
    public static HotelResource getInstance() {
        if (hotelResource == null) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }
    // to get a customer using emailId
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }
    // to create customer
    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }
    // to Get room
    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }
    //to reserve/book  room
    public Reservation bookARoom(String customerEmail, IRoom room,
                                 Date checkInDate, Date checkOutDate) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }
    //to get customer reservation
    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }
    // to check the available rooms
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }
}
