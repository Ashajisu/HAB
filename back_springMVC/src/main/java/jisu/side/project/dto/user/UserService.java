package jisu.side.project.dto.user;

import jisu.side.project.dto.Auth;
import jisu.side.project.dto.Role;
import jisu.side.project.security.SecurityUser;

public interface UserService {
    void insert(UserDto userDto, Role role);
    void changePw(UserDto userDto);

    User select(String username);
    Auth isUser(String username);
    void quit(UserDto userDto);
    SecurityUser login(UserDto userDto);
}
