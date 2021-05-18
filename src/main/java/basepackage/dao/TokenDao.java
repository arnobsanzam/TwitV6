package basepackage.dao;

import basepackage.entity.Token;
import basepackage.generic.CRUD;
import basepackage.payload.TokenPayload;

public interface TokenDao extends CRUD<Token> {
    Token getByHeader(String uuid);

}
