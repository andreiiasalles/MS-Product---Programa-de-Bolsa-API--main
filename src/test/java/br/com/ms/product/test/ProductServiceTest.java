package br.com.ms.product.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.ms.product.controller.dto.ProductDto;
import br.com.ms.product.entity.Product;
import br.com.ms.product.repository.ProductRepository;
import br.com.ms.product.service.ProductService;

SpringBootTest
class ProductServiceTest {
	
	public static final Long ID = 1l;
    public static final String NAME = "Aifone";
    public static final String DESCRIPTION = "Celular Caro";
    public static final BigDecimal PRICE = new BigDecimal (10.0);
	
	@Autowired 
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

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
		Mockito.when(productRepository.create(Mockito.any())).thenReturn(product);
		Product p = productService.save(productDto);
		Assert.assertNotNull(p);
		Assert.assertEquals(Product.class, p.getClass());
	}
	@Test
	void whenCreatThenReturnDataIntegrityViolationException() {
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(optProduct);
		
		try {
			optProduct.get().setId(2l);
			productService.save(productDto);
		}catch(Exception e) {
		Assert.assertEquals(DataIntegrityViolationException.class, e.getClass());
		}
	}
	
	
//atualizar produto por id
	@Test
	void whenUpdateThenReturnSucess() {
		Mockito.when(productRepository.create(Mockito.any())).thenReturn(product);
		Product p = productService.update(productDto);
		Assert.assertNotNull(p);
		Assert.assertEquals(Product.class, p.getClass());
		
	}
	
	@Test
	void whenUpdateThenReturnDataIntegrityViolationException() {
		Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(optProduct);
		
		try {
			optProduct.get().setId(2l);
			productService.save(productDto);
		}catch(Exception e) {
		Assert.assertEquals(DataIntegrityViolationException.class, e.getClass());
		}
		
	}
	
	
	
	
	
//	procurar todos os produtos
	@Test
	void whenFindAllThenReturnAnListOfProducts() {
		Mockito.when(productRepository.findAll()).thenReturn(List.of(product));
		List<Product> p = productService.listProduct();
		Assert.assertNotNull(p);
}
	@Test
	void buscarTodosOsProdutosComFalha() {
		
	}
	
	@Test
	void whenFindByIdThenReturnAnProductInstance() {
		Mockito.when(productRepository.findById(ID)).thenReturn(optProduct);
		Optional<Product> p = productService.findById(ID);
		Assertions.assertNotNull(p);
	}
	
	@Test
	void whenFindByIdTheReturnAnProductNotFoundException() {
		Mockito.when(productRepository.findById(Mockito.anyLong())).thenThrow( new EntityNotFoundException());
		try {
			productService.findById(ID);	
		} catch (Exception e) {
			assertEquals(EntityNotFoundException.class, e.getClass());	
      }
	}
	
	
//	procurar por preço min,max, nome 
	@Test
	void buscaDinamicaPreçoMinimoMaximoENome() {
		
	}
	@Test
	void buscaDinamicaPreçoMinimoMaximoENomeComFalha() {
		
	}
	
	
	
	
	
	
//	deletar produto por id
	@Test
	 void deleteWithAValidId() {
	      Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(optProduct);
	      Mockito.doNothing().when(productRepository).deleteById(Mockito.any());
	      productService.deleteById(1l);

	       Mockito.verify(productRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
	    }
	
//	@Test
//	void delectProductNotFoundException() {
//		Mockito.when(productRepository.findById(Mockito.anyLong()))
//			.thenThrow(new EntityNotFoundException());
//	
//		try {
//		productService.deleteById(ID);
//		} catch(Exception e) {
//		Assert.assertEquals(EntityNotFoundException.class, e.getClass());
//	}
//}
	
	private void productStarter(){
        product = new Product(ID, NAME, DESCRIPTION, PRICE);
        optProduct = Optional.of(new Product(ID, NAME, DESCRIPTION, PRICE));
        productDto = new ProductDto(ID, NAME, DESCRIPTION, PRICE);
    }
}
*/
