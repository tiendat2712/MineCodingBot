package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import connection.DbConnection;
import persistence.ItemGroup;
import persistence.dto.ItemGroupDto;
import utils.SqlUtils;

public class JdbcItemGroupDao implements ItemGroupDao {

	private static final int BATCH_SIZE = 50;
	
	private Connection connection;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	private static final String Q_GET_ALL_ITEM_GROUPS = "" 
	        + "SELECT * FROM item_group";
	
	private static final String Q_GET_ITEM_GROUPS_BY_ID = "" 
	        + "SELECT * FROM item_group WHERE ID = ?";
	
	private static final String Q_GET_ITEM_GROUPS_BY_NAME = "" 
	        + "SELECT * FROM item_group WHERE NAME = ?";
	
	private static final String Q_INSERT_ITEM_GROUPS = ""
			+ "INSERT INTO item_group(ID, NAME) \n" 
			+ "VALUES (?, ?)";
	
	private static final String Q_UPDATE_ITEM_GROUPS = ""
			+ "UPDATE item_group \n"
			+ "SET NAME = ? \n"
			+ "WHERE ID = ?";
	
	private static final String Q_COUNT_ITEMS_IN_ITEM_GROUP = ""
			+ "SELECT itg.ID, \n"
			+ "       itg.`NAME`, \n"
			+ "       SUM(itd.AMOUNT) SUM_CURRENT_AMOUNT, \n"
			+ "       group_concat(concat('{', it.NAME, ' - ', 'SizeID ',itd.SIZE_ID, ' -> ', itd.AMOUNT, '}') SEPARATOR ', ') DETAIL_INFO \n"
			+ "  FROM item_group itg \n"
			+ "  JOIN item it \n"
			+ "    ON itg.ID = it.ITEM_GROUP_ID \n"
			+ "  JOIN item_detail itd \n"
			+ "    ON it.ID = itd.ITEM_ID \n"
			+ "  GROUP BY itg.ID, itg.`NAME`";
	
	private static final String Q_MERGE_ITEM_GROUP = ""
			+ "CALL P_MERGE_INTO_ITEM_GROUP(?, ?)";
	
	
	/**
	 * Constructor
	 */
	public JdbcItemGroupDao() {
		connection = DbConnection.getConnection();
	}

	@Override
	public List<ItemGroup> getAll() {
		List<ItemGroup> result = new ArrayList<>();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(Q_GET_ALL_ITEM_GROUPS);

			while (rs.next()) {
				ItemGroup ig = new ItemGroup();
				ig.setId(rs.getInt("ID"));
				ig.setName(rs.getString("NAME"));
				result.add(ig);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtils.close(rs, st);
		}
		return result;
	}
	
	@Override
	public ItemGroup getById(int id) {
		ItemGroup result = null;
		try {
			pst = connection.prepareStatement(Q_GET_ITEM_GROUPS_BY_ID);	
			// '1' --> thay dấu ? đầu tiên trong Q_GET_ITEM_GROUPS_BY_ID bằng id
			// setInt --> vì dấu ? đó cần cho KDL int
			pst.setInt(1, id);
		    rs = pst.executeQuery();
			// vì có 1 đối tượng trả về nên có thể dùng if thay cho while
			if (rs.next()) {
				result = new ItemGroup(rs.getInt("ID"), rs.getString("NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtils.close(rs, pst);
		}
		return result;		
	}
	
	@Override
	public ItemGroup getByName(String name) {
		ItemGroup result = null;
		try {
			pst = connection.prepareStatement(Q_GET_ITEM_GROUPS_BY_NAME);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = new ItemGroup(rs.getInt("ID"), rs.getString("NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtils.close(rs, pst);
		}
		return result;
	}

	@Override
	public void save(ItemGroup itemGroup) {
		try {
			pst = connection.prepareStatement(Q_INSERT_ITEM_GROUPS);
			pst.setInt(1, itemGroup.getId());
			pst.setString(2, itemGroup.getName());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtils.close(rs, pst);
		}
	}
	
	@Override
	public void saveAll(Collection<ItemGroup> groups) {
		try {
			int batchCount = 0;
			pst = connection.prepareStatement(Q_INSERT_ITEM_GROUPS);
			for(ItemGroup group : groups) {
				pst.setInt(1, group.getId());
				pst.setString(2, group.getName());
				pst.addBatch();
				
	            batchCount++;
	            // Nếu batch chứa đủ BATCH_SIZE phần tử thì execute batch và reset batchCount
	            if (batchCount % BATCH_SIZE == 0) {
	                pst.executeBatch();
	                batchCount = 0;
	            }
			}
			// execute all hoặc phần còn lại trong batch
			pst.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtils.close(rs, pst);
		}
	}
	
	@Override
	public List<ItemGroupDto> countItemsByItemGroup() {
		List<ItemGroupDto> result = new ArrayList<>();
		try {
			pst = connection.prepareStatement(Q_COUNT_ITEMS_IN_ITEM_GROUP);
			rs = pst.executeQuery();

			while (rs.next()) {
				ItemGroupDto ig = new ItemGroupDto();
				ig.setIgID(rs.getInt("ID"));
				ig.setName(rs.getString("NAME"));
				ig.setTotalOfItems(rs.getLong("SUM_CURRENT_AMOUNT"));
				ig.setItems(rs.getString("DETAIL_INFO"));
				result.add(ig);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtils.close(rs, st);
		}
		return result;
	}

	@Override
	public void update(ItemGroup itemGroup) {
		try {
			pst = connection.prepareStatement(Q_UPDATE_ITEM_GROUPS);
			pst.setString(1, itemGroup.getName());
			pst.setInt(2, itemGroup.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtils.close(rs, pst);
		}
	}
	
	@Override
	public void merge(ItemGroup itemGroup) {
		try {
			pst = connection.prepareCall(Q_MERGE_ITEM_GROUP);
			
			pst.setInt(1, itemGroup.getId());
			pst.setString(2, itemGroup.getName());
			
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
