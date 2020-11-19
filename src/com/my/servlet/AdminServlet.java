package com.my.servlet;

import com.my.dao.DaoFactory;
import com.my.entity.Admin;
import com.my.entity.Student;
import com.my.utils.PathUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if("add".equals(method)){
            this.add(request, response);
        }

    }
    private void add(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        Admin admin = new Admin();
        admin.setUserName(userName);
        admin.setPwd(pwd);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
