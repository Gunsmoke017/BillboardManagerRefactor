package entity.billboards;

public class BookingDetails {
    private String customer;
    private String bookedDate;
    private String timeBooked;
    private int durationOfBooking;
    private String uploadedFile;
    private float price;

    public BookingDetails() {
    }

    public BookingDetails(String customer, String bookedDate, String timeBooked, int durationOfBooking, String uploadedFile, float price) {
        this.customer = customer;
        this.bookedDate = bookedDate;
        this.timeBooked = timeBooked;
        this.durationOfBooking = durationOfBooking;
        this.uploadedFile = uploadedFile;
        this.price = price;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getTimeBooked() {
        return timeBooked;
    }

    public void setTimeBooked(String timeBooked) {
        this.timeBooked = timeBooked;
    }

    public int getDurationOfBooking() {
        return durationOfBooking;
    }

    public void setDurationOfBooking(int durationOfBooking) {
        this.durationOfBooking = durationOfBooking;
    }

    public String getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(String uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return " >> Customer: " + customer +"\n" +
                " >> Rent Duration: " + durationOfBooking + "\n" +
                " >> Date Booked: " + bookedDate + "\n" +
                " >> Time Booked: " + timeBooked + "\n" +
                " >> File Uploaded: " + uploadedFile + "\n" +
                " >> Total Price: N" + price + "\n" +
                "***********************************************";
    }
}
