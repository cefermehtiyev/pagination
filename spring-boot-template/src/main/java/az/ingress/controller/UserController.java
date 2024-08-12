package az.ingress.controller;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.UserCriteria;
import az.ingress.model.request.UserRequest;
import az.ingress.model.response.CardResponse;
import az.ingress.model.response.PageableUserResponse;
import az.ingress.model.response.UserResponse;
import az.ingress.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users")
@CrossOrigin
public class UserController {
    private final UserService userService;


    @PostMapping
    @ResponseStatus(CREATED)
    public void createUser(@RequestBody UserRequest request) {
        userService.createUser(request);
    }

    @GetMapping("{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.getUser(id);
    }




    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        userService.updateUser(id, request);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PatchMapping("{id}")
    public void serBirthPlace(@PathVariable Long id,@RequestParam String birthPlace){
        userService.setBirthPlace(id, birthPlace);
    }

    @GetMapping
    public PageableUserResponse getUsers(PageCriteria pageCriteria,
                                         UserCriteria userCriteria){
        return userService.users(pageCriteria, userCriteria);

    }



}