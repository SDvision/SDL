package audioapk.com.example.android.farmertofarmer.Processes.WorldProcessesFragment;

public class WorldProcessCard {

    private String land,date,processDB;
    private int profit;

    public WorldProcessCard(String land, String date, String processDB, int profit) {
        this.land = land;
        this.date = date;
        this.processDB = processDB;
        this.profit = profit;
    }

    public String getLand() {
        return land;
    }

    public String getDate() {
        return date;
    }

    public String getProcessDB() {
        return processDB;
    }

    public int getProfit() {
        return profit;
    }

}
