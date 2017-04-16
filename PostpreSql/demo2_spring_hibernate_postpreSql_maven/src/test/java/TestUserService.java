import com.jason.demo.domain.User;
import com.jason.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by cjs on 2017/4/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-application.xml",
        "classpath:spring-hibernate.xml"})
public class TestUserService {

    @Autowired
    private IUserService userService;

    @Test
    public void saveUser(){
        User user = new User();
        user.setName("jason");
        user.setSex(0);
        user.setAge(15);
        user.setBirthDay(new Date());
        Integer id = userService.addUser(user);
        System.out.println(id);
    }
}
