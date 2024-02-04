package cinema;

import java.util.ArrayList;
import java.util.List;

public class Hall {
    private int rows;
    private int columns;
    private List<Seat> seats;

    public Hall() {
        this.rows = 9;
        this.columns = 9;
        this.seats = generateSeats(rows, columns);
    }

    private List<Seat> generateSeats(int rows, int columns) {
        List<Seat> tempList = new ArrayList<>();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                tempList.add(new Seat(i + 1, j + 1));
            }
        }
        return tempList;
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

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
