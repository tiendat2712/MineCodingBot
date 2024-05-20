package service;

import java.util.List;
import java.util.Objects;

import dao.HibernateItemDao;
import dao.ItemDao;
import persistence.entities.Item;

public class HibernateItemService implements ItemService {
	
	private ItemDao itemDao;
	
	public HibernateItemService() {
		itemDao = new HibernateItemDao();
	}
	
	@Override
	public List<Item> getAll() {
		return itemDao.getAll();
	}
	
	@Override
	public Item get(int id) {
		Objects.requireNonNull(id, "id should not be null");
		return itemDao.get(id);
	}

}
