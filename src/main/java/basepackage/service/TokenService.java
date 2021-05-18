package basepackage.service;

import basepackage.entity.Token;
import basepackage.generic.CRUD;
import basepackage.payload.TokenPayload;

public interface TokenService extends CRUD<Token> {
    void saveToken(TokenPayload tokenPayload);
    Token getByHeader(String token);
    void deleteAll();
}
