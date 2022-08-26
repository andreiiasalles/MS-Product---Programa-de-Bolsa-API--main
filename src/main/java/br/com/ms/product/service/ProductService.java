package br.com.ms.product.service;

import br.com.ms.product.controller.dto.ProductDto;
import br.com.ms.product.entity.Product;
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

	public Product findById(Long id){
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found!" + id +", Tipo: " + Product.class.getName()));
	}
	public List<Product> findAll() {
		return repository.findAll();
	}
	public Product create(ProductDto obj) {
		Product transformProductDto = new Product(obj);
		return repository.save(transformProductDto );
	}

	public Product update(Long id, ProductDto productDto) {
		Product newObj = findById(id);
		newObj.setName(newObj.getName());
		newObj.setPrice(newObj.getPrice());
		newObj.setDescription(newObj.getDescription());
		return repository.save(newObj);
	}
	public void deleteById(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	public List<Product> findProduct(BigDecimal minPrice, BigDecimal maxPrice, String name) {
		return repository.findProduct(minPrice, maxPrice, name);
	}
}
	

		
	

	

	
