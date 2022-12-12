package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FetchReqArgs_05_CookieValueAnnotation {

    /**
     * @RequestHeader 用法同 @RequestParam
     */
    // 执行当前，请求响应头会有cookie： Set-Cookie: JSESSIONID=01454BDE39EF15998FB5FA7FFA63AA0C; Path=/; HttpOnly
    // 后续请求发送会带当前cookie。
    @RequestMapping("/testCreateSessionCookieAndStoreToBrowser")
    public String testServletAPI(HttpServletRequest httpServletRequest) {    // 使用Servlet原生接口获取当前请求的请求参数
        HttpSession session = httpServletRequest.getSession();  // HttpSession - 这里将会 Set-Cookie: JSESSIONID=01454BDE39EF15998FB5FA7FFA63AA0C; Path=/; HttpOnly
        System.out.println("Creating session cookie to browser, session value:" + session);
        return "success";
    }

    // 执行完上面第一条pre-condition后，执行这条，请求头会带header：Cookie: JSESSIONID=769D3E95CD894DFAB89B7B055B2FE867
    @RequestMapping("/testCookieValueTagString")
    public String testRequestParamTag(@CookieValue("JSESSIONID") String jSessionIDCookie) { // String类型
        System.out.println("Cookie中的JSESSIONID值为：" + jSessionIDCookie); // Cookie中的JSESSIONID值为：769D3E95CD894DFAB89B7B055B2FE867
        return "success";
    }

    // 执行完上面第一条pre-condition后，执行这条，请求头会带header：Cookie: JSESSIONID=769D3E95CD894DFAB89B7B055B2FE867
    @RequestMapping("/testCookieValueTagCookie")
    public String testCookieValueTagCookie(@CookieValue("JSESSIONID") Cookie cookie) {  // Cookie类型
        System.out.println("Cookie中的JSESSIONID值为：" + cookie.getValue()); // Cookie中的JSESSIONID值为：769D3E95CD894DFAB89B7B055B2FE867
        return "success";
    }

    @RequestMapping("/testCookieValueTagRequired")
    public String testRequestParamTagRequired(@CookieValue(value = "NotExistCookieValue", required = false) String notExistCookieValue) {
        System.out.println("notExistCookieValue：" + notExistCookieValue); // notExistCookieValue：null
        return "success";
    }

    @RequestMapping("/testCookieValueTagDefaultValue")
    public String testRequestParamTagDefaultValue(@CookieValue(value = "NotExistCookieValue", required = false, defaultValue = "hello") String notExistCookieValue) {
        System.out.println("notExistCookieValue：" + notExistCookieValue); // NotExistHeader：hello
        return "success";
    }

    @RequestMapping("/testCookieValueTagDefaultValue2")
    public String testRequestParamTagDefaultValue2(@CookieValue(value = "notExistCookieValue", required = false, defaultValue = "hello") String notExistCookieValue) {
        System.out.println("notExistCookieValue：" + notExistCookieValue); // 调用传CookieValue空字符串(notExistCookieValue=）的输出：notExistCookieValue：hello
        return "success";
    }
}
