package com.my.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.my.entity.Admin;
import com.my.utils.PropertiesUtils;
/**
 * 会使用DBUtils工具类
 * @author lenovo
 *
 */
public class AdminDao {

	public void add(Admin admin) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into Admin(userName,pwd,name) values(?,?,?)";
		queryRunner.update(sql, admin.getUserName(), admin.getPwd(),admin.getName());
	}

	public void delete(Integer id) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from Admin where id = ?";
		queryRunner.update(sql,id);
	}

	public void update(Admin admin) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update Admin set userName = ?,pwd = ?,name = ? where id = ?";
		queryRunner.update(sql, admin.getUserName(), admin.getPwd(),admin.getName(),admin.getId());
	}

	public List<Admin> list(Admin admin) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from Admin";
		List<Admin> list = queryRunner.query(sql, new BeanListHandler<>(Admin.class));
		return list;
	}

	public Admin findById(Integer id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from Admin where id = ?";
		Admin admin = queryRunner.query(sql, new BeanHandler<>(Admin.class),id);
		return admin;
	}
	public Admin login(Admin admin) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from Admin where userName = ? and pwd = ?";
		Admin entity = queryRunner.query(sql, new BeanHandler<>(Admin.class),admin.getUserName(),admin.getPwd());
		return entity;
	}
}
