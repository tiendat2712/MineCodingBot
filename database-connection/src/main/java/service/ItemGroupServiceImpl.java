package service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import dao.ItemDao;
import dao.ItemGroupDao;
import dao.JdbcItemDao;
import dao.JdbcItemGroupDao;
import persistence.Item;
import persistence.ItemGroup;
import persistence.dto.ItemGroupDto;

public class ItemGroupServiceImpl implements ItemGroupService{

	private ItemGroupDao itemGroupDao;   
	private ItemDao itemDao;
	
	public ItemGroupServiceImpl() {
		itemGroupDao = new JdbcItemGroupDao();
		itemDao = new JdbcItemDao();
	}
	
	@Override
	public List<ItemGroup> getAll() {
		return itemGroupDao.getAll();
	}
	
	@Override
	public List<ItemGroup> getItemGroupsWithItems() {
		List<Item> items = itemDao.getAll(); // item --> item-group
		Map<ItemGroup, List<Item>> itemGroupItemsMap =  
				items.stream().collect(Collectors.groupingBy(Item::getGroup));
		
		return itemGroupItemsMap.entrySet()
		    .stream()
		    .map(e -> {
		    	ItemGroup itemGroup = e.getKey();
		    	itemGroup.setItems(e.getValue());
		    	return itemGroup;
		    })
		    .collect(Collectors.toList());
	}
	
	@Override
	public ItemGroup getById(int id) {
		return itemGroupDao.getById(id);
	}
	
	@Override
	public ItemGroup getByName(String name) {
		Objects.requireNonNull(name, "item-group name should not be null");
		return itemGroupDao.getByName(name);
	}
	
	@Override
	public List<ItemGroupDto> countItemsByItemGroup() {
		return itemGroupDao.countItemsByItemGroup();
	}
	
	@Override
	public void saveOrUpdate(ItemGroup itemGroup) {
		Objects.requireNonNull(itemGroup, "item-group should not be null !");
		Optional.ofNullable(getById(itemGroup.getId()))
		        .ifPresentOrElse(val -> update(itemGroup), () -> save(itemGroup));
	}

	@Override
	public void save(ItemGroup itemGroup) {
		Objects.requireNonNull(itemGroup, "item-group should not be null !");
		itemGroupDao.save(itemGroup);
	}
	
	// --> BATCH UPDATE IN DAO LAYER
	@Override
	public void saveAll(Collection<ItemGroup> groups) {
		Objects.requireNonNull(groups, "item-group should not be null !");
		if(!groups.isEmpty()) {
			itemGroupDao.saveAll(groups);
		}
	}

	@Override
	public void update(ItemGroup itemGroup) {
		Objects.requireNonNull(itemGroup, "item-group should not be null !");
		itemGroupDao.update(itemGroup);
	}

}	

