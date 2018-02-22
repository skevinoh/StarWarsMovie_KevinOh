package com.example.skevinoh.starwarsmovies_kevinoh;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by skevinoh on 2/20/18.
 */

public class MovieDetailActivity extends AppCompatActivity {

    public TextView titleTextView;
    public TextView descriptionTextView;
    public ImageView thumbnailImageView;
    RadioGroup radioGroup;
    private Button submitButton;

    private boolean alreadySeen;
    private boolean wantToSee;
    private boolean doNotLike;

    String movieTitleIntent;
    Movie mMovie;

    // override onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (!getIntent().hasExtra("title")) finish();

        movieTitleIntent = getIntent().getStringExtra("title");

        final ArrayList<Movie> MovieList = Movie.getMoviesFromFile("Movies.json", this);

        for (Movie m : MovieList) {
            if (m.title.equals(movieTitleIntent)) {
                mMovie = m;
                break;
            }
        }

        if (mMovie == null) finish();

        titleTextView = findViewById(R.id.movie_list_title2);
        descriptionTextView = findViewById(R.id.movie_list_description2);
        thumbnailImageView = findViewById(R.id.movie_list_image);

        titleTextView.setText(mMovie.title);
        titleTextView.setTextSize(30);
        descriptionTextView.setText(mMovie.description);
        Picasso.with(this).load(mMovie.poster).into(thumbnailImageView);

        radioGroup = findViewById(R.id.radio_group);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent radioButtonIntent = new Intent();
                radioButtonIntent.putExtra("radio_button_already_seen", alreadySeen);
                radioButtonIntent.putExtra("radio_button_want_to_see", wantToSee);
                radioButtonIntent.putExtra("radio_button_do_not_like", doNotLike);

                setResult(RESULT_OK, radioButtonIntent);
                finish();
            }
        });
    }
    public void alreadySeen(View view) {
        alreadySeen = ((RadioButton) view).isChecked();
    }
    public void wantToSee(View view){
        wantToSee = ((RadioButton) view).isChecked();
    }
    public void doNotLike(View view){
        doNotLike = ((RadioButton) view).isChecked();
    }
}
