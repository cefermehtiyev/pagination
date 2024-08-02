package az.ingress.mapper;

import az.ingress.dao.entity.UserEntity;
import az.ingress.model.enums.UserStatus;
import az.ingress.model.request.UserRequest;
import az.ingress.model.response.UserResponse;

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


}
