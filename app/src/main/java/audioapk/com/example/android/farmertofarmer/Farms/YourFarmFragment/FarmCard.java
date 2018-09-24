
package audioapk.com.example.android.farmertofarmer.Farms.YourFarmFragment;

class FarmCard {

    private String title;
    private final int imageResource;
    private double landArea;
    private int[] day;


    FarmCard(String title, int imageResource, double landArea, int[] day) {
        this.title = title;
        this.imageResource = imageResource;
        this.landArea = landArea;
        this.day = day;
    }

    public double getLandArea() {
        return landArea;
    }

    public int[] getDay() {
        return day;
    }

    String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }


}
