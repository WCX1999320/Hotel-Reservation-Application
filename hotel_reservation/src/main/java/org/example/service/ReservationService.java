package org.example.service;

import org.example.model.Customer;
import org.example.model.IRoom;
import org.example.model.Reservation;
import org.example.model.Room;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    private final Map<String, List<Reservation>> reservations;
    private final Map<String,IRoom>rooms;
    private ReservationService(){
            reservations=new HashMap<>();
            rooms=new HashMap<>();
    }
    //static reference for ReservationService class
    public static ReservationService getInstance(){
        if(reservationService==null){
            reservationService=new ReservationService();
        }
        return  reservationService;
    }
    //method to add a room
    public void addRoom(IRoom room){
        if(getARoom(room.getRoomNumber())==null){
            rooms.put(room.getRoomNumber(),room);
        }else{
            System.out.println("This room has already been added");
        }
    }
    //method to retrieve a room
    public IRoom getARoom(String roomId){
            return rooms.getOrDefault(roomId,null);
    }
    //method to reserve a room
    public Reservation reserveARoom(Customer customer, IRoom iRoom, Date checkInDate, Date checkOutDate){
            Reservation reservation=null;
            if(rooms.containsKey(iRoom.getRoomNumber())){
                if(iRoom.isFree()){
                    String email=customer.getEmail();
                    if(!reservations.containsKey(email)) {
                        reservations.put(email,new ArrayList<>());
                    }
                    reservation=new Reservation(customer,iRoom,checkInDate,checkOutDate);
                    reservations.get(email).add(reservation);
                    System.out.println("You have successfully reserved a room");
                    Room room=(Room) iRoom;
                    room.setFree(false);
                }
            }else{
                System.out.println("Can't find this room");
            }
            return reservation;
    }
    //method to find all available rooms during checkInDate and checkOutDate
    //Firstly,find all free rooms
    //Secondly,find all available rooms from all reserved rooms which will be available during checkInDate and checkOutDate
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        List<IRoom> availableRooms=new ArrayList<>();
        for(IRoom room:rooms.values()){
            Room curRoom =(Room) room;
            if(curRoom.isFree()){
                availableRooms.add(curRoom);
            }
        }
        for(List<Reservation> reservationList:reservations.values()){
            for(Reservation reservation:reservationList){
                if(isBefore(reservation.checkOutDate,checkInDate)||isAfter(reservation.checkInDate,checkOutDate)){
                    availableRooms.add(reservation.iRoom);
                }
            }
        }
        return availableRooms;
    }
    boolean isBefore(Date date1,Date date2){
        return date1.compareTo(date2)<=0;
    }
    boolean isAfter(Date date1,Date date2){
        return date1.compareTo(date2)>=0;
    }
    //method to retrieve Customers' all reservation
    public Collection<Reservation> getCustomersReservation(Customer customer){
        return reservations.getOrDefault(customer.getEmail(),null);
    }
    public void printAllReservation(){
        for(List<Reservation> reservationList:reservations.values()){
            for(Reservation reservation:reservationList){
                System.out.println(reservation);
            }
        }
    }
    public Collection<IRoom> getRooms(){
        return rooms.values();
    }
    public Set<String> getRoomNums(){
        return rooms.keySet();
    }
}
