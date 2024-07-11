package pe.edu.idat.week_6.expose;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.week_6.model.Customer;
import pe.edu.idat.week_6.service.CustomerService;

@Controller
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("register")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/register";
    }

    @PostMapping("register")
    public String registerForm(Model model, @Valid @ModelAttribute Customer customer,
                               BindingResult result) {
        if (result.hasErrors()) {
            return"customer/register";
        }
        customerService.add(customer);
        model.addAttribute("customers", this.customerService.all());
        return "customer/listCustomer";
    }

    @GetMapping("list")
    public String showList(Model model) {
        model.addAttribute("customers", customerService.all());
        return "customer/listCustomer";
    }

    @GetMapping("edit/{id}")
    public String editForm(Model model, @PathVariable Long id) {
        customerService.get(id)
                .ifPresent(customer -> model.addAttribute("customer", customer));
        return "customer/edit";
    }

    @PostMapping("edit")
    public String editForm(Model model, @Valid @ModelAttribute Customer customer,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "customer/edit";
        }
        customerService.edit(customer);
        model.addAttribute("customers", this.customerService.all());
        return "customer/listCustomer";
    }

    @GetMapping("delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        customerService.delete(id);
        model.addAttribute("customers", this.customerService.all());
        return "customer/listCustomer";
    }

}
