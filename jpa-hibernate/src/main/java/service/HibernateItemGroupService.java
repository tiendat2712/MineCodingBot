package service;

import java.util.List;

import dao.HibernateItemGroupDao;
import dao.ItemGroupDao;
import persistence.entities.ItemGroup;

public class HibernateItemGroupService implements ItemGroupService {
	
	private ItemGroupDao itemGroupDao;
	
	public HibernateItemGroupService() {
		itemGroupDao = new HibernateItemGroupDao();
	}
	
	@Override
	public List<ItemGroup> getAll() {
		return itemGroupDao.getAll();
	}
}
