package com.example.artspace;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ArtworkDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_artwork_details);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailsRoot), (view, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        Intent incoming = getIntent();
        String title = incoming.getStringExtra(MainActivity.EXTRA_TITLE);
        String artist = incoming.getStringExtra(MainActivity.EXTRA_ARTIST);
        int year = incoming.getIntExtra(MainActivity.EXTRA_YEAR, 0);

        TextView detailsText = findViewById(R.id.detailsText);
        detailsText.setText("Title: " + title
                + "\nArtist: " + artist
                + "\nCreation year: " + year);

        findViewById(R.id.backButton).setOnClickListener(view -> finish());

        findViewById(R.id.websiteButton).setOnClickListener(view -> {
            Uri website = Uri.parse("https://www.tate.org.uk/art");
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, website);

            try {
                startActivity(browserIntent);
            } catch (ActivityNotFoundException exception) {
                Toast.makeText(this, "No browser is available on this device.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}