package dao;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import persistence.dto.ItemGroupDto;
import persistence.entities.ItemGroup;

public class HibernateItemGroupDao extends BaseHibernateDao implements ItemGroupDao{
	
	private static final String Q_GET_ALL_ITEM_GROUPS = "" 
	        + "SELECT * FROM item_group";
	
	private static final String Q_GET_ITEM_GROUPS_BY_NAME = ""
			+ "SELECT * FROM item_group WHERE NAME = :pname";

	@Override
	public List<ItemGroup> getAll() {
		return openSession()
				   .createNativeQuery(Q_GET_ALL_ITEM_GROUPS, ItemGroup.class)
				   .getResultList();
	}
	
	@Override
	public ItemGroup get(int id) {
		return openSession().get(getEntityClass(), id);
	}
	
	@Override
	public ItemGroup get(String name) {
		return openSession()
				  .createNativeQuery(Q_GET_ITEM_GROUPS_BY_NAME, getEntityClass())
				  .setParameter("pname", name, StringType.INSTANCE)
				  .uniqueResult(); // null if not found
			   // .getSingleResult(); // javax.persistence.NoResultException
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------------------

	private static final String Q_COUNT_ITEMS_IN_ITEM_GROUP = ""
			+ "SELECT itg.ID " + ItemGroupDto.PROP_IG_ID + ",\n"
			+ "       itg.`NAME` " + ItemGroupDto.PROP_IG_NAME + ",\n"
			+ "       SUM(itd.AMOUNT) " + ItemGroupDto.PROP_TOTAL_OF_ITEMS + ",\n"
			+ "       group_concat(concat('{', it.NAME, ' - ', 'SizeID ',itd.SIZE_ID, ' -> ', itd.AMOUNT, '}') SEPARATOR ', ') " + ItemGroupDto.PROP_ITEMS + "\n"
			+ "  FROM item_group itg \n"
			+ "  JOIN item it \n"
			+ "    ON itg.ID = it.ITEM_GROUP_ID \n"
			+ "  JOIN item_detail itd \n"
			+ "    ON it.ID = itd.ITEM_ID \n"
			+ "  GROUP BY itg.ID, itg.`NAME`";

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<ItemGroupDto> countItemsByItemGroup() {
		return openSession()
				   .createNativeQuery(Q_COUNT_ITEMS_IN_ITEM_GROUP)
				   .addScalar(ItemGroupDto.PROP_IG_ID, IntegerType.INSTANCE)
				   .addScalar(ItemGroupDto.PROP_IG_NAME, StringType.INSTANCE)
				   .addScalar(ItemGroupDto.PROP_TOTAL_OF_ITEMS, LongType.INSTANCE)
				   .addScalar(ItemGroupDto.PROP_ITEMS, StringType.INSTANCE)
				   .setResultTransformer(Transformers.aliasToBean(ItemGroupDto.class))
				   .getResultList();
	}
	
	@Override
	public void save(ItemGroup itemGroup) {
		executeWithTransaction(session -> session.save(itemGroup));
	}
	
	@Override
	public void saveOrUpdate(ItemGroup itemGroup) {
		executeWithTransaction(session -> session.saveOrUpdate(itemGroup));
	}
	
	private Class<ItemGroup> getEntityClass() {
		return ItemGroup.class;
	}
	
}



































