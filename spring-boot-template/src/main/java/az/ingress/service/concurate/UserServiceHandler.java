package az.ingress.service.concurate;

import az.ingress.dao.entity.UserEntity;
import az.ingress.dao.repository.UserRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.UserCriteria;
import az.ingress.model.request.UserRequest;
import az.ingress.model.response.PageableUserResponse;
import az.ingress.model.response.UserResponse;
import az.ingress.service.specification.UserSpecification;
import az.ingress.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.mapper.UserMapper.USER_MAPPER;
import static az.ingress.model.enums.ExceptionConstants.NOT_FOUND_EXCEPTION;
import static az.ingress.model.enums.UserStatus.INACTIVE;
import static az.ingress.util.CacheUtil.mapToPageableResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {
    private final UserRepository userRepository;

    @Override
    public void createUser(UserRequest request) {
        userRepository.save(USER_MAPPER.buldUserEntity(request));

    }

    @Override
    public UserResponse getUser(Long id){
        var user = fetchUserExist(id);

        return USER_MAPPER.buldUserResponse(user);

    }

    public List<UserResponse> getAllUser(){
        return userRepository.findAll().stream().map(USER_MAPPER::buldUserResponse).toList();
    }

    public void updateUser(Long id, UserRequest request) {
        var user = fetchUserExist(id);
        user.setUserName(request.getUserName());
        user.setAge(request.getAge());
        user.setBirthPlace(request.getBirthPlace());
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        var user = fetchUserExist(id);
        user.setStatus(INACTIVE);
    }

    @Override
    public void setBirthPlace(Long id, String birthPlace) {
        var user = fetchUserExist(id);
        user.setBirthPlace(user.getBirthPlace());
        userRepository.save(user);
    }

    @Override
    public PageableUserResponse getUsers(PageCriteria pageCriteria, UserCriteria userCriteria) {
        var userPage = userRepository.findAll(
                new UserSpecification(userCriteria),
                PageRequest.of(pageCriteria.getPage(),pageCriteria.getCount(), Sort.by("id").descending())
        );
        return mapToPageableResponse(userPage);
    }


    private UserEntity fetchUserExist(Long id){
        return userRepository.findById(id).
                orElseThrow(()->{
                    log.error("ActionLog.fetchUserExist.error card with id:{}",id);
                    return new NotFoundException(NOT_FOUND_EXCEPTION.getCode(),NOT_FOUND_EXCEPTION.getMessage() );});

    }
}
