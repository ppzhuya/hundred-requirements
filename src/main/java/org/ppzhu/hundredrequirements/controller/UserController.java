package org.ppzhu.hundredrequirements.controller;

import org.ppzhu.hundredrequirements.pojo.ResponseData;
import org.ppzhu.hundredrequirements.pojo.User;
import org.ppzhu.hundredrequirements.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseData> register(@RequestParam String username,@RequestParam String password){
        User user = new User();
        user.setUname(username);
        user.setUpwd(password);
        boolean register = userService.register(user);
        if(register){
            return ResponseEntity.ok(ResponseData.success("注册成功"));
        }else {
            return ResponseEntity.ok(ResponseData.error("注册失败",409));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestParam String username,@RequestParam String password){
        Optional<User> userOptional = userService.login(username, password);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(new ResponseData("登录成功",200,userOptional.get().getUid()));
        }else {
            return ResponseEntity.ok(ResponseData.error("登录失败",401));
        }

    }



    @PostMapping("/delete")
    public ResponseEntity<ResponseData> delete(@RequestParam String uid){
        boolean b = userService.deleteUser(uid);

        if(b){
            return ResponseEntity.ok(ResponseData.success("删除成功"));
        }
        return ResponseEntity.ok(ResponseData.error("删除失败",404));
    }

    @PostMapping("/resetPwd")
    public ResponseEntity<ResponseData> resetPwd(@RequestParam String uid,@RequestParam String oldPassword,@RequestParam String newPassword){
        boolean b = userService.resetPassword(uid, oldPassword, newPassword);

        if(b){
            return ResponseEntity.ok(ResponseData.success("修改成功"));
        }
        return ResponseEntity.ok(ResponseData.error("修改失败",404));
    }

}
