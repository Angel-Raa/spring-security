package com.caja.ideal.user;

import com.caja.ideal.DTO.UserDTO;
import com.caja.ideal.exception.UserAlreadyExists;
import com.caja.ideal.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private IUserRepository repository;
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<List<UserDTO>> allUser() {
        List<UserDTO> list = repository.findAll().stream().map(user -> new UserDTO(user.getEmail(), user.getUsername())).toList();
        return ResponseEntity.ok(list);
    }
    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<UserModels> email(String email) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<UserModels> user(Long id) {
        UserModels user = repository.findById(id).orElseThrow( () -> new UserNotFoundException(" does not exist "));
        return ResponseEntity.ok(user);
    }
    @Transactional
    @Override
    public ResponseEntity<UserModels> save(UserModels user) {
        Optional<UserModels> email = repository.findByEmail(user.getEmail());
        if (email.isPresent()){
            throw new UserAlreadyExists("user already exists ");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        UserModels save = repository.save(user);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserModels> update(UserModels user, Long id) {
        UserModels userToUpdate = repository.findById(id).orElseThrow( () -> new UserNotFoundException(" does not exist "));
        BeanUtils.copyProperties(user, userToUpdate, "id");
        UserModels update = repository.save(userToUpdate);
        return ResponseEntity.ok(update);
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        Optional<UserModels> user = repository.findById(id);
        Map<String, String> responde = new HashMap<>();
        if (user.isPresent()){
            responde.put("message", " successfully removed");
            repository.findById(id);
            return ResponseEntity.ok(responde);
        }
        responde.put("message", " error ");
        return ResponseEntity.badRequest().body(responde);
    }
}
