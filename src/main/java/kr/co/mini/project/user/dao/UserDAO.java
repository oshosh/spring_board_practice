package kr.co.mini.project.user.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.co.mini.project.user.entity.User;

@Mapper
public interface UserDAO {
    List<User> findAll();
    User findById(Integer id);
    void save(User user);
    void update(User user);
    void delete(Integer id);
}