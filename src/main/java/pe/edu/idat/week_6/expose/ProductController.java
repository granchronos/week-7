package pe.edu.idat.week_6.expose;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.week_6.model.Product;
import pe.edu.idat.week_6.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {

    public static String REDIRECT_LIST = "product/list";

    @Autowired
    private ProductService productService;

    @GetMapping("register")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/register";
    }

    @PostMapping("register")
    public String registerForm(Model model, @Valid @ModelAttribute Product product,
                               BindingResult result, Pageable pageable) {
        if (result.hasErrors()) {
            return"product/register";
        }
        productService.add(product);
        model.addAttribute("productsPage", this.productService.all(pageable));
        return REDIRECT_LIST;
    }

    @GetMapping("list")
    public String showList(Model model, @PageableDefault(size = 5, sort = "price", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("productsPage", productService.all(pageable));
        return REDIRECT_LIST;
    }

    @GetMapping("edit/{id}")
    public String editForm(Model model, @PathVariable Long id) {
        productService.get(id)
                .ifPresent(product -> model.addAttribute("product", product));
        return "product/edit";
    }

    @PostMapping("edit")
    public String editForm(Model model, @Valid @ModelAttribute Product product,
                               BindingResult result, Pageable pageable) {
        if (result.hasErrors()) {
            return "product/edit";
        }
        productService.edit(product);
        model.addAttribute("products", this.productService.all(pageable));
        return REDIRECT_LIST;
    }

    @GetMapping("delete/{id}")
    public String delete(Model model, @PathVariable Long id, Pageable pageable) {
        productService.delete(id);
        model.addAttribute("products", this.productService.all(pageable));
        return REDIRECT_LIST;
    }

}
