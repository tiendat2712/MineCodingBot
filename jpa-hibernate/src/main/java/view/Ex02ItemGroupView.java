package view;

import service.HibernateItemGroupService;
import service.ItemGroupService;

import static utils.CollectionUtils.*;

import java.util.Collection;

import persistence.entities.Item;
import persistence.entities.ItemGroup;

public class Ex02ItemGroupView {

	private static ItemGroupService itemGroupService;
	
	static {
		itemGroupService = new HibernateItemGroupService();
	}
	
	public static void main(String[] args) {
		System.out.println("--------------------------------------------------");
		
		selfGenerate(
				"1a. Display all item-groups", itemGroupService.getAll()
				);
		
		generate(
				"1b. Display item-group by id = 2", itemGroupService.get(2)
				);
		
		generate(
				"1b*. Display item-group by name = 'Mũ'", itemGroupService.get("Mũ")
				);
		
		generate(
				"5.Count amount of items in an item-groups", itemGroupService.countItemsByItemGroup()
				);
		
	}
	
	public static void selfGenerate(String prefix, Collection<ItemGroup> elements) {
		System.out.println(prefix + " --> {");
		
		elements.forEach(e -> {
			System.out.println("   " + e);
			System.out.println("   " + e.getItems());
			System.out.println("   --> amount of item: " +e.getItems().size() +" \n");
		});
		
		System.out.println("}\n");
	}
}
