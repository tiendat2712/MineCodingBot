package dao;

import java.util.Collection;
import java.util.List;
import persistence.ItemGroup;

public interface ItemGroupDao {
	
	List<ItemGroup> getAll();
	
	ItemGroup getById(int id);
	
	/**
	 * Insert item-group
	 * @param random item-group input
	 */
	void save(ItemGroup itemGroup);
	
	/**
	 * Insert list of item-groups to database
	 * --> Using batch update(INSERT/ UPDATE/ DELETE)
	 * @param groups
	 */
	void saveAll(Collection<ItemGroup> groups);

	
	/**
	 * Update item-group
	 * --> update group NAME by ID
	 * @param the given item-group
	 */
	void update(ItemGroup itemGroup);
}
