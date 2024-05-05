package service;

import java.util.List;
import dao.ItemDao;
import dao.JdbcItemDao;
import persistence.Item;

public class itemServiceImpl implements ItemService {

	private ItemDao itemDao; 
	
	public itemServiceImpl() {
		itemDao = new JdbcItemDao();
	}
	
	@Override
	public List<Item> getAll() {
		return itemDao.getAll();
	}

	
	
	
}
