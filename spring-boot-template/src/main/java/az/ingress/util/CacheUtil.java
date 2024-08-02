package az.ingress.util;

import az.ingress.dao.entity.UserEntity;
import az.ingress.mapper.UserMapper;
import az.ingress.model.response.PageableUserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

import static az.ingress.mapper.UserMapper.USER_MAPPER;

public class CacheUtil {
    public static PageableUserResponse mapToPageableResponse(Page<UserEntity> userEntityPage){

        return PageableUserResponse.builder().users(userEntityPage.map(a ->USER_MAPPER.buldUserResponse(a)).stream().toList())
                .hasNextPage(userEntityPage.hasNext())
                .lastPageNumber(userEntityPage.getTotalPages())
                .totalElements(userEntityPage.getNumberOfElements()).
                build();

    }
}
