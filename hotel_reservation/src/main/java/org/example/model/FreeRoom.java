package org.example.model;

public class FreeRoom extends Room{
    public FreeRoom() {
        this.setPrice(0.0);
    }
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + this.getRoomNumber() + '\'' +
                ", price=" + this.getRoomPrice() +
                ", enumeration=" + this.getRoomType() +
                ", free="+this.isFree()+
                '}';
    }
}
