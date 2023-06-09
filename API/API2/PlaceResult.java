package mainActivity.API.API2;

import java.util.List;

public class PlaceResult {
    private List<Review> reviews;

    public PlaceResult(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
