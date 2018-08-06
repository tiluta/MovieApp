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
    public void saveMovie(Movie movie){
        movie.setId(counter.incrementAndGet());
        movies.add(movie);

    }

    @Override
    public Movie findByName(String name) {
        for(Movie movie: movies){
            if(movie.getName().equalsIgnoreCase(name)){
                return movie;
            }
        }
        return  null;
    }

    @Override
    public boolean isMovieExist(Movie movie) {
        return findByName(movie.getName())!=null;
    }

    @Override
    public void updateExistentMovie(Movie movie){
        Movie movieToBeUpdated = findById(movie.getId());
        if(movie.getId()!= 0)
        {
            movieToBeUpdated.setName(movie.getName());
            movieToBeUpdated.setDirector(movie.getDirector());
            movieToBeUpdated.setCategory(movie.getCategory());
        }

    }

    @Override
    public void deleteExistentMovie(Movie movie){
        Movie movieToBeDeleted = findById(movie.getId());
        movies.remove(movieToBeDeleted);

    }

}
