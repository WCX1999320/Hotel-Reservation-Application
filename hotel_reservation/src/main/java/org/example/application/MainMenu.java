package org.example.application;

import org.example.api.AdminResource;
import org.example.api.HotelResource;
import org.example.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {
    private static MainMenu mainMenu;
    private AdminResource adminResource;
    private HotelResource hotelResource;
    private boolean keepRunning;
    private MainMenu(){
        adminResource=AdminResource.getInstance();
        hotelResource=HotelResource.getInstance();
    }
    //static reference for MainMenu class
    public static MainMenu getInstance(){
        if (mainMenu==null){
            mainMenu=new MainMenu();
        }
        return mainMenu;
    }
    //start using MainMenu
    public void start(){
        keepRunning=true;
        selectOptions();
    }
    //Exit MainMenu
    public void exit(){
        keepRunning=false;
        System.out.println("You've exited this system successfully");
    }
    public void selectOptions(){
        while (keepRunning){
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            Scanner scanner=new Scanner(System.in);
            int selection = 0;
            try {
                selection=Integer.parseInt(scanner.next());
                if(selection>=1&&selection<=5){
                    switch (selection){
                        case 1:
                            findAndReserveARoom();
                            break;
                        case 2:
                            seeMyReservations(new Scanner(System.in));
                            break;
                        case 3:
                            createAnAccount(new Scanner(System.in));
                            break;
                        case 4:
                            AdminMenu adminMenu=AdminMenu.getInstance();
                            adminMenu.start();
                            break;
                        case 5:
                            this.exit();
                    }
                }else{
                    System.out.println("Sorry,please enter a number between 1 and 5");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Sorry,please enter a number between 1 and 5");
            }
        }
    }
    //find and reserve a room
    public void findAndReserveARoom() {
        Date checkIn = null;
        Date checkOut = null;
        String time = null;
        Scanner scanner = null;
        SimpleDateFormat format = null;
        while (checkIn == null) {
            System.out.println("Please enter your checkIn date(ep:2013-04-17):");
            scanner = new Scanner(System.in);
            time = scanner.nextLine();
            format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                checkIn = format.parse(time);
            } catch (ParseException e) {
                System.out.println("Illegal input");
            }
        }
        while (checkOut == null) {
            System.out.println("Please enter your checkOut date(ep:2013-04-17):");
            time = scanner.nextLine();
            format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                checkOut = format.parse(time);
            } catch (ParseException e) {
                System.out.println("Illegal input");
            }
        }
        List<IRoom> availableRooms = (List<IRoom>) hotelResource.findARoom(checkIn, checkOut);
        if (availableRooms.size()==0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkIn);
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
            checkIn = calendar.getTime();
            calendar.setTime(checkOut);
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
            checkOut = calendar.getTime();
            search(checkIn, checkOut);
        }else{
            for (IRoom room : availableRooms) {
                System.out.println("Free Room:" + room);
            }
            String option=null;
            while (!"yes".equals(option)&&!"no".equals(option)){
                System.out.println("Would you like to reserve a room,please enter yes or no");
                Scanner scanner1=new Scanner(System.in);
                option=scanner1.next();
                if ("yes".equals(option)){
                    book(checkIn,checkOut);
                }else if("no".equals(option)){
                    return;
                }else{
                    System.out.println("Please enter yes or no");
                }
            }
        }
    }
    // reserve a room
    public void book(Date checkIn,Date checkOut){
        System.out.println("Do you have an account,please enter yes or no?");
        String option=null;
        Scanner scanner=null;
        while (!"yes".equals(option)&&!"no".equals(option)){
            scanner=new Scanner(System.in);
            option=scanner.next();
            if ("yes".equals(option)){

            }else if("no".equals(option)){
                createAnAccount(new Scanner(System.in));
            }else{
                System.out.println("Please enter yes or no");
            }
        }
        System.out.println("Please enter your email");
        String email=scanner.next();
        Customer customer=hotelResource.getCustomer(email);
        if(customer==null){
            System.out.println("You are not currently registering with us");
        }
        System.out.println("Please enter a roomNumber");
        String roomNum=scanner.next();
        IRoom room=hotelResource.getRoom(roomNum);
        if (room==null){
            System.out.println("We don't have this room");
        }
        hotelResource.bookARoom(customer,room,checkIn,checkOut);
    }
    public void search(Date checkIn, Date checkOut){
        List<IRoom> availableRooms=(List<IRoom>)hotelResource.findARoom(checkIn,checkOut);
        if(availableRooms.size()==0){
            System.out.println("Sorry,there is no available room even in the next seven days");
            System.out.println("Please choose another checkIn and checkOut");
            return;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Sorry,there is no available room this weeks");
        System.out.println("I have some recommendations for you from"+sdf.format(checkIn)+"to"+sdf.format(checkOut));
        for(IRoom room:availableRooms){
            System.out.println("Recommended Room:"+room);
        }
        String option=null;
        while (!"yes".equals(option)&&!"no".equals(option)){
            System.out.println("Would you like to reserve a room,please enter yes or no");
            Scanner scanner1=new Scanner(System.in);
            option=scanner1.next();
            if ("yes".equals(option)){
                book(checkIn,checkOut);
            }else if("no".equals(option)){
                return;
            }else{
                System.out.println("Please enter yes or no");
            }
        }
    }
    //see my reservations
    public void seeMyReservations(Scanner input){
        System.out.println("--------------------");
        System.out.println("To view your reservations,you need to enter your email");
        String email=input.next();
        List<Reservation> reservations=null;
        try {
            reservations=(List<Reservation>) hotelResource.getCustomerReservations(email);
            for(Reservation reservation:reservations){
                System.out.println(reservation);
            }
        }catch (Exception e){
            System.out.println("You haven't reserve any rooms");
        }
    }
    //create an account
    public void createAnAccount(Scanner input){
        System.out.println("--------------------");
        System.out.println("To view your reservations,you need to enter all your personal information");
        System.out.println("please enter your firstname");
        String firstName=input.next();
        System.out.println("please enter your lastname");
        String lastName=input.next();
        System.out.println("please enter your email");
        String email=input.next();
        if(hotelResource.getCustomer(email)==null){
            hotelResource.createACustomer(firstName,lastName,email);
        }else{
            System.out.println("You've created an account");
        }
    }
}
