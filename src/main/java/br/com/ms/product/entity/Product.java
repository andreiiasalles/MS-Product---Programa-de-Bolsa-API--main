package br.com.ms.product.entity;

import br.com.ms.product.controller.dto.ProductDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Name field is required.")
	@NotNull
	@Column(nullable = false)
	private String name;
	@NotEmpty(message = "Description field is required.")
	@NotNull
	@Column(nullable = false)
	private String description;

	@NotEmpty(message = "Price field is required.")
	@NotNull
	@Column(nullable = false)
	private BigDecimal price;

	public Product() {}

	public Product(Long id, String name, String description, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Product(ProductDto product) {
		this.description = product.getDescription();
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product)) return false;
		Product product = (Product) o;
		return getId().equals(product.getId());
	}
	@Override
	public int hashCode() {

		return Objects.hash(getId());
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

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public BigDecimal getPrice() {

		return price;
	}

	public void setPrice(BigDecimal price) {

		this.price = price;
	}
}
