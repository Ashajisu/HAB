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


    public void insert(MemberDto memberDto, Role role) {
        Member member = memberDto.toEntity(bCryptEncoder);
        Auth auth = memberDto.toAuth(role);
        log.info("member, auth : {}, {}, ", member,auth);
        userRepository.save(member);
        authRepository.save(auth);
    }
    @Override
    public SecurityUser login(MemberDto memberDto) {
        Member db = userRepository.findOneById(memberDto.getId());
        log.info("db : {}", db);

        if(db.getEnabled()=='N'){ log.info("탈퇴한 계정입니다"); return null;}

//        Member user = memberDto.toEntity(bCryptEncoder);
        if(!bCryptEncoder.matches(memberDto.getPassword(),db.getPassword())){
            log.info("패스워드가 다릅니다");
            return null;
        }

        log.info("패스워드가 일치합니다");
        Role role = authRepository.findOneById(memberDto.getId()).getRole();
        String token = tokenProvider.createToken(String.format("%s:%s", memberDto.getId(), role));

        return new SecurityUser(memberDto.getId(),token,role);
    }

    public Member select(String username) {
        Member select = userRepository.findOneById(username);
        if(select.getEnabled() =='Y') return select;
        else return null;
    }

    public Auth isUser(String username) {
        return authRepository.findOneById(username);
    }

    @Override
    public void changePw(MemberDto memberDto) {
        Member member = userRepository.findOneById(memberDto.getId());
        if(member !=null) userRepository.save(memberDto.toEntity(bCryptEncoder));
        log.info("changed {}", memberDto);
    }

    @Override
    public void quit(MemberDto memberDto) {
        Member quit = memberDto.toQuit(bCryptEncoder);
        userRepository.save(quit);
        log.info("quit {}",quit);
    }


}
