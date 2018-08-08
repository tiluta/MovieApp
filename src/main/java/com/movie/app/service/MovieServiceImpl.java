package com.movie.app.service;

import com.movie.app.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

    private static final AtomicLong counter = new AtomicLong();
    public static List<Movie> movies;

    static{
        movies = populateDummyMovies();
    }
     private static List<Movie> populateDummyMovies(){
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(counter.incrementAndGet(),"Gossip Girl", "Serena", "comedy"));
        movies.add(new Movie(counter.incrementAndGet(),"Vampire Diaries", "Elena", "romance"));
        movies.add(new Movie(counter.incrementAndGet(),"Grey's Anatomy", "Dr. Grey", "documentary"));
        movies.add(new Movie(counter.incrementAndGet(),"Last house on the left", "Niculeasa", "horror"));

        return  movies;
    }

    @Override
    public Movie findById(long id) {
        for(Movie movie: movies){
            if(movie.getId() == id){
                return  movie;
            }
        }
        return null;
    }

    @Override
    public void create(Movie movie){
        movies.add(movie);

    }

    @Override
    public void update(Movie movie){
        Movie movieToBeUpdated = findById(movie.getId());
        if(movieToBeUpdated != null) {
            movieToBeUpdated.setName(movie.getName());
            movieToBeUpdated.setDirector(movie.getDirector());
            movieToBeUpdated.setCategory(movie.getCategory());
        }

    }

    @Override
    public void delete(Movie movie){
        Movie movieToBeDeleted = findById(movie.getId());
        movies.remove(movieToBeDeleted);

    }

}
