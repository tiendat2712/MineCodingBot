package persistence;

import java.util.List;
import java.util.Objects;

public class ItemGroup {
	private Integer id;
	private String name;
	
	// item-group 1-N item
	private List<Item> items;

	/**
	 * Empty constructor
	 * 
	 */
	public ItemGroup() {
	}
	
	public ItemGroup(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof ItemGroup)) {
			return false;
		}

		ItemGroup that = (ItemGroup) o;
		return getId() == that.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return "ItemGroup [id=" + id + ", name=" + name + "]";
	}

	

}
