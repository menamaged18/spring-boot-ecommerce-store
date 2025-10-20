package com.store.ecommerce.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.store.ecommerce.DTOs.User.UserRequest;
import com.store.ecommerce.DTOs.User.UserResponse;
import com.store.ecommerce.model.CustomUser;
import com.store.ecommerce.repository.UserRepo;

@Service
public class UserService {
    private final UserRepo userRepo;

    // @Autowired
    public UserService(UserRepo _userRepo){
        this.userRepo = _userRepo;
    }

    private CustomUser toEntity(UserRequest _user){
        CustomUser user = new CustomUser();
        user.setEmail(_user.getEmail());
        user.setName(_user.getName());
        user.setPassword(_user.getPassword());
        return user;
    }

    private UserResponse toResponse(CustomUser _CustomUser){
        UserResponse userRes = new UserResponse();
        userRes.setEmail(_CustomUser.getEmail());  
        userRes.setName(_CustomUser.getName());  
        return userRes;
    }

    // helper function to retrieve the CustomUser object
    public CustomUser ReturnCustomUserById(Long id){
        return userRepo.findById(id)
                            .orElseThrow(()-> new RuntimeException("user not found"));
    }

    // Services 
    public UserResponse RegisterUser(UserRequest userReq){
        if (userRepo.findByEmail(userReq.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        CustomUser userEntity = toEntity(userReq);
        CustomUser savedUser = userRepo.save(userEntity);

        return toResponse(savedUser);
    }


    public UserResponse FindUserById(Long id){
        CustomUser user = userRepo.findById(id)
                            .orElseThrow(()-> new RuntimeException("user not found"));
        return toResponse(user);
    }

    public List<UserResponse> allUsers(){
        List<CustomUser> users = userRepo.findAll();

        return users.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse EditUser(Long id, UserRequest userReq){
        CustomUser existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        
        if (userReq.getName() != null) {
            existingUser.setName(userReq.getName());
        }
        if (userReq.getEmail() != null) {
            existingUser.setEmail(userReq.getEmail());
        }
        // TODO: Password update would require hashing and specific business logic
        
        CustomUser updatedUser = userRepo.save(existingUser);
        return toResponse(updatedUser);
    }


    public void DeleteUserById(Long id){
        // CustomUser user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found!"));
        // userRepo.delete(user);
        userRepo.deleteById(id);
    }
}
