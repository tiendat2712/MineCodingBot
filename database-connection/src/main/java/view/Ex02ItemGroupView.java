package view;

import service.ItemGroupService;
import service.ItemGroupServiceImpl;
import persistence.ItemGroup;
import static utils.CollectionUtils.*;

import java.util.List;

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
		var itemGroupToBeSaved1 = new ItemGroup(14, "Loại hàng 14");
		var itemGroupToBeSaved2 = new ItemGroup(15, "Loại hàng 15");
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
		
	}
	
}
