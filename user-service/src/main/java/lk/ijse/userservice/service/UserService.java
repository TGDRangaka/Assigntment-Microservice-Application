package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.UserEntity;
import lk.ijse.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public UserDTO register(UserDTO userDTO) {
        userDTO.setId(UUID.randomUUID().toString());
        UserEntity save = userRepo.save(new ModelMapper().map(userDTO, UserEntity.class));
        return new ModelMapper().map(save, UserDTO.class);
    }

    public void update(UserDTO userDTO, String userId) {
        Optional<UserEntity> byId = userRepo.findById(userId);
        if(byId.isPresent()){
            userDTO.setId(userId);
            userRepo.save(new ModelMapper().map(userDTO, UserEntity.class));
            return;
        }

        throw new RuntimeException("Couldn't find user with id " + userId);
    }

    public boolean userVerify(String email, String password){
        Optional<UserEntity> byEmail = userRepo.findUserByEmailAndPassword(email, password);
        if(byEmail.isPresent()){
            return true;
        }
        return false;
    }
}
