package com.example.springbootdemo.extend.security;

import com.example.springbootdemo.security.dao.PermissionDao;
import com.example.springbootdemo.security.dao.UserDao;


import com.example.springbootdemo.security.po.Permission;
import com.example.springbootdemo.security.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyibo on 17/2/7.
 */
@Service
public class UrlUserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 校验方式，添加权限
     *
     * @param userName
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String userName) {
        //重写loadUserByUsername 方法获得 userdetails 类型用户
        User user = userDao.getByUserName(userName);
        if (user != null) {
            List<Permission> permissions = permissionDao.getByUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {
                    GrantedAuthority grantedAuthority = new UrlGrantedAuthority(permission.getPermissionUrl(),permission.getMethod());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            user.setGrantedAuthorities(grantedAuthorities);
            return user;
        } else {
            throw new UsernameNotFoundException("admin: " + userName + " do not exist!");
        }
    }
}