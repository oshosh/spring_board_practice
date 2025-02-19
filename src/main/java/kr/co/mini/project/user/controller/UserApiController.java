package kr.co.mini.project.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.mini.project.user.dto.UserDTO;
import kr.co.mini.project.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {
  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
      return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
      return ResponseEntity.ok(userService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
      userService.save(userDTO);
      return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateUser(@PathVariable Integer id, 
                                       @RequestBody UserDTO userDTO) {
      userDTO.setId(id);
      userService.update(userDTO);
      return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
      userService.delete(id);
      return ResponseEntity.ok().build();
  }
}
