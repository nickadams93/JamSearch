package auth;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jamsearch.auth.WebApplication;
import com.jamsearch.auth.model.User;
import com.jamsearch.auth.repository.UserRepository;
import com.jamsearch.auth.service.UserService;
import com.jamsearch.auth.web.UserController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.security.Principal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class, UserController.class, UserRepository.class, UserService.class})
@AutoConfigureMockMvc
public class MockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @MockBean
    private Model model;

    @MockBean
    private Principal principal;

    @Test
    public void shouldReturnWelome() throws Exception {
        User user = new User();
        user.setMessage("test");
        when(principal.getName()).thenReturn("testName");

        Assert.assertEquals(userController.welcome(model, principal), "welcome");

    }

    @Test
    public void messageCreation() throws Exception {
        mockMvc.perform(get("/messageCreation"));
        Assert.assertEquals(userController.messageCreation(model), "messageCreation");

    }
}