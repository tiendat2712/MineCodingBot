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
	public void saveOrUpdate(ItemGroup itemGroup) {
		if (itemGroup == null) {
			Objects.requireNonNull(itemGroup, "item-group should not be null !");
		}
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
		if (itemGroup == null) {
			Objects.requireNonNull(itemGroup, "item-group should not be null !");
		}
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
	
	/* 
	 * --> CHUYỂN QUA DÙNG BATCH UPDATE
	 * @Override
	public void saveAll(Collection<ItemGroup> groups) {
		if (groups == null) {
			Objects.requireNonNull(groups, "item-group should not be null !");
		}
		if (!groups.isEmpty()) {
			for (ItemGroup group : groups) {
				save(group); // void save(ItemGroup itemGroup);
			}
			
			 Cứ 1 item group -> gọi 1 hàm save(service) -> save(dao)
			 -> tạo ra 1 preparedStatement, executeUpdate, open/close connection java liên tục
			 JAVA: host
			 MySQL: host
			 --> bị ảnh hưởng bởi yếu tố network
			 --> mạng có vấn đề thì hàm này sẽ bị chậm 
			
			 fix --> JDBC --> batch update
			 --> update 1 lần 1 đống đối tượng vào database --> hạn chế open/close connection
		}
	} */

	@Override
	public void update(ItemGroup itemGroup) {
		if (itemGroup == null) {
			Objects.requireNonNull(itemGroup, "item-group should not be null !");
		}
		itemGroupDao.update(itemGroup);
	}

	
}
