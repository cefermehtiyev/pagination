package az.ingress.service.abstraction;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.UserCriteria;
import az.ingress.model.request.UserRequest;
import az.ingress.model.response.PageableUserResponse;
import az.ingress.model.response.UserResponse;

public interface UserService {

    void createUser(UserRequest request);
    UserResponse getUser(Long id);
    void updateUser(Long id, UserRequest request);
    void deleteUser(Long id);
    void setBirthPlace(Long id,String birthPlace);
    PageableUserResponse users(PageCriteria pageCriteria, UserCriteria userCriteria);

}
