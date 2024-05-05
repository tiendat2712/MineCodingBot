package service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import dao.ItemGroupDao;
import dao.JdbcItemGroupDao;
import persistence.ItemGroup;

public class ItemGroupServiceImpl implements ItemGroupService{

	private ItemGroupDao itemGroupDao;   
	
	public ItemGroupServiceImpl() {
		itemGroupDao = new JdbcItemGroupDao();
	}
	
	@Override
	public List<ItemGroup> getAll() {
		return itemGroupDao.getAll();
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
	public void saveOrUpdate(ItemGroup itemGroup) {
		Objects.requireNonNull(itemGroup, "item-group should not be null !");
		Optional.ofNullable(getById(itemGroup.getId()))
		        .ifPresentOrElse(val -> update(itemGroup), () -> save(itemGroup));

//		       ----------- "EXPLAIN" -----------
//		if (getById(itemGroup.getId()) == null) {
//			save(itemGroup);
//		} else {
//			update(itemGroup);
//		}   
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

