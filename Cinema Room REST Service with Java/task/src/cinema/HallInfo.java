package cinema;

import java.util.List;

public class HallInfo {

    private int rows;
    private int columns;
    private List<SeatInfo> seats;

    public HallInfo(int rows, int columns, List<Seat> allSeats) {
        this.rows = rows;
        this.columns = columns;
        this.seats = allSeats.stream().filter(seat -> !seat.isBooked()).map(seat -> new SeatInfo(seat.getRow(), seat.getColumn(), seat.getPrice())).toList();
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<SeatInfo> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatInfo> seats) {
        this.seats = seats;
    }
}
