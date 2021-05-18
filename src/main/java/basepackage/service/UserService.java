package basepackage.service;

import basepackage.entity.User;
import basepackage.generic.CRUD;
import basepackage.payload.UserPayload;
import basepackage.response.UserResponse;

import java.util.List;

public interface UserService extends CRUD<User> {
    User createUser(UserPayload userPayload);
    List<UserResponse> getAllUsers();
}
