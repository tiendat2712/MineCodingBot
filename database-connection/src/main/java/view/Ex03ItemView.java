package view;
import static utils.CollectionUtils.*;

import service.ItemService;
import service.itemServiceImpl;

public class Ex03ItemView {

	private static ItemService itemService;
	
	static {
		itemService = new itemServiceImpl();
	}
	
	public static void main(String[] args) {
		generate("1. Display all items", itemService.getAll());
	
	
	
	}
	
}
