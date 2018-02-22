package com.example.skevinoh.starwarsmovies_kevinoh;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by skevinoh on 2/20/18.
 */

public class Movie {
    // instance variables or fields
    public String title;
    public String episodeNumber;
    public ArrayList mainCharacters = new ArrayList();
    public String description;
    public String poster;
    public String url;
    public String alreadySeen;
    // constructor
    // default

    // method
    // static methods that read the json file in and load into Recipe

    // static method that loads our recipes.json using the helper method
    // this method will return an array list of recipes constructed from the JSON
    // file
    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context){
        ArrayList<Movie> moviesList = new ArrayList<Movie>();


        // try to read from JSON file
        // get information by using the tags
        // construct a Recipe Object for each recipe in JSON
        // add the object to arraylist
        // return arraylist
        try{
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            // for loop to go through each recipe in your recipes array

            for (int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.description = movies.getJSONObject(i).getString("description");
                movie.poster = movies.getJSONObject(i).getString("poster");
                movie.url = movies.getJSONObject(i).getString("url");
                // add to arraylist
                moviesList.add(movie);

                JSONArray jsonCharacters = (JSONArray)movies.getJSONObject(i).get("main_characters");
                for (int j=0; j < jsonCharacters.length(); j++){
                    movie.mainCharacters.add(j,jsonCharacters.getString(j));
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesList;
    }


    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }


}
