package br.com.ms.product.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ms.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT obj FROM Product obj WHERE obj.price >= :min AND obj.price <= :max AND obj.name = :name")
	Page<Product> findProduct(Pageable pagination, @Param("min") BigDecimal min, @Param("max") BigDecimal max, @Param("name") String name);
	
}
