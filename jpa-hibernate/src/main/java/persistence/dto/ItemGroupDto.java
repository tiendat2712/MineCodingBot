package persistence.dto;

import java.util.Objects;

public class ItemGroupDto {
	
	public static String PROP_IG_ID = "igID";
	public static String PROP_IG_NAME = "name";
	public static String PROP_TOTAL_OF_ITEMS = "totalOfItems";
	public static String PROP_ITEMS = "items";

	private Integer igID;
	private String name;
	private Long totalOfItems;
	private String items;

	/**
	 * Empty constructor
	 */
	public ItemGroupDto() {
	}

	public Integer getIgID() {
		return igID;
	}

	public void setIgID(Integer igID) {
		this.igID = igID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTotalOfItems() {
		return totalOfItems;
	}

	public void setTotalOfItems(Long totalOfItems) {
		this.totalOfItems = totalOfItems;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIgID());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof ItemGroupDto)) {
			return false;
		}

		ItemGroupDto that = (ItemGroupDto) o;
		return getIgID() == that.getIgID();
	}

	@Override
	public String toString() {
		return "ItemGroupDto [igID=" + igID + ", name=" + name + ", totalOfItems=" + totalOfItems + ", items=" + items
				+ "]";
	}
}
