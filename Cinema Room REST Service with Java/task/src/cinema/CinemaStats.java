package cinema;

public class CinemaStats {
    private int income;
    private int available;
    private int purchased;

    public CinemaStats() {
        this.income = 0;
        this.available = 81;
        this.purchased = 0;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    public void addPurchase(int income) {
        this.income += income;
        this.purchased ++;
        this.available --;
    }

    public void removePurchase(int income) {
        this.income -= income;
        this.purchased --;
        this.available ++;
    }
}
