package org.example.api;

import org.example.model.Customer;
import org.example.model.IRoom;
import org.example.model.Reservation;
import org.example.service.CustomerService;
import org.example.service.ReservationService;
import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource;
    private CustomerService customerService;
    private ReservationService reservationService;
    private HotelResource(){
        customerService=CustomerService.getInstance();
        reservationService=ReservationService.getInstance();
    }
    //static reference for HotelResource class
    public static HotelResource getInstance(){
        if(hotelResource==null){
            hotelResource=new HotelResource();
        }
        return hotelResource;
    }
    // retrieve a customer
    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    //add a customer
    public void createACustomer(String firstName, String lastName, String email){
        customerService.addCustomer(firstName,lastName,email);
    }
    //retrieve a room
    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }
    //make a reservation
    public Reservation bookARoom(Customer customer, IRoom iRoom, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(customer,iRoom,checkInDate,checkOutDate);
    }
    //retrieve a customer's reservation
    public Collection<Reservation>getCustomerReservations(String customerEmail){
        return reservationService.getCustomersReservation(this.getCustomer(customerEmail));
    }
    // find all available rooms
    public Collection<IRoom> findARoom(Date checkIn,Date checkOut){
        return reservationService.findRooms(checkIn,checkOut);
    }
}
