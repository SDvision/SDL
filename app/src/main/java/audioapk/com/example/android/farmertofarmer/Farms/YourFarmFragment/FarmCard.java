
package audioapk.com.example.android.farmertofarmer.Farms.YourFarmFragment;

class FarmCard {

    private String title;
    private String info;
    private final int imageResource;



    FarmCard(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
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
