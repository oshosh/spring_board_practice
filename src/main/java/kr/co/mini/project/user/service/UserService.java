package kr.co.mini.project.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.mini.project.user.dto.UserDTO;
import kr.co.mini.project.user.entity.User;
import kr.co.mini.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Integer id) {
        return convertToDTO(userRepository.findById(id));
    }

    @Transactional
    public void save(UserDTO userDTO) {
      userRepository.save(convertToEntity(userDTO));
    }

    @Transactional
    public void update(UserDTO userDTO) {
      userRepository.update(convertToEntity(userDTO));
    }

    @Transactional
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getName(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }

    private User convertToEntity(UserDTO dto) {
        return new User(
            dto.getId(),
            dto.getName(),
            dto.getCreatedAt(),
            dto.getUpdatedAt()
        );
    }
}
