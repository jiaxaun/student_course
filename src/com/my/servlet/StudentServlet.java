package com.my.servlet;

import com.my.dao.DaoFactory;
import com.my.entity.Student;
import com.my.utils.PageInfo;
import com.my.utils.PathUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if("list".equals(method)){
            this.list(request, response);
        }else if("add".equals(method)){
            this.add(request,response);
        }else if("edit".equals(method)){
            this.findById(request,response);
        }else if("editSubmit".equals(method)){
            this.editSubmit(request,response);
        }else if("delete".equals(method)){
            this.delete(request,response);
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        try {
            DaoFactory.getInstance().getStudentDao().delete(Integer.parseInt(id));
            //直接重定向到列表页面
            response.sendRedirect(PathUtils.getBasePath(request)+"student?method=list");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void editSubmit(HttpServletRequest request, HttpServletResponse response){
        Integer stuId = Integer.parseInt(request.getParameter("stuId"));
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        Student student = new Student();
        student.setStuName(stuName);
        student.setStuNo(stuNo);
        student.setStuId(stuId);
        try {
            DaoFactory.getInstance().getStudentDao().update(student);
            //直接重定向到列表页面
            response.sendRedirect(PathUtils.getBasePath(request)+"student?method=list");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void findById(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        try {
            Student student = DaoFactory.getInstance().getStudentDao().findById(Integer.parseInt(id));
            request.setAttribute("student",student);
            request.getRequestDispatcher("page/student/update.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void add(HttpServletRequest request, HttpServletResponse response){
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
            response.sendRedirect(PathUtils.getBasePath(request)+"student?method=list");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void list(HttpServletRequest request, HttpServletResponse response){
        //当前页码
        Integer pageNo = getInParameter(request,"pageNo");
        Integer stuId = getInParameter(request,"stuId");
        String stuName = request.getParameter("stuName");
        String stuNo = request.getParameter("stuNo");

        Student student = new Student();
        student.setStuId(stuId);
        student.setStuName(stuName);
        student.setStuNo(stuNo);

        //构造了一个pageInfo对象
        PageInfo<Student> pageInfo = new PageInfo<>(pageNo);
        try {
            pageInfo = DaoFactory.getInstance().getStudentDao().list(student,pageInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            request.setAttribute("pageInfo",pageInfo);
            //回写到页面
            request.setAttribute("student",student);
            request.getRequestDispatcher("page/student/list.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Integer getInParameter(HttpServletRequest request,String name){
        if(StringUtils.isNoneBlank(request.getParameter(name))){
            return Integer.parseInt(request.getParameter(name));
        }else{
            return null;
        }
    }


}
