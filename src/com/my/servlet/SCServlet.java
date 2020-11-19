package com.my.servlet;

import com.my.dao.DaoFactory;
import com.my.entity.Course;
import com.my.entity.StuCourse;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sc")
public class SCServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String method = request.getParameter("method");
    if ("select".equals(method)) {
      this.select(request, response);
    } else if ("submit".equals(method)) {
      this.submit(request, response);
    } else if ("tc".equals(method)) {
      this.teacher_course(request, response);
    } else if ("cs".equals(method)) {
      this.course_student(request, response);
    }
  }
  
  //选课的学生查询
  private void course_student(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //1、获取cid的参数
    Integer cId = getInParameter(request, "cid");
    //2、获取学生列表
    try {
      List<Student> list = DaoFactory.getInstance().getScDao().listStudentByCId(cId);
      request.setAttribute("list", list);
      request.getRequestDispatcher("page/sc/course_student.jsp").forward(request, response);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    //3、return返回页面
  }
  
  private void teacher_course(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //1、获取老师的session中的对象tId
    Teacher teacher = (Teacher) request.getSession().getAttribute("user");
    Integer pageNo = getInParameter(request, "pageNo");
    Course course = new Course();
    course.setTeacher(teacher);
    PageInfo<Course> pageInfo = new PageInfo<>(pageNo);
    //2、查询所教课程列表
    try {
      pageInfo = DaoFactory.getInstance().getCourseDao().list(course, pageInfo);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    //3、将信息返回到jsp页面
    request.setAttribute("pageInfo", pageInfo);
    request.getRequestDispatcher("page/sc/teacher_course.jsp").forward(request, response);
  }
  
  //保存选课
  private void submit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] cIds = request.getParameterValues("cId");
    List<Integer> cIdArray = new ArrayList<Integer>();
    for (String string : cIds) {
      cIdArray.add(Integer.parseInt(string));
    }
    Student student = (Student) request.getSession().getAttribute("user");
    try {
      int[] arr = DaoFactory.getInstance().getScDao().add(cIdArray, student.getStuId());
      if (arr.length == 0) {
        response.sendRedirect(PathUtils.getBasePath(request) + "sc?method=select&msg=0");
      } else {
        response.sendRedirect(PathUtils.getBasePath(request) + "sc?method=select&msg=1");
      }
    } catch (SQLException e) {
      response.sendRedirect(PathUtils.getBasePath(request) + "sc?method=select&msg=0");
    }
  }
  
  //选课跳转
  private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PageInfo<Course> info = new PageInfo<>(1);
    info.setPageSize(1000);
    try {
      info = DaoFactory.getInstance().getCourseDao().list(null, info);
      //获取登录的学生信息
      Student student = (Student) request.getSession().getAttribute("user");
      //获取已选课的课程ID（cId）
      List<StuCourse> list = DaoFactory.getInstance().getScDao().listByStuId(student.getStuId());
      request.setAttribute("scs", list);
      request.setAttribute("courses", info.getList());
      request.getRequestDispatcher("page/sc/select.jsp").forward(request, response);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public Integer getInParameter(HttpServletRequest request, String name) {
    if (StringUtils.isNoneBlank(request.getParameter(name))) {
      return Integer.parseInt(request.getParameter(name));
    } else {
      return null;
    }
  }
}
