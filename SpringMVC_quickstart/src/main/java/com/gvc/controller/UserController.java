package com.gvc.controller;

import com.gvc.domain.QueryVo;
import com.gvc.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/user")//一级访问目录
@SessionAttributes("username") //向request域中（model）存入key为username时，将同步到session域中
public class UserController {

    /*
        path属性等同于value
        method = RequestMethod.POST : 只能以post的请求方式访问该方法，其它请求方式会报错
        params：用来限定请求参数的条件  params={"accountName"} 表示请求参数中必须有accountName
    */
    @RequestMapping(path = "/quick",method = RequestMethod.GET, params = {"accountName"}) //二级访问目录
    public String quick(Integer id){
        //业务逻辑
        System.out.println("spring入门成功");
        //视图跳转
        return "success";
    }

    // 获取基本类型请求参数
    @RequestMapping("/simpleParam")
    public String simpleParam(Integer id, String username){
        System.out.println("id: " + id);
        System.out.println("username: " + username);
        return "success";
    }

    //获取对象请求类型参数
    @RequestMapping("/pojoParam")
    public String pojoParam(User user){
        System.out.println(user);
        return "success";
    }

    //获取数组请求类型参数
    @RequestMapping("/arrayParam")
    public String arrayParam(Integer[] ids){
        System.out.println(Arrays.toString(ids));
        return "success";
    }

    //获取复杂类型请求参数
    @RequestMapping("/queryParam")
    public String queryParam(QueryVo queryVo){
        System.out.println(queryVo);
        return "success";
    }

    //获取日期类型参数----自定义类型转换器
    @RequestMapping("/converterParam")
    public String converterParam(Date birthday){
        System.out.println(birthday);
        return "success";
    }

    /*@RequestParam属性
            name：前台传递参数的名称
            defaultValue: 参数的默认值
            required: 是否必须传递该参数，默认为true。但是如果设置了defaultValue，将自动变为false
    */
    @RequestMapping("/findByPage")
    public String findByPage(@RequestParam(name = "pageNo",defaultValue = "1",required = false)Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize){
        System.out.println("当前页面: " + pageNum);
        System.out.println("每页条数: " + pageSize);
        return "success";
    }

    //@RequestHeader演示
    @RequestMapping("/requestHeader")
    public String requestHeader(@RequestHeader("Cookie")String cookie){
        System.out.println(cookie);
        return "success";
    }

    //@CookieValue演示
    @RequestMapping("/cookieValue")
    public String cookieValue(@CookieValue("JSESSIONID")String jsessionId){
        System.out.println(jsessionId);
        return "success";
    }

    //原始servletAPI的获取
    @RequestMapping("/servletAPI")
    public String servletAPI(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
        return "success";
    }

    //通过原始servletAPI进行页面跳转
    @RequestMapping("/returnVoid")
    public void returnVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //直接返回数据
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("我是牛人");

        //request请求转发
//        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);

        //response重定向为两次请求： WEB-INF为安全目录，不允许外部请求直接访问，只允许服务器内部转发
//        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    //forward:关键字请求转发
    @RequestMapping("/forward")
    public String forward(Model model){
        //模型中设置值
        model.addAttribute("username","Forward转发:_J1vyC");
        //使用请求转发，既可以转发jsp,也可以转发到其他的Controller方法
        return "forward:/WEB-INF/pages/success.jsp";
//        return "forward:/product/findAll";
    }

    //@SeesionAttributes：多个请求之间共享数据
    @RequestMapping("/returnString")
    public String returnString(Model model){
       return "success";
    }

    //redirect:关键字重定向，当使用redirect或forward关键字时不会调用视图解析器拼接前后缀
    @RequestMapping("/redirect")
    public String redirect(Model model){
        //底层使用的仍是 request.setAttribute(),request域范围为一次请求,而重定向为两次请求大于request域范围
        model.addAttribute("username","redirect重定向:_GVCheng");
        return "redirect:/index.jsp";
    }

    //ModelAndView 页面跳转方式一：手动创建 ModelAndView 对象
    @RequestMapping("/returnModelAndView")
    public ModelAndView returnModelAndView(){
        //model模型：封装存放数据
        //view视图：展示数据
        ModelAndView modelAndView = new ModelAndView();
        //设置数据模型
        modelAndView.addObject("username","模型视图 方式一");
        //设置视图名称：视图解析器解析modelAndView 拼接前缀和后缀
        modelAndView.setViewName("success");
        return modelAndView;
    }

    //ModelAndView 页面跳转方式二：形参声明 ModelAndView
    @RequestMapping("/returnModelAndView2")
    public ModelAndView returnModelAndView2(ModelAndView modelAndView){
        modelAndView.addObject("username","模型视图 方式二");
        modelAndView.setViewName("success");
        return modelAndView;
    }

}
