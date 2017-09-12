package org.amuxia.emailtest.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.amuxia.emailtest.pojo.User;
import org.amuxia.emailtest.utils.GenerateLinkUtils;
import org.amuxia.emailtest.utils.MyJDBC;

public class ActivateServlet extends HttpServlet
{

    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of the object.
     */
    public ActivateServlet()
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

        String idValue = request.getParameter("id");
        System.out.println(idValue);
        int id = -1;
        try
        {
            id = Integer.parseInt(idValue);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        String SQL = "select * from tb_user where id=?";
        ResultSet rs = MyJDBC.query(SQL, id);
        User user = new User();
        try
        {
            if (rs.next())
            {
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setActivated(rs.getBoolean(5));
                user.setCodeUrl(rs.getString(6));
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 验证无误，状态更改为1，即激活
        if (GenerateLinkUtils.verifyCheckcode(user, request))
        {
            String updSQL = "update tb_user set activated =1 where id=?";
            MyJDBC.execute(updSQL, id);
            user.setActivated(true);
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/jsp/pass.jsp").forward(
                    request, response);
        }

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
