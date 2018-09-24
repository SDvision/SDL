
package audioapk.com.example.android.farmertofarmer.Farms.YourFarmFragment;

class FarmCard {

    private String title;
    private String info;
    private final int imageResource;
    private int landArea;
    private int[] day;


    FarmCard(String title, String info, int imageResource, int landArea, int[] day) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
        this.landArea = landArea;
        this.day = day;
    }

    public int getLandArea() {
        return landArea;
    }

    public int[] getDay() {
        return day;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }


}
