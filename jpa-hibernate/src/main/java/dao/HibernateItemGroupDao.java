package dao;

import java.util.List;

import persistence.entities.ItemGroup;

public class HibernateItemGroupDao extends BaseHibernateDao implements ItemGroupDao{
	
	// native query: viết query ở ngôn ngữ mysql/oracle
	
	// jpql/hql    : viết query ở dạng entities/ attribute
	//             : jpa/hibernate tự chuyển đổi sang native db
	
	// name query: native/jpql/hql
	// thay vì truyền native/jpql/hql vào
	// khai báo cho sql đó một cái name --> truyền name vào là xong
	// khai báo ở chỗ entity
	
//	private static final String Q_GET_ALL_ITEM_GROUPS = "" 
//	        + "SELECT * FROM item_group";
	
	private static final String Q_GET_ALL_ITEM_GROUPS_JPQL = "" 
    + "FROM ItemGroup";

//  --> Sử dụng native query
//	@Override
//	public List<ItemGroup> getAll() {
//		// Từng dòng trả về từ SQL sẽ được mapping vào entity
//		// Muốn map được, entity phải mapping được với columns của sql result
//		return openSession()
//					.createNativeQuery(Q_GET_ALL_ITEM_GROUPS, ItemGroup.class)
//				    .getResultList();
//	}
	
//  --> Sử dụng jpql/hql
//	@Override
//	public List<ItemGroup> getAll() {
//		return openSession()
//					.createQuery(Q_GET_ALL_ITEM_GROUPS_JPQL, ItemGroup.class)
//				    .getResultList();
//	}
	
	@Override
	public List<ItemGroup> getAll() {
		return openSession()
					.createNamedQuery("DemoNameQuery", ItemGroup.class)
				    .getResultList();
	}
	

}
