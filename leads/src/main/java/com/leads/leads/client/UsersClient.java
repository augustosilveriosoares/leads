package com.leads.leads.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("users")
public interface UsersClient {

    @GetMapping("/users")
    public List<Object> getAllUsers();
}
