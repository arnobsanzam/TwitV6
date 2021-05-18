package basepackage.controller;

import basepackage.entity.Tweet;
import basepackage.entity.User;
import basepackage.exception.*;
import basepackage.service.Helper;
import basepackage.payload.TweetPayload;
import basepackage.response.TweetResponse;
import basepackage.service.TweetService;
import basepackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api")
public class TweetREST {
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;
    @Autowired
    private Helper helper;

    @PostMapping(value = "/tweets")
    public ResponseEntity<TweetResponse> createTweet(@Valid @RequestBody TweetPayload tweetPayload, BindingResult bindingResult, @RequestHeader(value = "Authorization") String authorizationHeader) throws AuthorizationFailedException, NoActiveSessionFoundException, InvalidEntryException {
        String currentUser = helper.findCurrentUser(authorizationHeader);
        User user = userService.get(currentUser);
        if (bindingResult.hasErrors()) {
            String errorMessage = helper.getAllDefaultMessages(bindingResult);
            throw new InvalidEntryException(errorMessage);
        }
        tweetPayload.setUser(user);
        Tweet tweet = tweetService.createTweet(tweetPayload);
        user.getTweets().add(tweet);
        userService.update(user);
        TweetResponse tweetResponse = new TweetResponse(tweet.getTweetid(), tweet.getMessage(), tweet.getUser().getUsername(), tweet.getTimestamp());
        return ResponseEntity.status(HttpStatus.CREATED).body(tweetResponse);
    }

    @GetMapping(value = "/tweets")
    public ResponseEntity<List<TweetResponse>> seeUserTweets(@RequestHeader(value = "Authorization") String authorizationHeader) throws AuthorizationFailedException, NoActiveSessionFoundException {
        String currentUser = helper.findCurrentUser(authorizationHeader);
        User user = userService.get(currentUser);
        List<Tweet>tweets = (List<Tweet>) user.getTweets();
        List<TweetResponse>tweetResponses = new ArrayList<>();
        for(Tweet tweet:tweets){
            TweetResponse tweetResponse = new TweetResponse(tweet.getTweetid(),tweet.getMessage(),tweet.getUser().getUsername(),tweet.getTimestamp());
            tweetResponses.add(tweetResponse);
        }
        return ResponseEntity.ok().body(tweetResponses);
    }

    @GetMapping(value = "/tweets/{id}")
    public ResponseEntity<TweetResponse>seeUserTweetById(@RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable(name = "id")Long id) throws AuthorizationFailedException, NoActiveSessionFoundException, EntryNotFoundException, ForbiddenRequestException {
        String currentUser = helper.findCurrentUser(authorizationHeader);
        Tweet tweet = tweetService.get(id);
        if(tweet == null){
            throw new EntryNotFoundException("Tweet does not exist...");
        }
        else if(!tweet.getUser().getUsername().equals(currentUser)){
            throw new ForbiddenRequestException("Forbidden request...");
        }
        TweetResponse tweetResponse = new TweetResponse(tweet.getTweetid(),tweet.getMessage(),tweet.getUser().getUsername(),tweet.getTimestamp());
        return ResponseEntity.ok().body(tweetResponse);
    }

    @PutMapping(value = "/tweets/{id}")
    public ResponseEntity<TweetResponse>updateUserTweetById(@Valid @RequestBody TweetPayload tweetPayload, BindingResult bindingResult, @RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable(name = "id")Long id) throws AuthorizationFailedException, NoActiveSessionFoundException, EntryNotFoundException, ForbiddenRequestException, InvalidEntryException {
        String currentUser = helper.findCurrentUser(authorizationHeader);
        Tweet tweet = tweetService.get(id);
        if(tweet == null){
            throw new EntryNotFoundException("Tweet does not exist..");
        }
        if(!tweet.getUser().getUsername().equals(currentUser)){
            throw new ForbiddenRequestException("Forbidden request...");
        }
        if(bindingResult.hasErrors()){
            String errorMessage = helper.getAllDefaultMessages(bindingResult);
            throw new InvalidEntryException(errorMessage);
        }
        tweet.setMessage(tweetPayload.getMessage());
        tweet.setTimestamp(tweetPayload.getTimestamp());
        tweetService.update(tweet);
        TweetResponse tweetResponse = new TweetResponse(tweet.getTweetid(),tweet.getMessage(),tweet.getUser().getUsername(),tweet.getTimestamp());
        return ResponseEntity.ok().body(tweetResponse);
    }

    @DeleteMapping(value = "/tweets/{id}")
    public ResponseEntity deleteTweetById(@RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable(name = "id")Long id) throws AuthorizationFailedException, NoActiveSessionFoundException, EntryNotFoundException, ForbiddenRequestException {
        String currentUser = helper.findCurrentUser(authorizationHeader);
        Tweet tweet = tweetService.get(id);
        if(tweet == null){
            throw new EntryNotFoundException("Tweet does not exist...");
        }
        else if(!tweet.getUser().getUsername().equals(currentUser)){
            throw new ForbiddenRequestException("Forbidden request...");
        }
        tweetService.delete(tweet.getTweetid());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tweets/all")
    public ResponseEntity<List<TweetResponse>> seeAllTweets(@RequestHeader(value = "Authorization") String authorizationHeader) throws AuthorizationFailedException, NoActiveSessionFoundException {
        String currentUser = helper.findCurrentUser(authorizationHeader);
        List<TweetResponse> tweetResponses = tweetService.getAllTweets();
        if(tweetResponses.size() == 0){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(tweetResponses);
    }


}
