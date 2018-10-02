
package audioapk.com.example.android.farmertofarmer.Farms.YourFarmFragment;

class FarmCard {

    private String title;
    private final int imageResource;
    private double landArea;
    private String day;

    private int databaseId;


    FarmCard(String title, int imageResource, double landArea, String day) {
        this.title = title;
        this.imageResource = imageResource;
        this.landArea = landArea;
        this.day = day;
    }

    void setDatabaseId(int databaseId) {
        this.databaseId = databaseId;
    }

    int getDatabaseId() {
        return databaseId;
    }

    double getLandArea() {
        return landArea;
    }

    String getDay() {
        return day;
    }

    String getTitle() {
        return title;
    }

    int getImageResource() {
        return imageResource;
    }


}
