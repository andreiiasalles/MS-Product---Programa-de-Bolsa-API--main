
package br.com.ms.product.test;

import java.math.BigDecimal;

import java.util.Optional;

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
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerTest {
	
	public static final Long ID = 1l;
    public static final String NAME = "Aifone";
    public static final String DESCRIPTION = "Celular Caro";
    public static final BigDecimal PRICE = new BigDecimal (10.0);
	
    private Optional<Product> product;
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
	}
	
	@Test
	void whenfindByIdThenReturnSucess() {
		Mockito.when(service.findById(Mockito.anyLong())).thenReturn(ProductDto);
	    Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(productDto);
	    ResponseEntity<Product> response = controller.findById(ID);
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getBody());
	    assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ProductDto.class, response.getBody().getClass());

	    assertEquals(ID,response.getBody().getId());
		assertEquals(NAME,response.getBody().getId());
		assertEquals(DESCRIPTION,response.getBody().getId());
	    assertEquals(PRICE,response.getBody().getId());
	}

	@Test
	void findAll() {
		
	}
	
	@Test
	void created() {
		
	}
	
	@Test
	void update() {
		
	}
	
	@Test
	void querydinamica() {
		
	}
	
	@Test
	void delete() {
		
	}
	private void productStarter(){
        product = Optional.of(new Product(ID, NAME, DESCRIPTION, PRICE));
        productDto = new ProductDto(ID, NAME, DESCRIPTION, PRICE);
    }
}


