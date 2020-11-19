package com.my.dao;

import com.my.entity.StuCourse;
import com.my.entity.Student;
import com.my.utils.PropertiesUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ScDao {
	public int[] add(List<Integer> cIdArray,Integer stuId) throws SQLException {
		DataSource dataSource = PropertiesUtils.getDataSource();
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(false);
		QueryRunner queryRunner = new QueryRunner(dataSource);
		String _sql = "delete from stucourse where stuId = ?";
		queryRunner.update(connection,_sql,stuId);
		Object[][] object = new Object[cIdArray.size()][2];
		//将cIdArray与stuId保存为二维数组
		for (int i=0;i<cIdArray.size();i++){
			object[i][0] = stuId;
			object[i][1] = cIdArray.get(i);
		}
		String sql = "insert into stucourse(stuId,cId) values(?,?)";
		int [] arr = queryRunner.batch(connection,sql,object);
		connection.commit();
		return arr;
	}

	public void delete(Integer scId) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from stucourse where scId = ?";
		queryRunner.update(sql,scId);
	}

	public void update(StuCourse sc) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update stucourse set score = ? where scId = ?";
		queryRunner.update(sql, sc.getScore(),sc.getScId());
	}

	public List<StuCourse> list(StuCourse sc) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from stucourse";
		List<StuCourse> list = queryRunner.query(sql, new BeanListHandler<>(StuCourse.class));
		return list;

	}
	public List<StuCourse> listByStuId(Integer stuId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from stucourse where stuId = ?";
		List<StuCourse> list = queryRunner.query(sql, new BeanListHandler<>(StuCourse.class),stuId);
		return list;

	}

	public StuCourse findById(Integer scId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from stucourse where scId = ?";
		StuCourse sc = queryRunner.query(sql, new BeanHandler<>(StuCourse.class),scId);
		return sc;
	}

	public List<Student> listStudentByCId(Integer cId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select student.* from stucourse,student where stucourse.stuId = student.stuId and cId = ?";
		List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class),cId);
		return list;

	}
}
