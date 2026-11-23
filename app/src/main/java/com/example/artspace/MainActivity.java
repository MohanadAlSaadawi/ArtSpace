package com.example.artspace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_INDEX = "current_artwork_index";

    static final String EXTRA_TITLE = "artwork_title";
    static final String EXTRA_ARTIST = "artwork_artist";
    static final String EXTRA_YEAR = "artwork_year";

    private static final String[] TITLES = {
            "Dune Sunrise", "Blue Tide", "City Windows",
            "Mountain Echo", "Orbit Study", "Garden Tiles"
    };

    private static final String ARTIST = "ArtSpace Studio";
    private static final int YEAR = 2026;

    private int currentIndex = 0;

    private ImageView artworkImage;
    private TextView artworkCounter;
    private TextView artworkTitle;
    private TextView artworkArtistYear;
    private Button previousButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        artworkImage = findViewById(R.id.artworkImage);
        artworkCounter = findViewById(R.id.artworkCounter);
        artworkTitle = findViewById(R.id.artworkTitle);
        artworkArtistYear = findViewById(R.id.artworkArtistYear);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(STATE_INDEX, 0);
        }

        previousButton.setOnClickListener(view -> {
            if (currentIndex > 0) {
                currentIndex--;
                showArtwork();
            }
        });

        nextButton.setOnClickListener(view -> {
            if (currentIndex < TITLES.length - 1) {
                currentIndex++;
                showArtwork();
            }
        });

        artworkImage.setOnClickListener(view -> openDetails());

        showArtwork();
    }

    private void showArtwork() {
        String title = TITLES[currentIndex];

        artworkImage.setImageBitmap(GalleryArt.draw(currentIndex));
        artworkImage.setContentDescription("View details for " + title);
        artworkTitle.setText(title);
        artworkArtistYear.setText(ARTIST + " (" + YEAR + ")");
        artworkCounter.setText((currentIndex + 1) + " / " + TITLES.length);

        previousButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex < TITLES.length - 1);
    }

    private void openDetails() {
        Intent intent = new Intent(this, ArtworkDetailsActivity.class);
        intent.putExtra(EXTRA_TITLE, TITLES[currentIndex]);
        intent.putExtra(EXTRA_ARTIST, ARTIST);
        intent.putExtra(EXTRA_YEAR, YEAR);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_INDEX, currentIndex);
    }
}