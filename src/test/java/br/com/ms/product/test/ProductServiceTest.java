package br.com.ms.product.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import br.com.ms.product.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.ms.product.controller.dto.ProductDto;
import br.com.ms.product.entity.Product;
import br.com.ms.product.repository.ProductRepository;
import br.com.ms.product.service.ProductService;

@SpringBootTest
class ProductServiceTest {
	
	public static final Long ID = 1l;
    public static final String NAME = "Aifone";
    public static final String DESCRIPTION = "Celular Caro";
    public static final BigDecimal PRICE = new BigDecimal (10.0);

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	@Mock
	private ModelMapper mapper;

	private Product product;
	private ProductDto productDto;
	private Optional<Product> optProduct;


	
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productStarter();
    }
	
	@Test
	void whenCreatThenReturnSucess() {
		when(repository.save(any())).thenReturn(product);

		Product response = service.create(productDto);

		assertNotNull(response);
		assertEquals(ProductDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(DESCRIPTION, response.getDescription());
		assertEquals(PRICE, response.getPrice());
	}

	@Test
	void whenCreatThenReturnDataIntegrityViolationException() {
		when(repository.findById(any())).thenReturn(optProduct);

		try {
			optProduct.get().setId(9l);
			service.create(productDto);

		} catch(Exception e) {
		assertEquals(DataIntegrityViolationException.class, e.getClass());
		}
	}

	@Test
	void whenUpdateThenReturnSucess() {
		when(repository.findById(ID)).thenReturn(optProduct);
		when(repository.save(any())).thenReturn(product);

		Product response = service.update(ID, productDto);

		assertNotNull(response);
		assertEquals(ProductDto.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(DESCRIPTION, response.getDescription());
		assertEquals(PRICE, response.getPrice());
	}
	
	@Test
	void whenUpdateThenReturnDataIntegrityViolationException() {
		when(repository.findById(anyLong())).thenReturn(optProduct);
		
		try {
			optProduct.get().setId(9l);
			service.create(productDto);
		}catch(Exception e) {
		assertEquals(DataIntegrityViolationException.class, e.getClass());
		}
	}

	@Test
	void whenFindAllThenReturnAnListOfProducts() {
		when(repository.findAll()).thenReturn(List.of(product));
		List<Product> response = service.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Product.class, response.get(0).getClass());

		assertEquals(ID, response.get(0).getId());
		assertEquals(NAME, response.get(0).getName());
		assertEquals(DESCRIPTION, response.get(0).getDescription());
		assertEquals(PRICE, response.get(0).getPrice());
	}

	@Test
	void whenFindByIdThenReturnAnProductInstance() {
		when(repository.findById(anyLong())).thenReturn(optProduct);
		Product response = service.findById(ID);

		assertNotNull(response);

		assertEquals(Product.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(DESCRIPTION, response.getDescription());
		assertEquals(PRICE, response.getPrice());
	}
	
	@Test
	void whenFindByIdTheReturnAnProductNotFoundException() {
		when(repository.findById(anyLong())).thenThrow( new ObjectNotFoundException("Products not found"));

		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Products not found", e.getMessage());
      }
	}

	@Test
	 void deleteWithAValidId() {
	      when(repository.findById(anyLong())).thenReturn(optProduct);
	      doNothing().when(repository).deleteById(any());
	      service.deleteById(1l);

	       verify(repository, Mockito.times(1)).deleteById(anyLong());
	    }
	
	@Test
	void deleteProductNotFoundException() {
		when(repository.findById(anyLong()))
			.thenThrow(new EntityNotFoundException("Products not found"));

	try {
		service.deleteById(ID);
	} catch(Exception e) {
		assertEquals(EntityNotFoundException.class, e.getClass());
	}
	}
	
	private void productStarter(){
        product = new Product(ID, NAME, DESCRIPTION, PRICE);
        optProduct = Optional.of(new Product(ID, NAME, DESCRIPTION, PRICE));
        productDto = new ProductDto(ID, NAME, DESCRIPTION, PRICE);
    }
}

