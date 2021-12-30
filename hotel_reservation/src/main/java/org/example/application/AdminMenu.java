package org.example.application;

import org.example.api.AdminResource;
import org.example.api.HotelResource;
import org.example.model.Customer;
import org.example.model.IRoom;
import org.example.model.Room;
import org.example.model.RoomType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static AdminMenu adminMenu;
    private AdminResource adminResource;
    private HotelResource hotelResource;
    private AdminMenu(){
        adminResource=AdminResource.getInstance();
        hotelResource=HotelResource.getInstance();
    }
    //static reference for AdminMenu class
    public static AdminMenu getInstance(){
        if (adminMenu==null){
            adminMenu=new AdminMenu();
        }
        return adminMenu;
    }
    public void start(){
        boolean keepRunning = true;
        System.out.println("\n----------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        while(keepRunning){
            Scanner scanner=new Scanner(System.in);
            int selection = 0;
            try {
                selection=Integer.parseInt(scanner.next());
                if(selection>=1&&selection<=5){
                    switch (selection){
                        case 1:
                            seeAllCustomers();
                            break;
                        case 2:
                            seeAllRooms();
                            break;
                        case 3:
                            seeAllReservations();
                            break;
                        case 4:
                            addARoom();
                            break;
                        case 5:
                            keepRunning=false;
                            break;
                    }
                }else{
                    System.out.println("Sorry,please enter a number between 1 and 5");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Sorry,please enter a number between 1 and 5");
            }
        }
    }
    public void seeAllCustomers(){
        Collection<Customer> customers=adminResource.getAllCustomers();
        for(Customer customer:customers){
            System.out.println(customer);
        }
    }
    public void seeAllRooms(){
        Collection<IRoom> rooms=adminResource.getAllRooms();
        for(IRoom room:rooms){
            System.out.println(room);
        }
    }
    public void seeAllReservations(){
        adminResource.displayAllReservations();
    }
    public void addARoom(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter room number: ");
        String newRoomNumber=input.next();
        System.out.println("Enter room price: ");
        Double newPrice=Double.parseDouble(input.next());
        RoomType newRoomType = null;
        while (newRoomType==null){
            System.out.println("Enter room room type: ");
            String type=input.next();
            if("single".equals(type)){
                newRoomType= RoomType.SINGLE;
            }else if("double".equals(type)){
                newRoomType= RoomType.DOUBLE;
            }else{
                System.out.println("Sorry,we don't have this roomtype");
                System.out.println("Please enter again");
            }
        }
        IRoom room=new Room(newRoomNumber,newPrice,newRoomType);
        List<IRoom> rooms=new ArrayList<>();
        rooms.add(room);
        adminResource.addRoom(rooms);
        System.out.println("Would you like to add another rooms?");
        String option=input.next();
        if ("yes".equals(option)){
            addARoom();
        }else if ("no".equals(option)){
            return;
        }else{
            while (!"yes".equals(option)&&!"no".equals(option)) {
                System.out.println("Please enter yes or no");
                option=input.next();
                if ("yes".equals(option)){
                    addARoom();
                }else if ("no".equals(option)) {
                    return;
                }
            }
        }
    }

}
