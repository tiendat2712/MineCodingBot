package view;

import service.HibernateItemService;
import service.ItemService;
import java.util.Collection;

import static utils.CollectionUtils.*;

import persistence.entities.Item;

public class Ex03ItemView {

	private static ItemService itemService;
	
	static {
		itemService = new HibernateItemService();
	}
	
	public static void main(String[] args) {
		System.out.println("--------------------------------------------------");
		
		selfGenerate(
				"2ab. Display all items", itemService.getAll()
				);
		
		generate(
				"2c. Display item by ID = '3'", itemService.get(3)
				);
		
		

	}
	
	public static void selfGenerate(String prefix, Collection<Item> elements) {
		System.out.println(prefix + " --> {");
		
		elements.forEach(e -> {
			System.out.println("   " + e);
			System.out.println("   --> " +e.getGroup() +" \n");
		});
		
		System.out.println("}\n");
	}
	
}
