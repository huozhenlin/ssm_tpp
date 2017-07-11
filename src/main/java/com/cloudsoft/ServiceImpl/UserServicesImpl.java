package com.cloudsoft.ServiceImpl;

import com.cloudsoft.dao.IUserDao;
import com.cloudsoft.dao.UserMapper;
import com.cloudsoft.entity.User;
import com.cloudsoft.service.SendMail;
import com.cloudsoft.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * Created by hzl on 2017/3/29.
 */


//实现登录接口
//使用@Service注解，spring会自动给我们创建实例

@Service("UserService")
public class UserServicesImpl implements UserServices {

    //自动注入iuserdao用于访问数据库
    @Autowired
    private IUserDao Mapper;
    private User user;
    SendMail sendMail;

    //登录判断
    public boolean login(String username, String password) {

        try {
            user = Mapper.selectByNameandPass(username,password);
            System.out.println("输入的账号是" + username);
            System.out.println("输入的密码是" + password);
        }catch (Exception e){
            System.out.println("用户不存在");
            return false;
        }
        if(user!=null){
            if (user.getUsername().equals(username)&&
                user.getPassword().equals(password))
                System.out.println("验证成功");
                return true;
        }

        return false;

    }

    //增加用户,两个参数，username password
    public boolean addUser(String username, String password) {
        System.out.println("------------");
        System.out.println(username+password);
        Mapper.addUser(username,password);
        System.out.println("-++++++++-------");
        System.out.println("注册用户" + username);
        System.out.println("密码" + password);
        return true;
    }

    public boolean addUser(String username, String password, String email, String file) {
        return false;
    }

    //增加用户，三个参数，username password email
    public boolean addUser(String username, String password, String email) {
        System.out.println("------------");
        System.out.println(username+password+email);
        Mapper.addUser(username,password,email);
        System.out.println("-++++++++-------");
        System.out.println("注册用户" + username);
        System.out.println("密码" + password);
        System.out.println("email" + email);
        return true;

    }


    //查询用户是否已经存在
    public boolean isExist(String username) {

        user=Mapper.selectByName(username);
        if(user!=null) {
            return true;
        }else {
            return false;
        }
    }

    //通过邮箱找回密码
    public boolean findUser(String email) {
        user=Mapper.findPassword(email);
        if(user!=null){
            sendMail=new SendMail();
            String content="用户名为"+user.getUsername()+",你的密码是"+user.getPassword()+"如有疑问,请" +
                    "联系管理员:huo@sinaegg.cn";
            try {
                sendMail.send(content,email);
                System.out.println("邮件发送成功");
            } catch (MessagingException e) {
                System.out.println("邮件发送失败");
            }
            return true;

        }
        return false;
    }

    public boolean checkMail(String email) {
        user=Mapper.checkmail(email);
        if(user!=null){
            return true;
        }
        return false;
    }

    public int alter(String username, String password) {

        return Mapper.alter(username,password);

    }

    @Override
    public User checkRole(String username) {
        return Mapper.checkRole(username);
    }


}

