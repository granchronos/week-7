package pe.edu.idat.week_6.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.edu.idat.week_6.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void add(Product product);

    Page<Product> all(Pageable pageable);

    Optional<Product> get(Long id);

    void edit(Product product);

    void delete(Long id);

}
