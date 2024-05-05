package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DbConnection;
import persistence.Item;
import persistence.ItemGroup;
import utils.SqlUtils;

public class JdbcItemDao implements ItemDao {
 
	private static final String Q_GET_ALL_ITEMS = ""
			+ "SELECT it.*, \n"
			+ "       ig.ID IG_ID, \n"
			+ "       ig.NAME IG_NAME \n"
			+ "  FROM item it \n"
			+ "  JOIN item_group ig \n"
			+ "  ON it.ITEM_GROUP_ID = ig.ID";
	
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public JdbcItemDao() {
		connection = DbConnection.getConnection();
	}
	
	@Override
	public List<Item> getAll() {
		List<Item> result = new ArrayList<>();
		try {
			pst = connection.prepareStatement(Q_GET_ALL_ITEMS);
			rs = pst.executeQuery();
			while (rs.next()) {
				Item it = new Item();
				it.setId(rs.getInt("ID"));
				it.setName(rs.getString("NAME"));
				it.setMaterial(rs.getString("MATERIAL"));
				it.setBuyPrice(rs.getBigDecimal("BUY_PRICE"));
				it.setColor(rs.getString("COLOR"));
				// Quan tâm đến tên column(alias) khi query trả về
				ItemGroup group = new ItemGroup(rs.getInt("IG_ID"), rs.getString("IG_NAME"));
				it.setGroup(group);
				result.add(it);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtils.close(pst, rs);
		}
		return result;
	}
	
	

}
