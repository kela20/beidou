package com.beidou.gateway.config;

import com.beidou.common.util.StringUtil;
import com.beidou.gateway.entity.Role;
import com.beidou.gateway.entity.Rule;
import com.beidou.gateway.entity.User;
import com.beidou.gateway.service.UserService;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class MyShiroRealm extends AuthorizingRealm {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);


    @Autowired
    private UserService userService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份(登录认证)
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());

        // 从数据库获取对应用户名密码的用户
        User user = userService.login(username);
        if(user==null){
            throw new UnknownAccountException("用户名错误！");
        }
        // 用户为禁用状态
        if (user.getStatus()==0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        if(user.getPwd()!= StringUtil.encryptByMD5(password+user.getSalt())){
            throw new IncorrectCredentialsException("密码错误！");
        }
        logger.info("---------------- Shiro 凭证认证成功 ----------------------");
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                user.getPwd(), //密码
                getName()  //realm name
        );
        return authenticationInfo;


        
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof User) {
            User user = (User) principal;


            // 获取用户角色集
            List<Role> roleList = user.getRoles();
            Set<String> roleSet = roleList.stream().map(Role::getRolename).collect(Collectors.toSet());
            authorizationInfo.setRoles(roleSet);
            // 获取用户权限集
            List<Rule> permissionList=new ArrayList<>();
            for(Role role:roleList){
                for(Rule rule:role.getPermissions()){
                    permissionList.add(rule);
                }
            }
            Set<String> permissionSet = permissionList.stream().map(Rule::getPermissions).collect(Collectors.toSet());
            authorizationInfo.setStringPermissions(permissionSet);

        }
        logger.info("---- 获取到以下权限 ----");
        logger.info(authorizationInfo.getStringPermissions().toString());
        logger.info("---------------- Shiro 权限获取成功 ----------------------");
        return authorizationInfo;
    }

}