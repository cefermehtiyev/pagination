package az.ingress.mapper;

import az.ingress.dao.entity.UserEntity;
import az.ingress.model.enums.UserStatus;
import az.ingress.model.request.UserRequest;
import az.ingress.model.response.PageableUserResponse;
import az.ingress.model.response.UserResponse;
import org.springframework.data.domain.Page;

import java.util.Collections;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity buldUserEntity(UserRequest request) {
        return UserEntity.builder().
                userName(request.getUserName()).
                age(request.getAge()).
                status(UserStatus.ACTIVE).
                birthPlace(request.getBirthPlace()).
                build();

    }

    public UserResponse buldUserResponse(UserEntity userEntity) {
        return UserResponse.builder().
                id(userEntity.getId()).
                userName(userEntity.getUserName()).
                age(userEntity.getAge()).
                birthPalace(userEntity.getBirthPlace()).
                status(userEntity.getStatus()).
                build();
    }

    public UserEntity updateUser(UserEntity userEntity,UserRequest userRequest) {
        userEntity.setUserName(userRequest.getUserName());
        userEntity.setAge(userRequest.getAge());
        userEntity.setBirthPlace(userRequest.getBirthPlace());
        return userEntity;
    }

    public  PageableUserResponse buildUserPageableResponse(Page<UserEntity> userEntityPage){

        return PageableUserResponse.builder().users(Collections.singletonList(userEntityPage.map(a -> USER_MAPPER.buldUserResponse(a)).stream().toList()))
                .hasNextPage(userEntityPage.hasNext())
                .lastPageNumber(userEntityPage.getTotalPages())
                .totalElements(userEntityPage.getNumberOfElements()).
                build();

    }


}
