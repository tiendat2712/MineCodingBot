package dao;

import java.util.List;

import persistence.entities.Item;

public interface ItemDao {

	List<Item> getAll();
	
	/**
	 * get items by ID
	 * @param id
	 * @return item
	 */
	Item get(int id);
}
