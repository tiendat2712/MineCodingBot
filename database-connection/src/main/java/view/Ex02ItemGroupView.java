package view;

import service.ItemGroupService;
import service.ItemGroupServiceImpl;

public class Ex02ItemGroupView {

	private static ItemGroupService itemGroupService;
	
	static {
		itemGroupService = new ItemGroupServiceImpl();
	}
	
	public static void main(String[] args) {
		
		System.out.println("----------- Display all item groups -----------");
		
		var itemGroups = itemGroupService.getAll();
		
		itemGroups.forEach(System.out::println);
	}
	
}
