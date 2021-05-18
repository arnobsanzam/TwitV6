package basepackage.dao;

import basepackage.configuration.HibernateUtil;
import basepackage.entity.User;
import basepackage.payload.UserPayload;
import basepackage.response.UserResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    SessionFactory sessionFactory = null;
    public UserDaoImpl(){
        HibernateUtil hibernateUtil = new HibernateUtil();
        sessionFactory = hibernateUtil.getSessionFactory();
    }
    @Override
    public User get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class,id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public User get(String username) {
        User user = new User();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        user = (User) criteria.add(Restrictions.eq("username",username).ignoreCase()).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long userid) {
        User user = null;
        try {
            user = get(userid);
        }
        catch (Exception e)
        {
        }
        if(user != null)
        {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User ").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }
}
