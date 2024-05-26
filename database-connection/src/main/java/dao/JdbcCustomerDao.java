package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DbConnection;
import persistence.Customer;
import persistence.ItemGroup;
import utils.SqlUtils;

public class JdbcCustomerDao implements CustomerDao{
	
	private static final String Q_INSERT_CUSTOMER = ""
			+ "INSERT INTO customer(ID, NAME, EMAIL, ADDRESS, PHONE, PASSWORD)"
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String Q_GET_CUSTOMER_ACC_INFO = ""
			+ "SELECT *"
			+ "   FROM customer"
			+ "   WHERE EMAIL = ? AND PASSWORD = ?";

	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public JdbcCustomerDao() {
		connection = DbConnection.getConnection();
	}

	@Override
	public void save(Customer customer) {
		try {
			pst = connection.prepareStatement(Q_INSERT_CUSTOMER);
			pst.setInt(1, customer.getId());
			pst.setString(2, customer.getName());
			pst.setString(3, customer.getEmail());
			pst.setString(4, customer.getAddress());
			pst.setString(5, customer.getPhone());
			// mã hóa password theo thuật toán md5
			// lưu mã hóa mật khẩu vào database
			pst.setString(6, SqlUtils.md5(customer.getPassword()));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtils.close(rs, pst);
		}
	}
	
	@Override
	public Customer login(String email, String password) {
		Customer result = null;
		try {
			pst = connection.prepareStatement(Q_GET_CUSTOMER_ACC_INFO);
			pst.setString(1, email);
			pst.setString(2, SqlUtils.md5(password)); // Mã hóa mật khẩu để so sánh với mk trong database
			rs = pst.executeQuery();

			while (rs.next()) {
				result = new Customer();
				result.setId(rs.getInt("ID"));
				result.setName(rs.getString("NAME"));
				result.setEmail(rs.getString("EMAIL"));
				result.setAddress(rs.getString("ADDRESS"));
				result.setPhone(rs.getString("PHONE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtils.close(rs, pst);
		}
		return result;
	}
}
