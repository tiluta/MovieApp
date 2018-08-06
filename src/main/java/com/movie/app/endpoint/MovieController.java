package com.movie.app.endpoint;

import com.movie.app.model.Movie;
import com.movie.app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public class MovieController {

    /**
     *
     */


    @Autowired
    private MovieService movieService;

//    @RequestMapping(value = "/movie", method = RequestMethod.GET)
//    public ResponseEntity<String> getMovie() {
//        return new ResponseEntity<String>("Hello world!!", HttpStatus.OK);
//    }

    /************ Retrieve single movie from a list ******************/
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getMovie(@PathVariable("id") long id){
        System.out.println("Fetching Movie with id" + id);
        Movie movie = movieService.findById(id);
        if( movie == null){
            System.out.println("Movie with id " + id + "not found");
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);

        }
        return  new ResponseEntity<Movie>(movie, HttpStatus.OK);

    }

    @RequestMapping(value = "/movie/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMovie(@RequestBody Movie movie, UriComponentsBuilder ucBuilder){
        System.out.println("New movie" + movie.getName());

        if(movieService.isMovieExist(movie)){
            System.out.println("A movie with " + movie.getName() + "already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        movieService.saveMovie(movie);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("movie/id").buildAndExpand(movie.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/movie/", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateMovie(@RequestBody Movie movie){
        System.out.println("Update move" + movie.getName());

        if(movieService.isMovieExist(movie))
        {
            movieService.updateExistentMovie(movie);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else
        {
            System.out.println("A movie with " + movie.getName() + "doesn't exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/movie/", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMovie(@RequestBody Movie movie){
        System.out.println("Update move" + movie.getName());

        if(movieService.isMovieExist(movie))
        {
            movieService.deleteExistentMovie(movie);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else
        {
            System.out.println("A movie with " + movie.getName() + "cannot be deleted");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }
}
