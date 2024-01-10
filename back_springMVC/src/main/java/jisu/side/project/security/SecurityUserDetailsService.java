package jisu.side.project.security;

import jisu.side.project.dto.AuthRepository;
import jisu.side.project.dto.Role;
import jisu.side.project.dto.user.User;
import jisu.side.project.dto.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**입력한 비밀번호를 비교하기 위한 실제 사용자 정보 반환(암호화된 비밀번호)
 * 패스워드 일치 여부를 확인하는 로직은 Spring Security 내부에서 처리 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User db = userRepository.findOneById(username);
        if(db==null){
            throw  new UsernameNotFoundException(username);
        }
        log.info("loadUserByUsername : {}", db);
        Role role = authRepository.findOneById(db.getId()).getRole();
        return new SecurityUser(db.getId(), db.getPassword(), role);
    }
}

