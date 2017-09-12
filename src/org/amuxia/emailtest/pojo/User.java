package org.amuxia.emailtest.pojo;

public class User
{
    private int id;
    private String username;
    private String password;
    private String email;
    private boolean activated;// 账号状态
    private String codeUrl;// 激活链接中的随机码

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean isActivated()
    {
        return activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    public String getCodeUrl()
    {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl)
    {
        this.codeUrl = codeUrl;
    }

    public User()
    {
        super();

    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", username=" + username + ", password="
                + password + ", email=" + email + ", activated=" + activated
                + ", codeUrl=" + codeUrl + "]";
    }

}