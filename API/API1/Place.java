package mainActivity.API.API1;
public class Place {

    private String name;
    private String vicinity;
    private float rating;

    private String place_id;

    public String getId() {
        return place_id;
    }
    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public double getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
