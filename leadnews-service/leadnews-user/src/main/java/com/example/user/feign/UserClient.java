package com.example.user.feign;

import com.example.api.user.IUserClient;
import com.example.model.user.pojo.ApUser;
import com.example.user.service.ApUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserClient implements IUserClient {

    @Autowired
    private ApUserService apUserService;

    @Override
    @GetMapping("/api/v1/user/{id}")
    public ApUser findUserById(@PathVariable("id") Integer id) {
        return apUserService.getById(id);
    }
}