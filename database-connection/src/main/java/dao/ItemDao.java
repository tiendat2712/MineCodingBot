package dao;

import java.time.LocalDate;
import java.util.List;
import persistence.Item;

public interface ItemDao {
	
	//....
	List<Item> getAll();
	
	// get items by sale date
	List<Item> getItemsBySalesDate(LocalDate date);
    
}
