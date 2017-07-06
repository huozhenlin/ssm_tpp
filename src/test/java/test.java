/**
 * Created by hzl on 2017/7/5.
 */

import com.cloudsoft.dao.IUserDao;
import com.cloudsoft.dao.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 注意这里是一种利用SqlBuilder以及多种设置来注解使用
 *
 * @author zxl
 */
public class test {

    private static ApplicationContext ac;
    static {
        ac=new ClassPathXmlApplicationContext("spring-mybatis.xml");
    }
    public static void main(String[] args){

        IUserDao mapper2= (IUserDao) ac.getBean("IUserDao");

        //mapper2.addUsers("123","123","1@qq.com","1.jpeg");
        //mapper2.findPassword("1031734796@qq.com");
        //mapper2.selectByName("cheng");
        int i=mapper2.alter("h3l","1234");
        System.out.println("sss"+i);
        //mapper2.addUser("huo12","dd","@qq");
//        UserMapper mapper= (UserMapper) ac.getBean("UserMapper");
//        mapper.updateUserByUsername("hzl")
    }

}
