package jisu.side.project.dto.user;

import jisu.side.project.dto.Auth;
import jisu.side.project.dto.AuthRepository;
import jisu.side.project.dto.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AuthRepository authRepository;
//    @Autowired
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void insert(UserDto userDto, Role role) {
//        authRepository.save(new Auth(userDto.getId(), Role.USER));
//        String encoded = bCryptPasswordEncoder.encode(userDto.getPassword());
//        userDto.setPassword(encoded);
        User user = userDto.toEntity();
        Auth auth = userDto.toAuth(role);
        log.info("user, auth : {}, {}, ", user,auth);
        userRepository.save(user);
        authRepository.save(auth);
    }

    public User select(String username) {
        User select = userRepository.findOneById(username);
        if(select.getEnabled() =='Y') return select;
        else return null;
    }

    public Auth isUser(String username) {
        return authRepository.findOneById(username);
    }

    @Override
    public void changePw(UserDto userDto) {
//        String encoded = bCryptPasswordEncoder.encode(userDto.getPassword());
//        userDto.setPassword(encoded);
        User user = userRepository.findOneById(userDto.getId());
        if(user!=null) userRepository.save(userDto.toEntity());
        log.info("changed {}",userDto);
    }

    @Override
    public void quit(UserDto userDto) {
        User quit = userDto.toQuit();
        userRepository.save(quit);
        log.info("quit {}",quit);
    }



//    public User isAdmin(String username) {
//        User select = userRepository.findOneById(username);
//        Auth auth = authRepository.findOneById(username);
//        Role role = (auth != null) ? auth.getRole() : null;
//
//        if(select.getEnabled() =='Y' && role==Role.ADMIN) return select;
//        else return null;
//    }

}
