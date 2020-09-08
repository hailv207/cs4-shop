package com.red.app.http.controller.friends;

import com.red.services.friend.FriendService;
import com.red.services.user.UserService;
import com.red.system.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendListController {


    @Autowired
    private Auth auth;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendRepository friendRepository;

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public ResponseEntity<String> getFriends(@RequestParam(value = "id", required = false) Long id) {

        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}