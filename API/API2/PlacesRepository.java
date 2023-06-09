package mainActivity.API.API2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class PlacesRepository {
    private PlacesApiService apiService;

    public PlacesRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(PlacesApiService.class);
    }

    public void getPlaceReviews(String placeId, Callback<PlaceDetailsResponse> callback) {
        Call<PlaceDetailsResponse> call = apiService.getPlaceDetails(
                placeId,
                "reviews",
                "AIzaSyC1JbQNg7WkFD4zGdVr1Y-RkaWtluXStKE"
        );
        call.enqueue(callback);
    }
}


