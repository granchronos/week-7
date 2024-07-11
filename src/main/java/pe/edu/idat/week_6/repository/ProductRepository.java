package pe.edu.idat.week_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.idat.week_6.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
