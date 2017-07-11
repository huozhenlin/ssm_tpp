/**
 * Created by hzl on 2017/7/5.
 */

import com.cloudsoft.dao.IUserDao;
import com.cloudsoft.dao.UserMapper;
import com.cloudsoft.entity.User;
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
        User user=mapper2.checkRole("hzl");
        System.out.println(user.getRole());
        //User user=mapper2.selectById(11);
        //System.out.println(user.getEmail());

    }

}
