package com.example.makhzan.Service;

import com.example.makhzan.Api.ApiException;
import com.example.makhzan.Model.User;
import com.example.makhzan.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(User user , Integer id){
        User oldUser = userRepository.findUserById(id);
        if(oldUser == null) throw new ApiException("User not found");
        user.setId(id);
        user.setRole(oldUser.getRole());
        //user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);

    }

    public void delete(Integer id){
        User user = userRepository.findUserById(id);
        if(user == null) throw new ApiException("User not found");
        userRepository.delete(user);
    }
}
