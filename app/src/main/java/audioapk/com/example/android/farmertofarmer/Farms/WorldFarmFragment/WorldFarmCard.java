
package audioapk.com.example.android.farmertofarmer.Farms.WorldFarmFragment;

class WorldFarmCard {

    private String title;
    private final int imageResource;

    public WorldFarmCard(String title,int imageResource) {
        this.title = title;
        this.imageResource = imageResource;
    }

    String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }

}
