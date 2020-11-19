package com.my.servlet;

import com.my.dao.DaoFactory;
import com.my.entity.Admin;
import com.my.entity.Student;
import com.my.entity.Teacher;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        String checkcode = request.getParameter("vcode");
        System.out.println("用户名："+userName+"密码："+password+"验证码"+checkcode);//控制台输出账号密码
        if(StringUtils.isBlank(checkcode) || StringUtils.isBlank(userName) || StringUtils.isBlank(password) || StringUtils.isBlank(type)){
            request.setAttribute("error","录入信息不能为空！");
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }

        String key = (String) request.getSession().getAttribute("key");
        if(key != null){
            key = key.toUpperCase();
            checkcode = checkcode.toUpperCase();
            response.setContentType("text/html;charset=UTF-8");
        }
        if(checkcode.equals(key)){
            System.out.println("验证码输入正确！");
        }else {
            request.setAttribute("error","验证码输入错误！");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

		HttpSession session = request.getSession();
        if (StringUtils.isNotBlank(type)) {
            try {
                if ("0".equals(type)) {
                    //学生登录验证
                    Student student = DaoFactory.getInstance().getStudentDao().login(userName, password);
					if(student != null){
						session.setAttribute("user",student);
						session.setAttribute("type",type);
						response.sendRedirect("index.jsp");
					}else{
						request.setAttribute("error","用户名或密码错误！");
						request.getRequestDispatcher("login.jsp").forward(request,response);
					}
                } else if ("1".equals(type)) {
                    //老师登录验证
					Teacher teacher = DaoFactory.getInstance().getTeacherDao().login(userName,password);
					if(teacher != null){
						session.setAttribute("user",teacher);
						session.setAttribute("type",type);
						response.sendRedirect("index.jsp");
					}else{
						request.setAttribute("error","用户名或密码错误！");
						request.getRequestDispatcher("login.jsp").forward(request,response);
					}
                } else {
                    //管理员登录验证
                    Admin admin = new Admin();
                    admin.setUserName(userName);
                    admin.setPwd(password);
                    Admin entity = DaoFactory.getInstance().getAdminDao().login(admin);
                    if (entity != null) {
                        //执行跳转
						session.setAttribute("user",entity);
						session.setAttribute("type",type);
						response.sendRedirect("index.jsp");
                    } else {
                        //用户或密码错误!
						request.setAttribute("error","用户名或密码错误！");
						request.getRequestDispatcher("login.jsp").forward(request,response);
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
