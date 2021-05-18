package basepackage.service;

import basepackage.dao.UserDaoImpl;
import basepackage.entity.User;
import basepackage.payload.UserPayload;
import basepackage.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDaoImpl userDao;

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User get(String username) {
        User user;
        try{
            user = userDao.get(username);
        }
        catch (Exception e){
            user = null;
        }
        return user;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User createUser(UserPayload userPayload) {
        User user = new User();
        user.setUsername(userPayload.getUsername());
        user.setPassword(userPayload.getPassword());
        save(user);
        return user;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return null;
    }
}
