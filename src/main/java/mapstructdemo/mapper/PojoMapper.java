package mapstructdemo.mapper;

import mapstructdemo.dto.UserDto;
import mapstructdemo.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface PojoMapper {
    User userDtoToUser(UserDto userDto);

//    @Mapping(target = "fullName", expression = "java(user.getFirstName()+\" \"+user.getLastName())")
//    UserDto userToUserDto(User user);

    default UserDto userToUserDto(User user) {
        return UserDto.builder()
                .fullName(user.getFirstName() + " " + user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
