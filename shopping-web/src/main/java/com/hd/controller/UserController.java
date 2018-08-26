package com.hd.controller;

import com.github.pagehelper.PageInfo;
import com.hd.entity.User;
import com.hd.service.UserService;
import com.hd.util.Pager;
import com.hd.util.SecurityUtil;
import com.sun.rowset.internal.Row;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Resource(name = "userService")
    private UserService userService;
    @RequestMapping("/user/toLogin")
    public String login(){

        return "redirect:/jsp/pre/login.jsp";
    }
    @RequestMapping("/user/login")
    public @ResponseBody Map login(String loginName, String password, HttpSession session){
        Map<String,Object>map = new HashMap<>();
        User user = userService.getUserByUserName(loginName);
        if (user == null || !user.getPassword().equals(SecurityUtil.getHexStr(password))){
            map.put("message","对不起，密码错误，请重新输入");
        }else {
            session.setAttribute("loginUser",user);
            map.put("status",1);
        }
        return map;
    }
    @RequestMapping("/user/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/index.action";
    }
@RequestMapping("/user/toRegister")
    public String register(){
        return "redirect:/jsp/pre/register.jsp";
    }
    @RequestMapping("/user/register")
    public @ResponseBody Object register(User user){
        Map map = new HashMap();
        Integer integer = userService.addUser(user);
        if (integer > 0){
            map.put("status",1);
            map.put("message","注册成功，请登录");
        }else {
            map.put("status",0);
            map.put("message","用户名已存在，请重新输入");

        }
        return map;
    }

    /**
     * 会员中心中用户信息查询
     * @param model
     * @return
     */
    @RequestMapping("/sys/userInfo")
    public String userInfo(Model model){
        model.addAttribute("menu",2);
        return "jsp/backend/user/userInfo";
    }

    /**
     *查询用户列表
     * @param model
     * @return
     */
    @RequestMapping("/sys/queryUserList")
    public String queryUserList(Integer currentPage, Model model){
        if (currentPage == null){
            currentPage = 1;
        }
       Integer pageSize = 20;
        List<User>userList = userService.getUserList(currentPage, pageSize);
        PageInfo<User>pageInfo = new PageInfo<>(userList);
        String url = "sys/queryUserList.action?pageSize=5";

        Pager pager = new Pager(currentPage,pageInfo.getPages(),url);
        model.addAttribute("pager",pager);
        model.addAttribute("menu",8);
        model.addAttribute("userList",userList);

        return "jsp/backend/user/userList";
    }

    /**
     * 跳转到添加用户页面
     * @return
     */
    @RequestMapping("/sys/toAddUser")
    public String toAddUser(){
        return "jsp/backend/user/toUpdateUser";
    }
@RequestMapping("/sys/toUpdateUser")
    public String toUpdateUser(Integer id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "jsp/backend/user/toUpdateUser";
    }
    /**
     * 维护用户的信息（添加和修改）
     * @param user
     * @return
     */
    @RequestMapping("sys/maintainUser")
    public @ResponseBody Object maintainUser(User user){
        Map map = new HashMap();
        Integer row = -1;
        if (user.getId() == null || user.getId().equals("")){
           row= userService.addUser(user);
        }else {
           row = userService.updateUser(user);
        }
        map.put("status", row);
        return map;
    }
    /**
     * 通过id删除用户
     * @param id
     * @return
     */
    @RequestMapping("/sys/deleteUserById")
    public @ResponseBody Object deleteUserById(Integer id){
        Map map = new HashMap();
        Integer status = userService.deleteUserById(id);
        map.put("status",status);
        return map;
    }
}
