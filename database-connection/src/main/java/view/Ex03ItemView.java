package view;

import static utils.CollectionUtils.*;
import java.time.LocalDate;
import service.ItemService;
import service.itemServiceImpl;

public class Ex03ItemView {

	private static ItemService itemService;
	
	static {
		itemService = new itemServiceImpl();
	}
	
	public static void main(String[] args) {
		generate("1. Display all items", itemService.getAll());
		
		generate("2. Displat items by sales date = '7-9-2023' ", 
				itemService.getItemsBySalesDate(LocalDate.of(2023, 9, 7)));
	
	
	
	}
	
}
