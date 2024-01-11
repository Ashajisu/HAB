package jisu.side.project.dto.user;

import jisu.side.project.dto.Auth;
import jisu.side.project.dto.Role;
import jisu.side.project.security.SecurityUser;

public interface UserService {
    void insert(MemberDto memberDto, Role role);
    void changePw(MemberDto memberDto);

    Member select(String username);
    Auth isUser(String username);
    void quit(MemberDto memberDto);
    SecurityUser login(MemberDto memberDto);
}
