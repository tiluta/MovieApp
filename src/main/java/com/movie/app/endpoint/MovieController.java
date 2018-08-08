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

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getMovie(@PathVariable("id") long id){
        Movie movie = movieService.findById(id);
        if( movie == null){
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);

        }
        return  new ResponseEntity<Movie>(movie, HttpStatus.OK);

    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public ResponseEntity<Void> createMovie(@RequestBody Movie movie, UriComponentsBuilder uriComponentsBuilder){

        if(movieService.findById(movie.getId())!= null){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        else
        {
            movieService.create(movie);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponentsBuilder.path("movie/id").buildAndExpand(movie.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }

    }

    @RequestMapping(value = "/movie", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateMovie(@RequestBody Movie movie){

        if(movieService.findById(movie.getId())!= null)
        {
            movieService.update(movie);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/movie", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMovie(@RequestBody Movie movie){

        if(movieService.findById(movie.getId())!= null)
        {
            movieService.delete(movie);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
