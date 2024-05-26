package persistence.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "size")
public class Size {
	
	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "SIZE")
	private String size;
	
	@Column(name = "GENDER")
	private Boolean gender;
	
	@Column(name = "DESC")
	private String desc;
	
	public Size() {
	}

	public Size(Integer id, String size, Boolean gender, String desc, List<ItemDetail> itemDetails) {
		this.id = id;
		this.size = size;
		this.gender = gender;
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof Size)) {
			return false;
		}

		Size that = (Size) o;
		return getId() == that.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return "Size [id=" + id + ", size=" + size + ", gender=" + gender + ", desc=" + desc + "]";
	}

}
