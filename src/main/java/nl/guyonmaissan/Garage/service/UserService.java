package nl.guyonmaissan.Garage.service;


import nl.guyonmaissan.Garage.model.User;

import java.util.Collection;
import java.util.Map;

public interface UserService {

    Collection<User> getAllUsers();
    User getUserById(Long id);
    Collection<User> getUser(String email);
    long createUser(User user);
    void updateUser(Long id, User user);
    void partialUpdateUser(Long id, Map<String, String> fields);
    void deleteUser(Long id);
}
