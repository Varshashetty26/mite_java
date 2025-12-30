package model;

public class FreeRoom extends Room{
   //constructor-set price to zero
    public FreeRoom(String roomNumber,RoomType roomType){
        super(roomNumber,0.0,roomType);
    }

    @Override
    public String toString() {
        return "Free Room: "+super.toString();
    }
}
