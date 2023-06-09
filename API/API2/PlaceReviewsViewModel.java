package mainActivity.API.API2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceReviewsViewModel extends ViewModel {
    private MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
    private PlacesRepository repository;

    public PlaceReviewsViewModel() {
        repository = new PlacesRepository();
    }

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public void fetchPlaceReviews(String placeId) {
        repository.getPlaceReviews(placeId, new Callback<PlaceDetailsResponse>() {
            @Override
            public void onResponse(Call<PlaceDetailsResponse> call, Response<PlaceDetailsResponse> response) {
                if (response.isSuccessful()) {
                    PlaceDetailsResponse placeDetailsResponse = response.body();
                    List<Review> reviewList = placeDetailsResponse.getResult().getReviews();
                    reviews.setValue(reviewList);
                }
            }

            @Override
            public void onFailure(Call<PlaceDetailsResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }
}