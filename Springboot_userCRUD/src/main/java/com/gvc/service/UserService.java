package com.gvc.service;

import com.gvc.pojo.User;
import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserByID(int id);
    public void addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(int id);
}
