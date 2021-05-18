package basepackage.service;

import basepackage.entity.Tweet;
import basepackage.generic.CRUD;
import basepackage.payload.TweetPayload;
import basepackage.response.TweetResponse;

import java.util.List;

public interface TweetService extends CRUD<Tweet> {
    Tweet createTweet(TweetPayload tweetPayload);
    List<TweetResponse> getAllTweets();
}
