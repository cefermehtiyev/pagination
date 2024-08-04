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

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {
    private final UserRepository userRepository;

    @Override
    public void createUser(UserRequest request) {
        log.info("ActionLog.createUser.start {}", request);
        userRepository.save(USER_MAPPER.buldUserEntity(request));

    }

    @Override
    public UserResponse getUser(Long id){
        var user = fetchUserExist(id);

        return USER_MAPPER.buldUserResponse(user);

    }


    public void updateUser(Long id, UserRequest request) {
        var user = fetchUserExist(id);
        USER_MAPPER.updateUser(user,request);
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        var user = fetchUserExist(id);
        user.setStatus(INACTIVE);
        userRepository.save(user);
    }

    @Override
    public void setBirthPlace(Long id, String birthPlace) {
        var user = fetchUserExist(id);
        user.setBirthPlace(user.getBirthPlace());
        userRepository.save(user);
    }


    @Override
    public PageableUserResponse users(PageCriteria pageCriteria, UserCriteria userCriteria) {
        var userPage = userRepository.findAll(
                new UserSpecification(userCriteria),
                PageRequest.of(pageCriteria.getPage(),pageCriteria.getCount(), Sort.by("id").descending())
        );
        return USER_MAPPER.buildUserPageableResponse(userPage);
    }


    private UserEntity fetchUserExist(Long id){
        return userRepository.findById(id).
                orElseThrow(()->
                     new NotFoundException(NOT_FOUND_EXCEPTION.getCode(),NOT_FOUND_EXCEPTION.getMessage() ));

    }
}
