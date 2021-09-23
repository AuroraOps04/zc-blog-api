package club.auroraops04.auroraops04_blog.mapper;

import club.auroraops04.auroraops04_blog.vo.request.UserListFilterForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testListByCondition(){
        UserListFilterForm userListFilterForm = new UserListFilterForm();
        userListFilterForm.setUsername("s");
        userMapper.listByCondition(userListFilterForm).forEach(System.out::println);
    }
}
