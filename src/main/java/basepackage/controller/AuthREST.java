package basepackage.controller;

import basepackage.entity.Token;
import basepackage.entity.User;
import basepackage.exception.*;
import basepackage.payload.TokenPayload;
import basepackage.payload.UserPayload;
import basepackage.response.UserResponse;
import basepackage.service.TokenService;
import basepackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AuthREST {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserPayload> signUp(@Valid @RequestBody UserPayload userPayload, BindingResult bindingResult) throws InvalidEntryException, DatabaseDuplicateEntryException {
        if(bindingResult.hasErrors()){
            throw new InvalidEntryException("Invalid entry...");
        }
        User user = userService.get(userPayload.getUsername());
        if(user != null){
            throw new DatabaseDuplicateEntryException("Username " + user.getUsername() + " already exists");
        }
        userService.createUser(userPayload);
        return ResponseEntity.ok().body(userPayload);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenPayload> signIn(@Valid @RequestBody UserPayload userPayload, BindingResult bindingResult) throws InvalidEntryException, EntryNotFoundException, AuthorizationFailedException {
        if(bindingResult.hasErrors()){
            throw new InvalidEntryException("Invalid entry...");
        }
        User user = userService.get(userPayload.getUsername());
        if(user == null){
            throw new EntryNotFoundException("User " + userPayload.getUsername() + " does not exist...");
        }
        if(!user.getPassword().equals(userPayload.getPassword())){
            throw new AuthorizationFailedException("Credentials do not match...");
        }

        Token token = tokenService.get(user.getUsername());
        if(token != null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String uuid = UUID.randomUUID().toString();
        TokenPayload tokenPayload = new TokenPayload(uuid,user.getUsername());
        tokenService.saveToken(tokenPayload);
        return ResponseEntity.ok().body(tokenPayload);
    }

    @GetMapping("/sign-out")
    public ResponseEntity<String>signout(@RequestHeader(value = "Authorization") String authorizationHeader) throws NoActiveSessionFoundException, ForbiddenRequestException {
        List<Token> tokenList = tokenService.getAll();
        if(tokenList.isEmpty()){
            throw new NoActiveSessionFoundException("No active session...");
        }
        Token token = tokenService.getByHeader(authorizationHeader);
        if(token != null){
            tokenService.delete(token.getTokenid());
            return ResponseEntity.ok().build();
        }
        throw new ForbiddenRequestException("Forbidden request...");
    }

    @DeleteMapping(value = {"/remove-tokens"})
    public ResponseEntity<Object>removeAllTokens(){
        tokenService.deleteAll();
        return new ResponseEntity<>("Tokens removed...",HttpStatus.OK);
    }
}
