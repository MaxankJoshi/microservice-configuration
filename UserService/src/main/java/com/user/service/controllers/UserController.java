package com.user.service.controllers;

import com.user.service.entities.User;
import com.user.service.payloads.ApiResponse;
import com.user.service.services.UserService;
import com.user.service.services.implementation.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api")
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = this.userService.createUser(user);

        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = this.userService.updateUser(user);

        return ResponseEntity.ok(updatedUser);
    }

    int retryCount = 1;
    @GetMapping("/get/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId) {
//        logger.info("Retry count : {} ", retryCount);
//        retryCount++;
        User user = this.userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

//    Creating fall back method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(Integer userId,Exception ex) {
        logger.info("Fallback is executed because service is down : " + ex.getMessage());

        User user = User.builder().userId(0).userName("Dummy").userEmail("dummy@gmail.com").userAbout("This user is created dummy because some services is down").build();

        return ResponseEntity.ok(user);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
        this.userService.deleteUser(userId);

        ApiResponse apiResponse = ApiResponse.builder().message("User is deleted successfully with user id: " + userId).success(true).httpStatus(HttpStatus.OK).build();

        return ResponseEntity.ok(apiResponse);
    }
}
