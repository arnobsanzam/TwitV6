package basepackage.dao;

import basepackage.configuration.HibernateUtil;
import basepackage.entity.Tweet;
import basepackage.entity.User;
import basepackage.payload.TweetPayload;
import basepackage.response.TweetResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TweetDaoImpl implements TweetDao {

    SessionFactory sessionFactory = null;

    public TweetDaoImpl(){
        HibernateUtil hibernateUtil = new HibernateUtil();
        sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public Tweet get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Tweet tweet = session.get(Tweet.class,id);
        session.getTransaction().commit();
        session.close();
        return tweet;
    }

    @Override
    public Tweet get(String s) {
        return null;
    }

    @Override
    public void save(Tweet tweet) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(tweet);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Tweet tweet) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(tweet);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Tweet tweet = session.get(Tweet.class,id);
        User user = tweet.getUser();
        tweet.setUser(null);
        user.getTweets().remove(tweet);
        session.delete(tweet);
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Tweet> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Tweet> tweets = session.createQuery("from Tweet ").list();
        session.getTransaction().commit();
        session.close();
        return tweets;
    }
}
