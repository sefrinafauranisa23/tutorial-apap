package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    void deleteUser(UserModel user);
    List<UserModel> getListUser();
    UserModel getUserById(String id);
    UserModel findByUsername (String username);
    void updateUser(UserModel user);
    boolean isMatch(String oldPassword, String newPassword);
}

