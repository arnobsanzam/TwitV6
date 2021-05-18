package basepackage.test;

import basepackage.dao.TweetDaoImpl;
import basepackage.entity.Token;
import basepackage.payload.TweetPayload;
import basepackage.service.TweetService;
import basepackage.service.TweetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

public class Test {
    @Autowired
    TweetService tweetService;
    public static void main(String[] args) {
        TweetService tweetService = new TweetServiceImpl();
        TweetDaoImpl tweetDao = new TweetDaoImpl();
        System.out.println(tweetService.get(Long.valueOf(1)).getMessage());
    }


}
