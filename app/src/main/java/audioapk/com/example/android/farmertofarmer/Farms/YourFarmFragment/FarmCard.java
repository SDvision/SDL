
package audioapk.com.example.android.farmertofarmer.Farms.YourFarmFragment;

class FarmCard {

    private String title;
    private final int imageResource;
    private double landArea;
    private String day;


    FarmCard(String title, int imageResource, double landArea, String day) {
        this.title = title;
        this.imageResource = imageResource;
        this.landArea = landArea;
        this.day = day;
    }

    public double getLandArea() {
        return landArea;
    }

    public String getDay() {
        return day;
    }

    String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }


}
