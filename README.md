# MapStruct Demo Application (Java)

This is a demo application that demonstrates the usage of MapStruct library in a maven project for bean mapper classes
generation. MapStruct is a _code generator_ that simplifies the implementation of mappings between Java bean types based
on a convention over configuration approach.

A common type of conversion happens between persistence-backed entities and DTOs that go out to the client-side.
MapStruct solves this problem by **generating bean mapper classes automatically**.

## Implementation

1. create a new simple maven project.
2. Add the following Mapstruct dependency to your project:

```xml

<dependency>
   <groupId>org.mapstruct</groupId>
   <artifactId>mapstruct</artifactId>
   <version>1.5.3.Final</version>
</dependency>
```

3. Add the _annotationProcessorPaths_ section to the configuration part of the maven-compiler-plugin plugin.

```xml

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.5.1</version>
    <configuration>
        <source>11</source>
        <target>17</target>
        <annotationProcessorPaths>
            <path>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>1.5.3.Final</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

The mapstruct-processor is used to generate the mapper implementation during the build
> **NOTE:** The latest stable release
> of [MapStruct](https://central.sonatype.com/artifact/org.mapstruct/mapstruct/1.5.3.Final) and
> its [processor](https://central.sonatype.com/artifact/org.mapstruct/mapstruct-processor/1.5.3.Final) are both
> available from the Maven Central Repository.

4. Create Source & Destination POJOs for mapping.

```java
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    // getters & setters
}

public class UserDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    // getters & setters
}
```

5. Create a mapper interface.

```java

@Mapper
public interface PojoMapper {
    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
```

> **NOTE**: MapStruct creates implementation for the PojoMapper.

6. Trigger Mapstruct processing by executing an `mvn clean install` command. This will generate the implementation class
   under _/target/generated-sources/annotations/_. Here is the class that MapStruct auto-generated for us:

```java
public class PojoMapperImpl implements PojoMapper {

    @Override
    public User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        return user;
    }

    @Override
    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        return userDto;
    }
}
```

---

## Mapstruct with Lombok

MapStruct uses getters and setters for its mappers. We will see how MapStruct utilizes Lombok generated builders.

### Lombok

Project Lombok helps _eliminate_ a lot of **boilerplate code** from your Java code.

**For example**. Define a POJO with several properties, then use Lombok Annotations to enhance the class with
Getters, Setters, Constructors, and Builders.

### Implementation

1. Add lombok dependency

```xml

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <scope>provided</scope>
    <version>1.18.22</version>
</dependency>
```

> **NOTE:** The latest stable release
> of [Lombok](https://central.sonatype.com/artifact/org.projectlombok/lombok/1.18.22) is available from the Maven
> Central Repository.

2. Update _annotationProcessorPaths_ with lombok and lombok-mapstruct binding.

```xml

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.5.1</version>
    <configuration>
        <annotationProcessorPaths>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.22</version>
            </path>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok-mapstruct-binding</artifactId>
                <version>0.2.0</version>
            </path>
            <path>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>1.5.3.Final</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

> **NOTE:** This implementation needs `maven-compiler-plugin` with version 3.5.1 or higher.

3. Update POJO getter and setter with Lombok Annotations `@getter` and `@setter` or `@Data`.
4. Trigger Mapstruct processing by executing an `mvn clean install` command. This will generate the implementation class
   under _/target/generated-sources/annotations/_.