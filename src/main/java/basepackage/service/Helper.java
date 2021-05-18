package basepackage.service;

import basepackage.entity.Token;
import basepackage.exception.AuthorizationFailedException;
import basepackage.exception.NoActiveSessionFoundException;
import basepackage.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class Helper {
    @Autowired
    TokenService tokenService;
    public String findCurrentUser(String uuid) throws NoActiveSessionFoundException, AuthorizationFailedException {
        List<Token> tokens = tokenService.getAll();
        if(tokens.isEmpty()){
            throw new NoActiveSessionFoundException("No active session...");
        }
        Token token = tokenService.getByHeader(uuid);
        if(token != null){
            return token.getUsername();
        }
        else{
            throw new AuthorizationFailedException("Authorization failed...");
        }
    }

    public String getAllDefaultMessages(BindingResult bindingResult){
        List<FieldError> errors = bindingResult.getFieldErrors();
        String errorMessage = "";
        for (FieldError error : errors) {
            errorMessage += error.getObjectName() + " - " + error.getDefaultMessage() + " ";
        }
        return errorMessage;
    }
}
