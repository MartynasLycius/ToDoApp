package com.hafiz.interview.east.netic.todo.feignclient;

import com.hafiz.interview.east.netic.todo.dataclass.common.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@FeignClient(name = "AUTH-SERVICE", url = "${service.feign.client.auth-service}")
public interface AuthenticationServiceClient {

    @PostMapping("users/by-id-set")
    List<UserDto> getUsersByIds(Set<UUID> ids);
}
