package com.example.skevinoh.starwarsmovies_kevinoh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        // data to display
        final ArrayList<Movie> movieList = Movie.getMoviesFromFile("movies.json", this);

        // create the adapter
        MovieAdapter adapter = new MovieAdapter(this, movieList);

        // find the listview in the layout
        // set the adapter to listview
        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);

        // 1. each row should be clickable
        // when the row is clicked,
        // the intent is created and send

        mListView.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){
                Movie selectedMovie = movieList.get(position);

                // create my intent package
                // add all the information needed for detail page
                // startActivity with that intent

                //explicit
                // from, to
                resultTextView = view.findViewById(R.id.movie_list_has_seen);
                System.out.println("I'm doing this");

                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);
                // put title and instruction URL
                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("poster", selectedMovie.poster);
                detailIntent.putExtra("description", selectedMovie.description);

                launchActivity(detailIntent);

            }
        });

    }

    private void launchActivity(Intent intent) { startActivityForResult(intent, 1);}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                boolean already_seen_button = data.getBooleanExtra("radio_button_already_seen", false);
                boolean want_to_see_button = data.getBooleanExtra("radio_button_want_to_see", false);
                boolean do_not_like_button = data.getBooleanExtra("radio_button_do_not_like", false);

                if(already_seen_button) {
                    resultTextView.setText("Already seen");
                    System.out.println("I'm doing this");
                }

                else if(want_to_see_button) {
                    resultTextView.setText("Want to see");
                }
                else if(do_not_like_button) {
                    resultTextView.setText("Do not Like");
                }
            }
        }
    }
}
