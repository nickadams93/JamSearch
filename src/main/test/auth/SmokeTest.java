package auth;
import static org.assertj.core.api.Assertions.assertThat;

import com.jamsearch.auth.WebApplication;
import com.jamsearch.auth.web.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class, UserController.class})
public class SmokeTest {

    @Autowired
    private UserController userController;

    @Test
    public void contexLoads() throws Exception {
        assertThat(userController).isNotNull();
    }
}