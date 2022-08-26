package br.com.ms.product.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ms.product.controller.dto.ProductDto;
import br.com.ms.product.entity.Product;
import br.com.ms.product.service.ProductService;

@Validated
@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product obj = this.service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@GetMapping
	public ResponseEntity<List<ProductDto>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(ProductDto.transformProductDto(list));
	}
	@GetMapping("/search")
	public ResponseEntity<List<ProductDto>> search(
			@RequestParam(value="minPrice", required = false) BigDecimal minPrice,
			@RequestParam(value="maxPrice", required = false) BigDecimal maxPrice,
			@RequestParam(value="name", required = false ) String name){

		List<Product> findProducts = service.search (minPrice, maxPrice, name);
		return ResponseEntity.ok(ProductDto.transformProductDto(findProducts));
	}
	@PostMapping
	public ResponseEntity<ProductDto> create(@RequestBody ProductDto obj){
		Product newObj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto productDto) {
		Product newObj = service.update(id, productDto);
		newObj.setId(id);
		return ResponseEntity.ok().body(new ProductDto(newObj));
	}
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<ProductDto> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}



