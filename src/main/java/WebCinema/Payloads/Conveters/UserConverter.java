package WebCinema.Payloads.Conveters;

import WebCinema.Entity.User;
import WebCinema.Payloads.DataRequests.UserRequest.SignUpRequest;
import WebCinema.Payloads.DataResponses.DataUser.UserResponse;
import WebCinema.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserResponse EntityToDTO(User user) {
        var dataResponseUser = modelMapper.map(user, UserResponse.class);
        dataResponseUser.setUserName(user.getUsername()); // check why mapper not work with userName
//       var optionalUser = userRepository.findByUserName(user.getUsername());
//        optionalUser.ifPresent(foundUser -> {
//            if (foundUser.getRole() != null) {
//                dataResponseUser.setRoleName(foundUser.getRole().getRoleName().name());
//            }
//        });
        userRepository.findByUserName(user.getUsername())
                .map(User::getRole)
                .map(role -> role.getRoleName().name())
                .ifPresent(dataResponseUser::setRoleName);
        return dataResponseUser;
    }

    public User DTOtoEntity(SignUpRequest request) {
        return modelMapper.map(request, User.class);
    }
}
