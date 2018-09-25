package audioapk.com.example.android.farmertofarmer.Processes.YourProcessesFragment;

public class ProcessCard {

    private String title;
    private String date;
    private String description;


    public ProcessCard(String title, String date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }


}
