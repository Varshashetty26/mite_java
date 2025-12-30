package model;
//for room details
public class Room implements IRoom{
    private String roomNumber;
    private Double price;
    private RoomType roomType;
    public Room(String roomNumber,Double price,RoomType roomType){
        this.roomNumber=roomNumber;
        this.price=price;
        this.roomType=roomType;
    }
    public String getRoomNumber(){
        return roomNumber;
    }
    public Double getRoomPrice(){
        return price;
    }
    public RoomType getRoomType(){
        return roomType;
    }
    public boolean isFree(){
        return price==0.0;
    }
    //to string method
    @Override
    public String toString() {
        return "--------------------------------:\n" +
                "Room Number= " + roomNumber + "\n" +
                "price= " + price +
                "\n roomType= " + roomType;
    }
}
