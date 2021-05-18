package basepackage.dao;

import basepackage.configuration.HibernateUtil;
import basepackage.entity.Token;
import basepackage.payload.TokenPayload;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TokenDaoImpl implements TokenDao {
    SessionFactory sessionFactory = null;

    public TokenDaoImpl(){
        HibernateUtil hibernateUtil = new HibernateUtil();
        sessionFactory = hibernateUtil.getSessionFactory();
    }


    @Override
    public Token get(Long id) {
        return null;
    }

    @Override
    public Token get(String s) {
        Token token = new Token();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Token.class);
        token = (Token) criteria.add(Restrictions.eq("username",s).ignoreCase()).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return token;
    }

    @Override
    public void save(Token token) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(token);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Token token) {

    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Token token = session.get(Token.class,id);
        session.delete(token);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Token> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Token> tokens = session.createQuery("from Token ").list();
        session.getTransaction().commit();
        session.close();
        return tokens;
    }

    @Override
    public Token getByHeader(String uuid) {
        Token token = new Token();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Token.class);
        token = (Token) criteria.add(Restrictions.eq("uuid",uuid).ignoreCase()).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return token;

    }

    public void deleteAll(){
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from Token ").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
