package service;

import java.util.List;

import persistence.dto.ItemGroupDto;
import persistence.entities.ItemGroup;

public interface ItemGroupService {

	List<ItemGroup> getAll();
	
	ItemGroup get(int id);
	
	ItemGroup get(String name);
	
	/**
	 * counting items in item-groups
	 *   + display the item-details info
	 */
	List<ItemGroupDto> countItemsByItemGroup();
}
