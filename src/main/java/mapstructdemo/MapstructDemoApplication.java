package mapstructdemo;

import mapstructdemo.dto.EmployeeDto;
import mapstructdemo.dto.UserDto;
import mapstructdemo.entity.Employee;
import mapstructdemo.entity.User;
import mapstructdemo.mapper.PojoMapper;
import org.mapstruct.factory.Mappers;

public class MapstructDemoApplication {
    public static void main(String[] args) {
        PojoMapper mapper = Mappers.getMapper(PojoMapper.class);

        // UserDto to User mapping
        UserDto userDto = new UserDto();
        userDto.setEmail("user1@exxample.com");
        userDto.setFirstName("User");
        userDto.setLastName("One");
        userDto.setPhone("+919988776655");
        userDto.setUsername("user1");

        User mappedUser = mapper.userDtoToUser(userDto);
        System.out.println(userDto);
        System.out.println(mappedUser.toString());

        // User to UserDto mapping
        User user = new User();
        user.setEmail("user2@exxample.com");
        user.setFirstName("User");
        user.setLastName("Two");
        user.setPhone("+919988776655");
        user.setUsername("user2");
        UserDto mappedUserDto = mapper.userToUserDto(user);
        System.out.println(user);
        System.out.println(mappedUserDto);

        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setEmployeeName("Employee1");
        employeeDto1.setEmployeeJobTitle("Developer");
        Employee mappedEmployee1 = mapper.employeeDtoToEmployee(employeeDto1);
        System.out.println(employeeDto1);
        System.out.println(mappedEmployee1);

        Employee employee1 = new Employee();
        employee1.setName("Employee1");
        employee1.setJobTitle("Developer");
        EmployeeDto mappedEmployeeDto1 = mapper.employeeToEmployeeDto(employee1);
        System.out.println(employee1);
        System.out.println(mappedEmployeeDto1);

    }
}
