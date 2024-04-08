package WebCinema.Services.Interfaces;

import WebCinema.Payloads.DataRequests.UserRequest.ChangePasswordRequest;
import WebCinema.Payloads.DataRequests.UserRequest.ChangeRoleRequest;
import WebCinema.Payloads.DataRequests.UserRequest.UpdateUserRequest;
import WebCinema.Payloads.DataResponses.DataUser.UserResponse;
import WebCinema.Payloads.Responses.MessageResponse;

import java.security.Principal;
import java.util.List;

public interface IUserService {
    MessageResponse changePassword(ChangePasswordRequest request, Principal connectedUser);

    MessageResponse changeRole(ChangeRoleRequest request);

    MessageResponse updateUser(UpdateUserRequest request);

    MessageResponse delete(String email);

    List<UserResponse> getAllUsers();

    List<UserResponse> getById(long id);

}
