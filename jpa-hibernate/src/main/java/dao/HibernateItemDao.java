package dao;

import java.util.List;

import persistence.entities.Item;

public class HibernateItemDao extends BaseHibernateDao implements ItemDao {
	
	private static final String Q_GET_ALL_ITEMS = ""
			+ "SELECT * FROM item";
	
	@Override
	public List<Item> getAll() {
		return openSession()
				  .createNativeQuery(Q_GET_ALL_ITEMS, Item.class)
				  .getResultList();
	}
	
	@Override
	public Item get(int id) {
		return openSession().get(Item.class, id);
	}

}
