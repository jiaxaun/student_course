package com.my.servlet;

import com.my.dao.DaoFactory;
import com.my.entity.Student;
import com.my.entity.Teacher;
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

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
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
            DaoFactory.getInstance().getTeacherDao().delete(Integer.parseInt(id));
            //直接重定向到列表页面
            response.sendRedirect(PathUtils.getBasePath(request)+"teacher?method=list");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void editSubmit(HttpServletRequest request, HttpServletResponse response){
        Integer tId = Integer.parseInt(request.getParameter("tId"));
        String tName = request.getParameter("tName");
        String userName = request.getParameter("userName");
        Teacher teacher = new Teacher();
        teacher.settId(tId);
        teacher.settName(tName);
        teacher.setUserName(userName);
        try {
            DaoFactory.getInstance().getTeacherDao().update(teacher);
            //直接重定向到列表页面
            response.sendRedirect(PathUtils.getBasePath(request)+"teacher?method=list");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void findById(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        try {
            Teacher teacher = DaoFactory.getInstance().getTeacherDao().findById(Integer.parseInt(id));
            request.setAttribute("teacher",teacher);
            request.getRequestDispatcher("page/teacher/update.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void add(HttpServletRequest request, HttpServletResponse response){
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
            response.sendRedirect(PathUtils.getBasePath(request)+"teacher?method=list");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void list(HttpServletRequest request, HttpServletResponse response){
        //当前页码
        Integer pageNo = getInParameter(request,"pageNo");
        //查询条件
        Integer tId = getInParameter(request,"tId");
        String tName = request.getParameter("tName");
        String userName = request.getParameter("userName");

        Teacher teacher = new Teacher();
        teacher.settName(tName);
        teacher.setUserName(userName);
        teacher.settId(tId);

        //构造了一个pageInfo对象
        PageInfo<Teacher> pageInfo = new PageInfo<>(pageNo);
        try {
            pageInfo = DaoFactory.getInstance().getTeacherDao().list(teacher,pageInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            request.setAttribute("pageInfo",pageInfo);
            //回写到页面
            request.setAttribute("teacher",teacher);
            request.getRequestDispatcher("page/teacher/list.jsp").forward(request,response);
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
