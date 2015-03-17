package com.example.app.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by naga on 2015/01/29.
 */
@RestController
class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public List<Comment> list() {
        return commentService.findAll();
    }

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Comment> save(@RequestBody Comment comment,
                                        UriComponentsBuilder uriBuilder) {

        Comment created = commentService.save(comment);
        URI location = uriBuilder.path("customers/{id}")
                .buildAndExpand(created.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
    }
}
