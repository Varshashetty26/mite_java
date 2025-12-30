package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = null;
    // Collections to store rooms & reservation
    private Map<String, IRoom> rooms = new HashMap<>();
    private Set<Reservation> reservations = new HashSet<>();
    private ReservationService() {}

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }
    // to add rooms
    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }
    // to get a room using roomId
    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }
    // to reserve a room
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }
    // to find available rooms
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        List<IRoom> availableRooms = new ArrayList<>(rooms.values());
        // for loop to check the conflict
        for (Reservation reservation : reservations) {

            boolean overlaps =
                    checkInDate.before(reservation.getCheckOutDate()) &&
                            checkOutDate.after(reservation.getCheckInDate());

            if (overlaps) {
                availableRooms.remove(reservation.getRoom());
            }
        }
        return availableRooms;
    }
    // to get all reservation for a customer
    public Collection<Reservation> getCustomersReservation(Customer customer) {

        List<Reservation> result = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                result.add(reservation);
            }
        }

        return result;
    }
    //to print all reservations
    public void printAllReservation() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        }

        for (Reservation reservation : reservations) {
            System.out.println(reservation + "\n");
        }
    }
    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }
}
