<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html>  
    <head>  

    <title>注册</title>  
    </head>  

    <body>  
        <form action="/servlet/RegistServlet" method="post">  
            用户名:<input type="text" name="username"><br/>  
            密码:<input type="password" name="password"><br/>  
            邮箱:<input type="text" name="email"><br/>  
            <input type="submit" value="注册">  
       </form>  
    </body>  
    </html>  