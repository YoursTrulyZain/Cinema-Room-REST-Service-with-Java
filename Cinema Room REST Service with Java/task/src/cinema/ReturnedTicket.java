package cinema;

public class ReturnedTicket {
    private SeatInfo ticket;

    public ReturnedTicket(SeatInfo ticket) {
        this.ticket = ticket;
    }

    public SeatInfo getTicket() {
        return ticket;
    }

    public void setTicket(SeatInfo ticket) {
        this.ticket = ticket;
    }
}
