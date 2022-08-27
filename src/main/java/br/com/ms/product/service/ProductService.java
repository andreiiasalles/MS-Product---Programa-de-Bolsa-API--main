package br.com.ms.product.service;

import br.com.ms.product.controller.dto.ProductDto;
import br.com.ms.product.entity.Product;
import br.com.ms.product.exceptions.MethodArgumentNotValidException;
import br.com.ms.product.exceptions.ObjectNotFoundException;
import br.com.ms.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! " + id));
	}

	public List<Product> findAll() {
		return Optional.ofNullable(repository.findAll()).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found!"));
	}

	public Product create(ProductDto obj) {
		if (obj.getName() == null || obj.getDescription() == null || obj.getPrice() == null) {
			throw new MethodArgumentNotValidException("The field cannot be blank.");
		}
		Product transformProductDto = new Product(obj);
		repository.save(transformProductDto);
		return obj;
	}

	public Product update(Long id, ProductDto newObj) {
		Product Obj = findById(id);
		if (newObj.getName() == null || newObj.getDescription() == null || newObj.getPrice() == null) {
			throw new MethodArgumentNotValidException("The field cannot be blank.");
		}
		Obj.setName(newObj.getName());
		Obj.setPrice(newObj.getPrice());
		Obj.setDescription(newObj.getDescription());

		Product transformProductDto = new Product(newObj);
		repository.save(transformProductDto);
		return newObj;
	}

	public void deleteById(Long id) {
		Optional.of(repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found!")));
		repository.deleteById(id);
	}

	public List<Product> search(BigDecimal minPrice, BigDecimal maxPrice, String name) {
		List<Product> findProducts = repository.findProduct(minPrice, maxPrice, name);
		if (findProducts.isEmpty()) {
			throw new ObjectNotFoundException("Products not found");
		}
		return findProducts;
	}
}
	

		
	

	

	
