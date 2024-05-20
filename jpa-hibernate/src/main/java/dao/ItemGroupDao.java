package dao;

import java.util.List;

import persistence.dto.ItemGroupDto;
import persistence.entities.ItemGroup;

public interface ItemGroupDao {

	/**
	 * Get all item groups
	 * @return
	 */
	List<ItemGroup> getAll();
	
	/**
	 * Display item group by ID
	 */
	ItemGroup get(int id);
	
	/**
	 * Display item group by name
	 */
	ItemGroup get(String name);
	
	/**
	 * count items in a item-group
	 */
	List<ItemGroupDto> countItemsByItemGroup();

}
