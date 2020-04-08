package com.murad.todoapp.Mapper;

import com.murad.todoapp.domain.User;
import com.murad.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class UserMapper {

    @Autowired
    UserService userService;

   public void mapUserObjectForEdit(@ModelAttribute User user, @RequestParam(value = "onoffswitch", required = false, defaultValue = "false") boolean onoffswitch) {
        User editUser = userService.findOneById(user.getId());
        editUser.setEmail(user.getEmail());
        editUser.setActive(onoffswitch);
        editUser.setRole(user.getRole());
        String passwordHash = user.getPasswordHash();
        if (passwordHash.isEmpty() || passwordHash.equals("")) {
            userService.editAndSave(editUser);
        } else {
            editUser.setPasswordHash(user.getPasswordHash());
            userService.save(editUser);
        }
    }

}
