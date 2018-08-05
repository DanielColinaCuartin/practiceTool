package com.alerttool.react.controllers;

import com.alerttool.react.models.User;
import com.alerttool.react.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method=RequestMethod.GET, value="/users")
    public Iterable<User> user() {
        return userRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/users")
    public User save(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @RequestMapping(method=RequestMethod.GET, value="/users/{id}")
    public Optional<User> show(@PathVariable String id) {
        return userRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/users/{id}")
    public User update(@PathVariable String id, @RequestBody User user) {
        Optional<User> optUser = userRepository.findById(id);
        User c = optUser.get();
        if(user.getFullName() != null)
            c.setFullName(user.getFirstName(), user.getLastName());
        if(user.getFirstName() != null)
            c.setFirstName(user.getFirstName());
        if(user.getLastName() != null)
            c.setLastName(user.getLastName());
        if(user.getPhoneNumber() != null)
            c.setPhoneNumber(user.getPhoneNumber());
        if(user.getEmail() != null)
            c.setEmail(user.getEmail());
        if(user.getTeamName() != null) 
            c.setTeamName(user.getTeamName());
        if(user.getOnCallStatus() != true) 
            c.changeOnCallStatus(user.getOnCallStatus());
        userRepository.save(c);
        return c;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/users/{id}")
    public String delete(@PathVariable String id) {
        Optional<User> optUser = userRepository.findById(id);
        User user = optUser.get();
        userRepository.delete(user);

        return "";
    }
}