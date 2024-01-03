import jisu.side.project.Application;
import jisu.side.project.controller.UserController;
import jisu.side.project.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
//@ActiveProfiles("test") // 'test' 프로파일이 활성화된 환경에서 실행
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserController controller;

    @Test
    @Transactional
    public void testInsert() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setId("testUser");
        userDto.setPassword("testPassword");
        userDto.setEnabled('Y');

        Role role = Role.USER;

        // When
        controller.singUser(userDto, role);
        log.info(userDto.toString());


        // Then
        String auth = controller.whoIs(userDto.getId());
        log.info(auth);

    }
}
