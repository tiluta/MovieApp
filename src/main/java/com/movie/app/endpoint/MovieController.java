package com.movie.app.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public ResponseEntity<String> getMovie() {
        return new ResponseEntity<String>("Hello world!!", HttpStatus.OK);
    }
}
