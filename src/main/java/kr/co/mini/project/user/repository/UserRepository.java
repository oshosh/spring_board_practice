package kr.co.mini.project.user.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import kr.co.mini.project.user.dao.UserDAO;
import kr.co.mini.project.user.entity.User;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserDAO userDAO;

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User findById(Integer id) {
        return userDAO.findById(id);
    }

    public void save(User user) {
        userDAO.save(user);
    }

    public void update(User user) {
        userDAO.update(user);
    }

    public void delete(Integer id) {
        userDAO.delete(id);
    }
}