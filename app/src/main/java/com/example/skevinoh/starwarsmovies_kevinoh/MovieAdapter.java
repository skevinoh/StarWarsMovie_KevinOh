package com.example.skevinoh.starwarsmovies_kevinoh;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by skevinoh on 2/20/18.
 */

public class MovieAdapter extends BaseAdapter {
    // adapter takes the app itself and a list of data to display
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;

    // constructor
    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList){

        // initialize instances variables
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    // methods
    // a list of methods we need to override

    // gives you the number of recipes in the data source
    @Override
    public int getCount(){
        return mMovieList.size();
    }

    // returns the item at specific position in the data source

    @Override
    public Object getItem(int position){
        return mMovieList.get(position);
    }

    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        // check if the view already exists
        // if yes, you don't need to inflate and findViewbyID again
        if (convertView == null){
            // inflate
            convertView = mInflater.inflate(R.layout.list_item_movies, parent, false);
            // add the views to the holder
            holder = new ViewHolder();
            // views
            holder.titleTextView = convertView.findViewById(R.id.movie_list_title);
            holder.descriptionTextView = convertView.findViewById(R.id.movie_list_description);
            holder.thumbnailImageView = convertView.findViewById(R.id.imageView);
            holder.charactersTextView = convertView.findViewById(R.id.movie_list_characters);
            holder.hasSeenTextView = convertView.findViewById(R.id.movie_list_has_seen);
            // add the holder to the view
            // for future use
            convertView.setTag(holder);
        }
        else{
            // get the view holder from converview
            holder = (ViewHolder)convertView.getTag();
        }
        // get relavate subview of the row view
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;
        TextView charactersTextView = holder.charactersTextView;


        // get corresonpinding recipe for each row
        Movie movie = (Movie)getItem(position);


        // update the row view's textviews and imageview to display the information

        // titleTextView
        titleTextView.setText(movie.title);
        titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        titleTextView.setTextSize(20);

        // servingTextView
        descriptionTextView.setText(movie.description + " servings");
        descriptionTextView.setTextSize(9);


        // charactersTextView
        charactersTextView.setText(movie.mainCharacters.get(0) + ", " + movie.mainCharacters.get(1) + ", " + movie.mainCharacters.get(2));
        charactersTextView.setTextSize(11);
        charactersTextView.setTextColor(ContextCompat.getColor(mContext, R.color.green));

        // hasSeenTextView
        String hasSeenText = movie.alreadySeen != null ? movie.alreadySeen : "Has seen?";
        holder.hasSeenTextView.setText(hasSeenText);


        // imageView
        // use Picasso library to load image from the image url
        Picasso.with(mContext).load(movie.poster).into(thumbnailImageView);
        return convertView;
    }

    // viewHolder
    // is used to customize what you want to put into the view
    // it depends on the layout design of your row
    // this will be a private static class you have to define
    private static class ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;
        public ImageView thumbnailImageView;
        public TextView charactersTextView;
        public TextView hasSeenTextView;
    }


    // intent is used to pass information between activities
    // intent -> pacakge
    // sender, receiver

}

