package persistence.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item_group")
@NamedQueries(
      @NamedQuery(
    		      name = "DemoNameQuery", 
                  query = "From ItemGroup")		
)
public class ItemGroup {
	
	@Id
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	// "group" --> bắt buộc trùng với thuộc tính ItemGroup group ở bảng con Item!!
	// @OneToMany --> mặc định fetchType = LAZY;
	
	// Muốn dùng được @OneToMany thì trước đó phải có @ManyToOne
	// Còn @ManyToOne thì không bắt buộc
	
	@OneToMany(mappedBy = "group")
	private List<Item> items;
	
	/*
	 * Hibernate required empty constructor
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
