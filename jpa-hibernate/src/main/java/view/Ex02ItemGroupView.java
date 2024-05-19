package view;

import service.HibernateItemGroupService;
import service.ItemGroupService;

import static utils.CollectionUtils.*;

public class Ex02ItemGroupView {

	private static ItemGroupService itemGroupService;
	
	static {
		itemGroupService = new HibernateItemGroupService();
	}
	
	public static void main(String[] args) {
		System.out.println("--------------------------------------------------");
		generate(
				"1a. Display all item-groups", itemGroupService.getAll()
				);
		
		
	}
}
