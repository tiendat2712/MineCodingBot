package dao;

import java.time.LocalDate;
import java.util.List;
import persistence.Item;
import persistence.dto.ItemDetailDto;

public interface ItemDao {
	
	//....
	List<Item> getAll();
	
	// get items by sale date
	List<Item> getItemsBySalesDate(LocalDate date);
    
	//
	List<ItemDetailDto> getItemDetails();
	
	/*
	 * Test update item details
	 * 
	 * Using transaction 
	 */
	void updateItemDetails();
}
