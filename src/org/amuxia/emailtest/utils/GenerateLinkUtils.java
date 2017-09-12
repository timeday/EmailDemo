package org.amuxia.emailtest.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletRequest;

import org.amuxia.emailtest.pojo.User;

/**
 * 
 * @ClassName: GenerateLinkUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhangHongWei
 * @date 2017年9月12日 上午10:24:29
 *
 */
public class GenerateLinkUtils
{

    private static final String CHECK_CODE = "checkCode";

    public static String generateActivateLink(User user)
    {
        return "http://localhost:8080/EmailPass/ActivateServlet?id="
                + user.getId() + "&" + CHECK_CODE + "="
                + generateCheckcode(user);
    }

    /**
     * 生成校验码，用户名+UUID唯一标识符，为安全把他们加密发送
     * 
     * @param user
     * @return
     */
    public static String generateCheckcode(User user)
    {
        String userName = user.getUsername();
        String randomCode = user.getCodeUrl();
        return md5(userName + ":" + randomCode);
    }

    /**
     * 接收回来的校验码和发送出去的是不是同一份
     * 
     * @param user
     * @param request
     * @return
     */
    public static boolean verifyCheckcode(User user, ServletRequest request)
    {
        String checkCode = request.getParameter(CHECK_CODE);
        System.out.println(generateCheckcode(user).equals(checkCode));
        return true;
    }

    private static String md5(String string)
    {
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("md5");
            md.update(string.getBytes());
            byte[] md5Bytes = md.digest();
            return bytes2Hex(md5Bytes);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            System.out.println("md5这里出错了");
        }

        return null;
    }

    private static String bytes2Hex(byte[] byteArray)
    {
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++)
        {
            if (byteArray[i] >= 0 && byteArray[i] < 16)
            {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
        }
        return strBuf.toString();
    }

}