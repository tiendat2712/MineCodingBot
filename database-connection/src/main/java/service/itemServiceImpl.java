package service;

import java.time.LocalDate;
import java.util.List;
import dao.ItemDao;
import dao.JdbcItemDao;
import persistence.Item;
import persistence.dto.ItemDetailDto;

public class itemServiceImpl implements ItemService {

	private ItemDao itemDao; 
	
	public itemServiceImpl() {
		itemDao = new JdbcItemDao();
	}
	
	@Override
	public List<Item> getAll() {
		return itemDao.getAll();
	}
	
	@Override
	public List<Item> getItemsBySalesDate(LocalDate date) {
		if (date == null) {
			return List.of();
		}
		return itemDao.getItemsBySalesDate(date);
	}
	
	@Override
	public List<ItemDetailDto> getItemDetails() {
		return itemDao.getItemDetails();
	}
	
	@Override
	public void updateItemDetails() {
		itemDao.updateItemDetails();
	}
	
}
