package org.amuxia.emailtest.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.amuxia.emailtest.pojo.User;
import org.amuxia.emailtest.utils.EmailUtils;
import org.amuxia.emailtest.utils.MyJDBC;

public class RegistServlet extends HttpServlet
{

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of the object.
     */
    public RegistServlet()
    {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy()
    {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to get.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to
     * post.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        request.setCharacterEncoding("utf-8");

        response.setCharacterEncoding("utf-8");

        response.setHeader("Content-type", "text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String codeUrl = UUID.randomUUID().toString();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setActivated(false); // 刚注册默认是没有激活状态
        String SQL = "insert into tb_user(username,password,email,activated,codeUrl) value (?,?,?,?,?) ";
        MyJDBC.insert(SQL, false, username, password, email, 0, codeUrl);// 注册信息插入数据库
        String querySQL = "select * from tb_user where email=?";
        ResultSet rs = MyJDBC.query(querySQL, email);
        try
        {
            if (rs.next())
            {
                user.setId(rs.getInt(1));
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 注册成功后,发送帐户激活链接
        request.getSession().setAttribute("user", user);
        EmailUtils.sendAccountActivateEmail(user);
        request.getRequestDispatcher("/WEB-INF/jsp/successs.jsp").forward(
                request, response);

    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException
     *             if an error occurs
     */
    public void init() throws ServletException
    {
        // Put your code here
    }

}
