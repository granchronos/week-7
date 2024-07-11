package pe.edu.idat.week_6.expose;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("welcomeTitle", "Welcome to Customer Application");
        model.addAttribute("welcomeMessage", "Manage your clients in a simple way.");
        return "index";
    }

}
