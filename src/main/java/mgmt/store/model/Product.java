package mgmt.store.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "product_name")
	private String name;
	
	@Column(name = "price")
	private Float price;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "availability")
	private String isAvailable;

	@ManyToMany(mappedBy = "products")
	private Set<Order> orders = new HashSet<>();
	
	public Product() {
		super();
	}
	
	public Product(String name, Float price, String description, String isAvailable) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.isAvailable = isAvailable;
	}
	
	public Product(Long id, String name, Float price, String description, String isAvailable) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.isAvailable = isAvailable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", isAvailable=" + isAvailable + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, isAvailable, name, orders, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(isAvailable, other.isAvailable) && Objects.equals(name, other.name)
				&& Objects.equals(orders, other.orders) && Objects.equals(price, other.price);
	}
}
