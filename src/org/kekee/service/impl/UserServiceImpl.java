package org.kekee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.kekee.service.UserService;
import org.kekee.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.kekee.entity.Page;
import org.kekee.entity.Role;
import org.kekee.entity.User;

/**
 * @author cocoa
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User login(String name, String pass) {
        // TODO Auto-generated method stub
        User u = userMapper.login(name);
        if (u != null && u.getUserPassword().equals(pass)) {
            return u;
        }
        return null;
    }

    @Override
    public List<User> findAllUser() {
        // TODO Auto-generated method stub
        return userMapper.findAllUser();
    }

    @Override
    public User findUserById(int id) {
        // TODO Auto-generated method stub
        return userMapper.findUserById(id);
    }

    @Override
    public List<Role> findAllRole() {
        // TODO Auto-generated method stub
        return userMapper.findAllRole();
    }

    @Override
    public boolean userAdd(User u) {
        // TODO Auto-generated method stub
        if (userMapper.userAdd(u) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean userModify(User u) {
        // TODO Auto-generated method stub
        if (userMapper.userModify(u) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String findCode(String userCode) {
        // TODO Auto-generated method stub
        if (userMapper.findCode(userCode) != null) {
            return "exist";
        }
        return "noexist";
    }

    @Override
    public String userDel(int id) {
        // TODO Auto-generated method stub
        if (userMapper.findUserById(id) != null) {
            if (userMapper.userDel(id) > 0) {
                return "true";
            }
            return "false";
        }
        return "notexist";
    }

    @Override
    public List<User> findByNameAndRole(String userName, Integer userRole) {
        // TODO Auto-generated method stub
        return userMapper.findByNameAndRole(userName, userRole);
    }

    @Override
    public boolean pwdModify(int id, String newpassword) {
        // TODO Auto-generated method stub
        if (userMapper.pwdModify(id, newpassword) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int findUserCount(String userName, Integer userRole) {
        // TODO Auto-generated method stub
        return userMapper.findUserCount(userName, userRole);
    }

    @Override
    public List<User> findUserByCount(String userName, Integer userRole,
                                      Page page) {
        page.setSize(5);
        page.setTotalCount(userMapper.findUserCount(userName, userRole));
        // TODO Auto-generated method stub
        return userMapper.findUserByCount(userName, userRole, (page.getCurrentpage() - 1) * page.getSize(), page.getSize());

    }

}
