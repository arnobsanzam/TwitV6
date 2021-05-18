package basepackage.service;

import basepackage.dao.TweetDaoImpl;
import basepackage.entity.Tweet;
import basepackage.payload.TweetPayload;
import basepackage.response.TweetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {
    @Autowired
    TweetDaoImpl tweetDao;

    @Override
    public Tweet get(Long id) {
        return tweetDao.get(id);
    }

    @Override
    public Tweet get(String s) {
        return tweetDao.get(s);
    }

    @Override
    public void save(Tweet tweet) {
        tweetDao.save(tweet);
    }

    @Override
    public void update(Tweet tweet) {
        tweetDao.update(tweet);
    }

    @Override
    public void delete(Long id) {
        tweetDao.delete(id);
    }

    @Override
    public List<Tweet> getAll() {
        return tweetDao.getAll();
    }

    @Override
    public Tweet createTweet(TweetPayload tweetPayload) {
        Tweet tweet = new Tweet();
        tweet.setMessage(tweetPayload.getMessage());
        tweet.setTimestamp(tweetPayload.getTimestamp());
        tweet.setUser(tweetPayload.getUser());
        save(tweet);
        return tweet;
    }

    @Override
    public List<TweetResponse> getAllTweets() {
        List<TweetResponse>tweetResponses = new ArrayList<>();
        List<Tweet>tweets = getAll();
        for(int i = 0; i < tweets.size(); i++) {
            TweetResponse tweetResponse = new TweetResponse(tweets.get(i).getTweetid(),tweets.get(i).getMessage(), tweets.get(i).getUser().getUsername(),tweets.get(i).getTimestamp());
            tweetResponses.add(tweetResponse);
        }
        return tweetResponses;
    }
}
