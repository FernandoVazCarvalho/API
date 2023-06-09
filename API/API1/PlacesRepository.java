package mainActivity.API.API1;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacesRepository {
    private PlacesApiService placesApiService;

    public PlacesRepository() {
        placesApiService = ApiClient.getClient().create(PlacesApiService.class);
    }

    public LiveData<List<Place>> getNearbyPlaces(String location, int radius, String type, String apiKey) {
        MutableLiveData<List<Place>> placesLiveData = new MutableLiveData<>();

        Call<PlacesResponse> call = placesApiService.getNearbyPlaces(location, radius, type, apiKey);
        call.enqueue(new Callback<PlacesResponse>() {
            @Override
            public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {
                if (response.isSuccessful()) {
                    List<Place> places = response.body().getResults();
                    placesLiveData.setValue(places);
                } else {
                    // Manejar la respuesta de error
                }
            }

            @Override
            public void onFailure(Call<PlacesResponse> call, Throwable t) {
                // Manejar el error de la solicitud
            }
        });

        return placesLiveData;
    }
}