package kr.co.mini.project.user.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import kr.co.mini.project.user.entity.User;

@Mapper
public interface UserDAO {
    List<User> findAll();
    User findById(Integer id);
    Optional<User> findByName(String name); // 일단 임시적으로 중복이름은 안된다고 대충 가정하고 이름으로만 구별하자
    void save(User user);
    void update(User user);
    void delete(Integer id);
}