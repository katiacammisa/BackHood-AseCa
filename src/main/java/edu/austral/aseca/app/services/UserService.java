package edu.austral.aseca.app.services;

import edu.austral.aseca.app.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class UserService {
  
  private final HashMap<Long, User> users = new HashMap<>();
  
  public User findById(Long id) {
    return users.getOrDefault(id, new User(id));
  }
  
  public void save(User user) {
    users.putIfAbsent(user.getId(), user);
  }
}
