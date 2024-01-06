package com.example.makhzan.Controller;

import com.example.makhzan.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/makhzan/user")
@RequiredArgsConstructor
public class UserController {
    private  final UserService userService;


}
