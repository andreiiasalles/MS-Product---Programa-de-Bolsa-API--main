
package br.com.ms.product.test;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.swagger.models.Response;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ms.product.controller.ProductController;
import br.com.ms.product.controller.dto.ProductDto;
import br.com.ms.product.entity.Product;
import br.com.ms.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {
	
	public static final Long ID = 1l;
    public static final String NAME = "Aifone";
    public static final String DESCRIPTION = "Celular Caro";
    public static final BigDecimal PRICE = new BigDecimal (10.0);

	private Product product;

    private ProductDto productDto;
	@InjectMocks
	private ProductController controller;
	
	@Mock
	private ProductService service;
	
	@Mock
	private ModelMapper mapper;

	private ProductControllerTest testClass;
	

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
        productStarter();
	}
	
	@Test
	void whenfindByIdThenReturnSucess() {
		when(service.findById(anyLong())).thenReturn (productDto);
	    when(mapper.map(any(), any())).thenReturn(productDto);

	    ResponseEntity<Product> response = controller.findById(ID);

        assertNotNull(response);
		assertNotNull(response.getBody());
	    assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ProductDto.class, response.getBody().getClass());

	    assertEquals(ID,response.getBody().getId());
		assertEquals(NAME,response.getBody().getName());
		assertEquals(DESCRIPTION,response.getBody().getDescription());
	    assertEquals(PRICE,response.getBody().getPrice());
	}

	@Test
	void whenFindAllThenReturnAListOfProductDto() {
		when(service.findAll()).thenReturn(List.of(product));
		when(mapper.map(any(), any())).thenReturn(productDto);

		ResponseEntity<List<ProductDto>> response = controller.findAll();

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
		
	}
	
	@Test
	void whenCreateThenReturnCreated() {
		when(service.create((any()))).thenReturn(product);

		ResponseEntity<ProductDto> response = controller.create(productDto);

		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getHeaders().get("Location"));
		
	}
	
	@Test
	void whenUpdateThenReturnSucess() {
		when(service.update(ID, productDto)).thenReturn(product);
		when(mapper.map(any(), any())).thenReturn(productDto);

		ResponseEntity<ProductDto> response = controller.update(ID, productDto);

		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ProductDto.class, response.getBody().getClass());

		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME, response.getBody().getName());
		assertEquals(DESCRIPTION, response.getBody().getDescription());
		assertEquals(PRICE, response.getBody().getPrice());
	}

	@Test
	void whenDeleteThenReturnSucess() {
		doNothing().when(service).deleteById(anyLong());

		ResponseEntity<ProductDto> response = controller.deleteById(ID);

		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(service, times(1)).deleteById(anyLong());

	}
	private void productStarter(){
        product = new Product(ID, NAME, DESCRIPTION, PRICE);
        productDto = new ProductDto(ID, NAME, DESCRIPTION, PRICE);
    }
}


