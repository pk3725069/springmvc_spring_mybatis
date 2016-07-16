package com.cn.hnust.controller;  
  
import javax.annotation.Resource;  
import javax.servlet.http.HttpServletRequest;  
  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  

import cn.itcast.mybatis.domain.User;
import cn.itcast.mybatis.service.IUserService;

@Controller  
@RequestMapping("/user")  
public class UserController {  
    @Resource  
    private IUserService userService;  
      
    @RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){  
        Integer userId = Integer.parseInt(request.getParameter("asd"));  
        User user = this.userService.get(userId);  
        model.addAttribute("user", user);  
        return "showName";  
    }  
}  