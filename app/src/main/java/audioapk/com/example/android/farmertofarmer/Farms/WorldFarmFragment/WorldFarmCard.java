
package audioapk.com.example.android.farmertofarmer.Farms.WorldFarmFragment;

class WorldFarmCard {

    private String title;
    private final int imageResource;

    WorldFarmCard(String title,int imageResource) {
        this.title = title;
        this.imageResource = imageResource;
    }

    String getTitle() {
        return title;
    }

    int getImageResource() {
        return imageResource;
    }
}
