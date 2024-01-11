package jisu.side.project.security;

import jisu.side.project.dto.Auth;
import jisu.side.project.dto.AuthRepository;
import jisu.side.project.dto.Role;
import jisu.side.project.dto.user.Member;
import jisu.side.project.dto.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
        Member db = userRepository.findOneById(username);
        if(db==null){
            throw  new UsernameNotFoundException(username);
        }
        log.info("loadUserByUsername : {}", db);
        Role role =  authRepository.findOneById(db.getId()).getRole();
//        return new SecurityUser(db.getId(), db.getPassword(), role);

        return toPrincipal(db,role);
    }


    private User toPrincipal(Member member, Role role){
        User principal
                = new org.springframework.security.core.userdetails.User(
                        member.getId(), member.getPassword(),Collections.singletonList(new SimpleGrantedAuthority(role.toString())));

        if(member.getEnabled()=='N'){
            principal.builder().disabled(false);
        }

        return principal;
    }
}

