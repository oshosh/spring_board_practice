package kr.co.mini.project.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import kr.co.mini.common.dto.CommonResponseDto;
import kr.co.mini.project.user.dto.UserDTO;
import kr.co.mini.project.user.entity.User;
import kr.co.mini.project.user.service.CustomUserDetailsService;
import kr.co.mini.project.user.service.MyUserDetailsService;
import kr.co.mini.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {
  private final UserService userService;
  private final CustomUserDetailsService customUserDetailsService;

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

  @PostMapping("/force-login")
  public ResponseEntity<CommonResponseDto> forceLogin(jakarta.servlet.http.HttpSession session) {
    // 해당 사항은 잘 모르겠음 일단 메모리로 일단 처리만 시켜 두고 서버에서 프론트를 서빙하는 형태다 보니 복잡하게 jwt처리 대신 session으로만 처리하는게 나을듯
    User charlie = new User(3, "Charlie", "1234", null, null);
    MyUserDetailsService userDetails = (MyUserDetailsService) customUserDetailsService.loadUserByUsername(charlie.getName());
    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "1234", userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    // 세션에 SecurityContext 저장
    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

    return ResponseEntity.ok(new CommonResponseDto("OK", "Charlie로 로그인되었습니다."));
  }
}
