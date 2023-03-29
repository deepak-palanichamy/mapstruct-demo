package mapstructdemo.mapper;

import mapstructdemo.dto.EmployeeDto;
import mapstructdemo.dto.UserDto;
import mapstructdemo.entity.Employee;
import mapstructdemo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PojoDtoMapper {
    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    @Mapping(source = "employeeDto.employeeName", target = "name")
    @Mapping(source = "employeeDto.employeeJobTitle", target = "jobTitle")
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    @Mapping(source = "employee.name", target = "employeeName")
    @Mapping(source = "employee.jobTitle", target = "employeeJobTitle")
    EmployeeDto employeeToEmployeeDto(Employee employee);
}
