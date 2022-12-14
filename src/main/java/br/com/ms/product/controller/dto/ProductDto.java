package br.com.ms.product.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.ms.product.entity.Product;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDto extends Product {

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

	public ProductDto(){}
	public ProductDto(Long id, String name, String description, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public ProductDto(Product product) {

		this.description = product.getDescription();
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public static Page<ProductDto> transformProductDto(Page<Product> products){
		return products.map(ProductDto::new);

	}
}
