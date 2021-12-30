package org.example.api;

import org.example.model.Customer;
import org.example.model.IRoom;
import org.example.service.CustomerService;
import org.example.service.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AdminResource {
    private static AdminResource adminResource;
    private CustomerService customerService;
    private ReservationService reservationService;
    private AdminResource(){
        customerService=CustomerService.getInstance();
        reservationService=ReservationService.getInstance();
    }
    //static reference for AdminResource class
    public static AdminResource getInstance(){
        if(adminResource==null){
            adminResource=new AdminResource();
        }
        return adminResource;
    }
    //retrieve a customer
    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    //add rooms
    public void addRoom(List<IRoom> rooms){
        for(IRoom room:rooms){
            reservationService.addRoom(room);
        }
    }
    //retrieve all rooms
    public Collection<IRoom>getAllRooms(){
        return reservationService.getRooms();
    }
    //retrieve all customers
    public Collection<Customer>getAllCustomers(){
        return customerService.getAllCustomers();
    }
    //display all reservations
    public void displayAllReservations(){
        reservationService.printAllReservation();
    }
    //retrieve all roomNums
    public Set<String> getAllRoomNums(){
        return reservationService.getRoomNums();
    }
}
