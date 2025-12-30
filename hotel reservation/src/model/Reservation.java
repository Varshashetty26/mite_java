package model;

import java.util.Date;
// for the reservation
public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;
    //constructir
    public Reservation(Customer customer,IRoom room,Date checkInDate,Date checkOutDate){
        this.customer=customer;
        this.room=room;
        this.checkInDate=checkInDate;
        this.checkOutDate=checkOutDate;
    }
    //Getter methods
    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
    //to String method for description

    @Override
    public String toString() {
        return "RESERVATION DETAIL:\n" +
                "Customer: " + customer + "\n" +
                "Room: " + room + "\n" +
                "Check-In: " + checkInDate + "\n" +
                "Check-Out: " + checkOutDate;
    }
}
