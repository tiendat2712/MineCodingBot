package service;

import java.util.Collection;
import java.util.List;

import persistence.ItemGroup;
import persistence.dto.ItemGroupDto;

public interface ItemGroupService {
	// --
	List<ItemGroup> getAll();
	
	// --
	List<ItemGroup> getItemGroupsWithItems();
	
	// --
	ItemGroup getById(int id);
	
	// --
	ItemGroup getByName(String name);
	
	/**
	 * counting items in item-groups
	 *   + display the item-details info
	 */
	List<ItemGroupDto> countItemsByItemGroup();
	
	/**
	 * Check if the item-group already exists
	 * 'True' --> Update
	 * 'Else' --> Insert 
	 * @param random item-group input
	 */
	void saveOrUpdate(ItemGroup itemGroup);
	
	/**
	 * Insert item-group
	 * @param random item-group input
	 */
	void save(ItemGroup itemGroup);
	
	/**
	 * Update item-group
	 * --> update group NAME by ID
	 * @param the given item-group
	 */
	void update(ItemGroup itemGroup);
	
	
	/**
	 * Insert list of item-groups to database
	 * --> Using batch update()
	 * @param groups
	 */
	void saveAll(Collection<ItemGroup> groups);
}
