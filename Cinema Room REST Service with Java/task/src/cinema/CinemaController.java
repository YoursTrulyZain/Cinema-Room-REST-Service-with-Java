package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class CinemaController {

    Hall hall = new Hall();
    Map<UUID, Receipt> receipts = new HashMap<>();
    CinemaStats cinemaStats = new CinemaStats();

    @GetMapping("/seats")
    public HallInfo getSeatInfo() {
        return new HallInfo(hall.getRows(), hall.getColumns(), hall.getSeats());
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam String password) {
        if(!"super_secret".equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("The password is wrong!"));
        }
        return ResponseEntity.ok().body(cinemaStats);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseSeat(@RequestBody userSeat userSeat) {
        int row = userSeat.getRow();
        int column = userSeat.getColumn();

        if(row < 1 | row > 9 | column < 1 | column > 9) {
            return ResponseEntity.badRequest().body(new ErrorResponse("The number of a row or a column is out of bounds!"));
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number of a row or a column is out of bounds!");
        }
        SeatInfo resultSeatInfo = null;
        Receipt customerReceipt = null;

        for(Seat seat : hall.getSeats()) {
            if(seat.getRow() == row && seat.getColumn() == column) {
                if(seat.isBooked()) {
                    return ResponseEntity.badRequest().body(new ErrorResponse("The ticket has been already purchased!"));
//                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The ticket has been already purchased!");
                }else {
                    seat.setBooked(true);
                    resultSeatInfo = new SeatInfo(seat.getRow(), seat.getColumn(), seat.getPrice());
                    customerReceipt = new Receipt(resultSeatInfo);
                    receipts.put(customerReceipt.getToken(), customerReceipt);
                    cinemaStats.addPurchase(resultSeatInfo.getPrice());
                }
            }
        }
        return ResponseEntity.ok().body(customerReceipt);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnTicket(@RequestBody CustomerToken token) {

        UUID userToken = UUID.fromString(token.getToken());

        if (!receipts.containsKey(userToken)) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Wrong token!"));
        }

        Receipt returnReceipt = receipts.get(userToken);
        SeatInfo returnTicket = returnReceipt.getTicket();

        hall.getSeats().stream()
                .filter(seat -> seat.getRow() == returnTicket.getRow() && seat.getColumn() == returnTicket.getColumn())
                .findFirst()
                .ifPresent(seat -> seat.setBooked(false));
        cinemaStats.removePurchase(returnTicket.getPrice());
        receipts.remove(userToken);

        return ResponseEntity.ok().body(new ReturnedTicket(returnTicket));
    }
}
