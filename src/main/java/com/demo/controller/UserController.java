package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", users);
        return ResponseEntity.ok(result);
    }

    /**
     * 根据ID查询用户
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        Map<String, Object> result = new HashMap<>();
        if (user == null) {
            result.put("code", 404);
            result.put("message", "用户不存在");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", user);
        return ResponseEntity.ok(result);
    }

    /**
     * 新增用户
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            Map<String, Object> result = new HashMap<>();
            result.put("code", 400);
            result.put("message", "用户名不能为空");
            return ResponseEntity.badRequest().body(result);
        }
        User saved = userService.addUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 201);
        result.put("message", "添加成功");
        result.put("data", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * 更新用户
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User updated = userService.updateUser(user);
        Map<String, Object> result = new HashMap<>();
        if (updated == null) {
            result.put("code", 404);
            result.put("message", "用户不存在");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        result.put("code", 200);
        result.put("message", "更新成功");
        result.put("data", updated);
        return ResponseEntity.ok(result);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        Map<String, Object> result = new HashMap<>();
        if (!deleted) {
            result.put("code", 404);
            result.put("message", "用户不存在");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        result.put("code", 200);
        result.put("message", "删除成功");
        return ResponseEntity.ok(result);
    }
}
