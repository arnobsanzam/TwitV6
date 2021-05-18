package basepackage.service;

import basepackage.dao.TokenDaoImpl;
import basepackage.entity.Token;
import basepackage.payload.TokenPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenDaoImpl tokenDao;

    @Override
    public Token get(Long id) {
        return null;
    }

    @Override
    public Token get(String s) {
        Token token;
        try {
            token = tokenDao.get(s);
        }
        catch (Exception e){
            token = null;
        }
        return token;
    }

    @Override
    public void save(Token token) {
        tokenDao.save(token);
    }

    @Override
    public void update(Token token) {

    }

    @Override
    public void delete(Long id) {
        tokenDao.delete(id);

    }

    @Override
    public List<Token> getAll() {
        return tokenDao.getAll();
    }

    @Override
    public void saveToken(TokenPayload tokenPayload) {
        Token token = new Token();
        token.setUuid(tokenPayload.getUuid());
        token.setUsername(tokenPayload.getUsername());
        tokenDao.save(token);
    }

    @Override
    public Token getByHeader(String uuid) {
        Token token = new Token();
        try{
            token = tokenDao.getByHeader(uuid);
        }
        catch (Exception e){
            token = null;
        }
        return token;
    }

    @Override
    public void deleteAll() {
        tokenDao.deleteAll();
    }
}
