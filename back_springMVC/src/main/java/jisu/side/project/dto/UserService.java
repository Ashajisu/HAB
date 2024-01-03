package jisu.side.project.dto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AuthRepository authRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void insert(UserDto userDto, Role role) {
//        authRepository.save(new Auth(userDto.getId(), Role.USER));
        String encoded = bCryptPasswordEncoder.encode(userDto.getPassword());
        log.info("encoder : {}, ", encoded);
        userDto.setPassword(encoded);
        userRepository.save(userDto.toEntity(role));
    }

    public User select(String username) {
        User select = userRepository.findOneById(username);
        if(select.getEnabled() =='Y') return select;
        else return null;
    }

    public User isUser(String username) {
        User select = userRepository.findOneById(username);
        Auth auth = authRepository.findOneById(username);
        Role role = (auth != null) ? auth.getRole() : null;
        if(select.getEnabled() =='Y' && role==Role.USER) return select;
        else return null;
    }
    public User isAdmin(String username) {
        User select = userRepository.findOneById(username);
        Auth auth = authRepository.findOneById(username);
        Role role = (auth != null) ? auth.getRole() : null;

        if(select.getEnabled() =='Y' && role==Role.ADMIN) return select;
        else return null;
    }
}
