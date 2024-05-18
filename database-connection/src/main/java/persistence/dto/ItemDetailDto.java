package persistence.dto;

import java.util.Objects;

import persistence.ItemGroup;

public class ItemDetailDto {
 
	private Integer itemId;
	private String itemName;
	private Integer size;
	private Integer amount;
	private ItemGroup group;
	
	/*
	 * Empty constructor
	 */
	public ItemDetailDto() {
	}

	public ItemDetailDto(Integer itemId, String itemName, Integer size, Integer amount, ItemGroup group) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.size = size;
		this.amount = amount;
		this.group = group;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public ItemGroup getGroup() {
		return group;
	}

	public void setGroup(ItemGroup group) {
		this.group = group;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof ItemDetailDto)) {
			return false;
		}

		ItemDetailDto that = (ItemDetailDto) o;
		return getItemId().equals(that.getItemId()) 
				&& getSize().equals(that.getSize())
				&& getGroup().getId().equals(that.getGroup().getId());

	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getItemId(), getSize(), getGroup().getId());
	}

	@Override
	public String toString() {
		return "ItemDetailDto [itemId=" + itemId + ", itemName=" + itemName + ", size=" + size + ", amount=" + amount
				+ ", group=" + group + "]";
	}

}
