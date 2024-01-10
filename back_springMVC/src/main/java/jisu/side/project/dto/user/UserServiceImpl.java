package jisu.side.project.dto.user;

import jisu.side.project.dto.Auth;
import jisu.side.project.dto.AuthRepository;
import jisu.side.project.dto.Role;
import jisu.side.project.security.SecurityUser;
import jisu.side.project.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AuthRepository authRepository;
    @Autowired
    private final PasswordEncoder bCryptEncoder;
    @Autowired
    private final TokenProvider tokenProvider;	// 추가


    public void insert(UserDto userDto, Role role) {
        User user = userDto.toEntity(bCryptEncoder);
        Auth auth = userDto.toAuth(role);
        log.info("user, auth : {}, {}, ", user,auth);
        userRepository.save(user);
        authRepository.save(auth);
    }
    @Override
    public SecurityUser login(UserDto userDto) {
        User db = userRepository.findOneById(userDto.getId());
        log.info("db : {}", db);

        if(db.getEnabled()=='N'){ log.info("탈퇴한 계정입니다"); return null;}

//        User user = userDto.toEntity(bCryptEncoder);
        if(!bCryptEncoder.matches(userDto.getPassword(),db.getPassword())){
            log.info("패스워드가 다릅니다");
            return null;
        }

        log.info("패스워드가 일치합니다");
        Role role = authRepository.findOneById(userDto.getId()).getRole();
        String token = tokenProvider.createToken(String.format("%s:%s", userDto.getId(), role));

        return new SecurityUser(userDto.getId(),token,role);
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
        User user = userRepository.findOneById(userDto.getId());
        if(user!=null) userRepository.save(userDto.toEntity(bCryptEncoder));
        log.info("changed {}",userDto);
    }

    @Override
    public void quit(UserDto userDto) {
        User quit = userDto.toQuit(bCryptEncoder);
        userRepository.save(quit);
        log.info("quit {}",quit);
    }


}
