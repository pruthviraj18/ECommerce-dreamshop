package com.application.Ecommerce.service.user;

import com.application.Ecommerce.dto.UserDto;
import com.application.Ecommerce.model.User;
import com.application.Ecommerce.request.CreateUserRequest;
import com.application.Ecommerce.request.UserUpdateRequest;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);
}
