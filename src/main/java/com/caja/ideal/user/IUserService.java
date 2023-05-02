package com.caja.ideal.user;

import com.caja.ideal.DTO.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    ResponseEntity<List<UserDTO>> allUser();
    ResponseEntity<UserModels> email(String email);
    ResponseEntity<UserModels> user(Long id);
    ResponseEntity<UserModels> save(UserModels user);
    ResponseEntity<UserModels> update(UserModels user, Long id);
    ResponseEntity<Object> delete (Long id);
}
