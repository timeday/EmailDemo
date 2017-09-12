package org.amuxia.emailtest.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.amuxia.emailtest.pojo.User;

/**
 * @author
 */
public class EmailUtils
{

    private static final String FROM = "自己的163邮箱";

    public static void sendAccountActivateEmail(User user)
    {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);
        try
        {
            message.setSubject("这是一封激活账号的邮件，复制链接到地址栏来激活他");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(RecipientType.TO,
                    new InternetAddress(user.getEmail()));
            message.setContent("<a  target='_BLANK' href=''>"
                    + GenerateLinkUtils.generateActivateLink(user) + "</a>",
                    "text/html;charset=utf-8");
            Transport.send(message);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Session getSession()
    {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(FROM, "客户端密码,非邮箱密码");
            }
        });
        return session;
    }
}