package com.my.dao;

import com.my.entity.Teacher;
import com.my.utils.PageInfo;
import com.my.utils.PropertiesUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao {
    public void add(Teacher teacher) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "insert into teacher(tName,userName,pwd) values(?,?,?)";
        queryRunner.update(sql, teacher.gettName(), teacher.getUserName(),teacher.getPwd());
    }

    public void delete(Integer tId) throws SQLException{
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "delete from teacher where tId = ?";
        queryRunner.update(sql,tId);
    }

    public void update(Teacher teacher) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "update teacher set tName = ?,username = ? where tId = ?";
        queryRunner.update(sql, teacher.gettName(),teacher.getUserName(),teacher.gettId());
    }

    public PageInfo<Teacher> list(Teacher teacher,PageInfo<Teacher> pageInfo) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String _sql = "";
        List<Object> _list = new ArrayList<Object>();
        if(teacher != null && teacher.gettId() != null){
            _sql += " and TID = ?";
            _list.add(teacher.gettId());
        }
        if(teacher != null && StringUtils.isNoneBlank(teacher.gettName())){
            _sql += " and TNAME like ?";
            _list.add("%"+teacher.gettName()+"%");
        }
        if(teacher != null && StringUtils.isNoneBlank(teacher.getUserName())){
            _sql += " and USERNAME like ?";
            _list.add("%"+teacher.getUserName()+"%");
        }
        //_list转数组
        Object[] arr = new Object[_list.size()];
        for (int i=0;i<_list.size();i++) {
            arr[i] = _list.get(i);
        }
        String sql = "select * from teacher where 1=1 "+_sql+" limit "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
        List<Teacher> list = queryRunner.query(sql, new BeanListHandler<>(Teacher.class),arr);
        pageInfo.setList(list);
        pageInfo.setTotalCount(this.count(teacher));
        return pageInfo;

    }
    public Long count(Teacher teacher) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String _sql = "";
        List<Object> _list = new ArrayList<Object>();
        if(teacher != null && teacher.gettId() != null){
            _sql += " and TID = ?";
            _list.add(teacher.gettId());
        }
        if(teacher != null && StringUtils.isNoneBlank(teacher.gettName())){
            _sql += " and TNAME like ?";
            _list.add("%"+teacher.gettName()+"%");
        }
        if(teacher != null && StringUtils.isNoneBlank(teacher.getUserName())){
            _sql += " and USERNAME like ?";
            _list.add("%"+teacher.getUserName()+"%");
        }
        //_list转数组
        Object[] arr = new Object[_list.size()];
        for (int i=0;i<_list.size();i++) {
            arr[i] = _list.get(i);
        }
        String sql = "select count(*) from teacher where 1=1"+_sql;
        Long count = (Long)queryRunner.query(sql, new ScalarHandler(),arr);
        return count;

    }

    public Teacher findById(Integer tId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "select * from teacher where tId = ?";
        Teacher teacher = queryRunner.query(sql, new BeanHandler<>(Teacher.class),tId);
        return teacher;
    }
    public Teacher login(String userName,String pwd) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
        String sql = "select * from teacher where userName = ? and pwd = ?";
        Teacher teacher = queryRunner.query(sql, new BeanHandler<>(Teacher.class),userName,pwd);
        return teacher;
    }
}
