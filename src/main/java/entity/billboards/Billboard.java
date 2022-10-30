package entity.billboards;

import enums.State;

public class Billboard {
    private long serialNumber;
    private String location;
    private String dimension;
    private State state;
    private int pricePerHr;
    private  BookingDetails bookingDetails;

    public Billboard() {
    }

    public Billboard(long serialNumber, String location, String dimension, State state, int pricePerHr, BookingDetails bookingDetails) {
        this.serialNumber = serialNumber;
        this.location = location;
        this.dimension = dimension;
        this.state = state;
        this.pricePerHr = pricePerHr;
        this.bookingDetails = bookingDetails;
    }

    public Billboard(long serialNumber, String location, String dimension, State state, int pricePerHr) {
        this.serialNumber = serialNumber;
        this.location = location;
        this.dimension = dimension;
        this.state = state;
        this.pricePerHr = pricePerHr;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getPricePerHr() {
        return pricePerHr;
    }

    public void setPricePerHr(int pricePerHr) {
        this.pricePerHr = pricePerHr;
    }

    public BookingDetails getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    @Override
    public String toString() {
        return "\n***********************************************\n" +
                " >> Serial number: " + serialNumber +"\n" +
                " >> Location: " + location +"\n" +
                " >> Dimension: " + dimension +"\n" +
                " >> Price per hour: " + pricePerHr + "\n" +
                " >> State: " + state + "\n" +
                bookingDetails;
    }


    public String ToString() {
        return "\n***********************************************\n" +
                " >> Serial number: " + serialNumber +"\n" +
                " >> Location: " + location +"\n" +
                " >> Dimension: " + dimension +"\n" +
                " >> Price per hour: " + pricePerHr + "\n" +
                " >> State: " + state + "\n" +
                "***********************************************"
                ;
    }
}
