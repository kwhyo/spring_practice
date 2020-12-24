package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String heelo(Model model){
     model.addAttribute("data","호잇!!");
     return "hello"; //hello.html로 찾아감.
    }
}
