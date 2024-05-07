package view;

import service.ItemGroupService;
import service.ItemGroupServiceImpl;
import persistence.Item;
import persistence.ItemGroup;
import static utils.CollectionUtils.*;
import java.util.List;
import java.util.stream.Collectors;

public class Ex02ItemGroupView {

	private static ItemGroupService itemGroupService;
	
	static {
		itemGroupService = new ItemGroupServiceImpl();
	}
	
	public static void main(String[] args) {
		
		var itemGroupToBeTested1st = new ItemGroup(12, "Loại hàng 12");
		itemGroupService.saveOrUpdate(itemGroupToBeTested1st);
		
		var itemGroupToBeTested2nd = new ItemGroup(13, "Loại hàng 13");
		itemGroupService.saveOrUpdate(itemGroupToBeTested2nd);
		
		// TODO: saveOrUpdate list with BATCH UPDATE
		var itemGroupToBeSaved1 = new ItemGroup(16, "Loại hàng 16");
		var itemGroupToBeSaved2 = new ItemGroup(17, "Loại hàng 17");
		var groupToBeSaved = List.of(itemGroupToBeSaved1, itemGroupToBeSaved2);
		
		itemGroupService.saveAll(groupToBeSaved);
		
		generate(
				"1. Display all item groups", 
				itemGroupService.getAll()
		);
		
		generate(
				"2. Display item groups by ID = 1", 
				itemGroupService.getById(1)
		);
		
		generate(
				"3. Display item groups by NAME = ..", 
				itemGroupService.getByName("Thắt lưng")
		);
		
		System.out.println("4. Display all item-group with items");
		itemGroupService.getItemGroupsWithItems()
		.forEach(group -> {
			String itemNames = group.getItems()
					.stream()
					.map(Item::getName)
					.collect(Collectors.joining(", "));
			System.out.println(group + " ---> " + itemNames);
		});
		System.out.println();
		
		generate(
				"5. Counting items in item-group", 
				itemGroupService.countItemsByItemGroup()
		);
		
		System.out.println("----------------------------------------------------");
	}
	
}
