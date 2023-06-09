package mainActivity.API;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.tfg.marfol.R;

import java.util.List;

import mainActivity.API.API1.ApiClient;
import mainActivity.API.API1.Place;
import mainActivity.API.API1.PlacesAdapter;
import mainActivity.API.API1.PlacesApiService;
import mainActivity.API.API1.PlacesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRestaurante extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlacesAdapter placesAdapter;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_api);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        placesAdapter = new PlacesAdapter();
        recyclerView.setAdapter(placesAdapter);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        fetchPlaces();
        placesAdapter.setOnItemClickListener(new PlacesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Place place) {
                // Obtener los datos del lugar seleccionado
                String place_id = place.getId();
                String placeName = place.getName();
                float placeRating = (float) place.getRating();


                // Crear un Intent para iniciar la nueva actividad
                Intent intent = new Intent(MainRestaurante.this, MainDetalle.class);
                intent.putExtra("placeId", place_id);
                intent.putExtra("placeName", placeName);
                intent.putExtra("placeRating", placeRating);
                startActivity(intent);
            }
        });

    }

    private void fetchPlaces() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            String locationString = latitude + "," + longitude;

                            PlacesApiService placesApiService = ApiClient.getClient().create(PlacesApiService.class);
                            Call<PlacesResponse> call = placesApiService.getNearbyPlaces(
                                    locationString,
                                    5000,
                                    "restaurant",
                                    "AIzaSyC1JbQNg7WkFD4zGdVr1Y-RkaWtluXStKE"
                            );

                            call.enqueue(new Callback<PlacesResponse>() {
                                @Override
                                public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {
                                    if (response.isSuccessful()) {
                                        List<Place> places = response.body().getResults();
                                        placesAdapter.setPlaces(places);
                                    }
                                }

                                @Override
                                public void onFailure(Call<PlacesResponse> call, Throwable t) {
                                    // Manejar el error de la solicitud
                                }
                            });
                        }
                    });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchPlaces();
            }
        }
    }
}