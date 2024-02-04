package cinema;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.UUID;

public class Receipt {

    private UUID token;
    private SeatInfo ticket;

    public Receipt(SeatInfo ticket) {
        this.token = UUID.randomUUID();
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }
    public void setToken(UUID token) {
        this.token = token;
    }
    public SeatInfo getTicket() {
        return ticket;
    }
    public void setTicket(SeatInfo ticket) {
        this.ticket = ticket;
    }
}
