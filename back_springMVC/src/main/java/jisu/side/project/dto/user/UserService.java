package jisu.side.project.dto.user;

import jisu.side.project.dto.Role;

public interface UserService {
    void insert(UserDto userDto, Role role);
    void changePw(UserDto userDto);

    User select(String username);
    User isUser(String username);
//    User isAdmin(String username);




}
