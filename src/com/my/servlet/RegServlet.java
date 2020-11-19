package com.my.servlet;

import com.my.dao.DaoFactory;
import com.my.entity.Admin;
import com.my.entity.Student;
import com.my.entity.Teacher;
import com.my.utils.PathUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if("aAdd".equals(method)){
            this.aAdd(request, response);
        }else if("stuAdd".equals(method)){
            this.stuAdd(request, response);
        }else if("tAdd".equals(method)){
            this.tAdd(request, response);
        }
    }

    private void aAdd(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");
        Admin admin = new Admin();
        admin.setUserName(userName);
        admin.setPwd(pwd);
        admin.setName(name);
        try {
            DaoFactory.getInstance().getAdminDao().add(admin);
            //直接重定向到列表页面
            response.sendRedirect(PathUtils.getBasePath(request)+"login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void stuAdd(HttpServletRequest request, HttpServletResponse response){
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String stuPwd = request.getParameter("stuPwd");
        Student student = new Student();
        student.setStuName(stuName);
        student.setStuNo(stuNo);
        student.setStuPwd(stuPwd);
        try {
            DaoFactory.getInstance().getStudentDao().add(student);
            //直接重定向到列表页面

            response.sendRedirect(PathUtils.getBasePath(request)+"login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void tAdd(HttpServletRequest request, HttpServletResponse response){
        String tName = request.getParameter("tName");
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        Teacher teacher = new Teacher();
        teacher.settName(tName);
        teacher.setUserName(userName);
        teacher.setPwd(pwd);
        try {
            DaoFactory.getInstance().getTeacherDao().add(teacher);
            //直接重定向到列表页面
            response.sendRedirect(PathUtils.getBasePath(request)+"login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
