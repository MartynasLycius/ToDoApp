package com.backend.boiler.plate.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.boiler.plate.Utils.Constants;
import com.backend.boiler.plate.app.models.MenuItem;
import com.backend.boiler.plate.app.models.Role;
import com.backend.boiler.plate.app.models.User;

@Service
public class UserAccessMenuGenerator {
	
	public List<MenuItem> generateMenu(Optional<User> useOptional) {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		for(Role role: useOptional.get().getRoles()) {
			if(role.getName().equals("ROLE_"+Constants.getInstance().ROLE_ADMIN)) {
				MenuItem menuItem = new MenuItem();
				
				menuItem.setPath("/dashboard");
				menuItem.setTitle("Home");
				menuItem.setShow(true);
				menuItems.add(menuItem);
				
				menuItem = new MenuItem();
				menuItem.setPath("/dashboard/users");
				menuItem.setTitle("Users");
				menuItem.setShow(true);
				menuItems.add(menuItem);
				
				menuItem = new MenuItem();
				menuItem.setPath("/dashboard/users/add");
				menuItem.setTitle("Add User");
				menuItem.setShow(true);
				menuItems.add(menuItem);
				
				menuItem = new MenuItem();
				menuItem.setPath("/dashboard/users/?");
				menuItem.setTitle("Update User");
				menuItem.setShow(false);
				menuItems.add(menuItem);
				
			} else if(role.getName().equals("ROLE_"+Constants.getInstance().ROLE_USER)) {
				
				MenuItem menuItem = new MenuItem();
				
				menuItem.setPath("/dashboard");
				menuItem.setTitle("Home");
				menuItem.setShow(true);
				menuItems.add(menuItem);
				
				menuItem = new MenuItem();
				menuItem.setPath("/dashboard/todos");
				menuItem.setTitle("Todos List");
				menuItem.setShow(true);
				menuItems.add(menuItem);
				
				menuItem = new MenuItem();
				menuItem.setPath("/dashboard/todos/add");
				menuItem.setTitle("Add Todo");
				menuItem.setShow(true);
				menuItems.add(menuItem);
				
				menuItem = new MenuItem();
				menuItem.setPath("/dashboard/todos/?");
				menuItem.setTitle("Update Todo");
				menuItem.setShow(false);
				menuItems.add(menuItem);
			}
		}
		return menuItems;
	}
}
