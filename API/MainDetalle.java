package mainActivity.API;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tfg.marfol.R;

import java.util.ArrayList;

import mainActivity.API.API2.PlaceReviewsViewModel;
import mainActivity.API.API2.ReviewAdapter;

public class MainDetalle extends AppCompatActivity {
    private PlaceReviewsViewModel viewModel;
    private ReviewAdapter reviewAdapter;

    private TextView placeIdTextView;
    private TextView placeNameTextView;
    private RatingBar placeRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_descripcion);

        placeNameTextView = findViewById(R.id.placeNameTextView);
        placeRatingBar = findViewById(R.id.placeRatingBar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewAdapter = new ReviewAdapter(new ArrayList<>());
        recyclerView.setAdapter(reviewAdapter);

        viewModel = new ViewModelProvider(this).get(PlaceReviewsViewModel.class);
        viewModel.getReviews().observe(this, reviews -> {
            reviewAdapter.setReviews(reviews);
            reviewAdapter.notifyDataSetChanged();
        });

        // Obtener los datos del Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String placeId = extras.getString("placeId");
            String placeName = extras.getString("placeName");
            float placeRating = extras.getFloat("placeRating");

            // Asignar los datos a los TextView y RatingBar
            placeIdTextView.setText(placeId);
            placeNameTextView.setText(placeName);
            placeRatingBar.setRating(placeRating);

            // Llamar al método fetchPlaceReviews con el placeId
            viewModel.fetchPlaceReviews(placeId);
        }
    }
}