package com.adjcv01.adjcv01.Controllers;

import com.adjcv01.adjcv01.Models.Producto;
import com.adjcv01.adjcv01.Models.User;
import com.adjcv01.adjcv01.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @Autowired
    UserRepository userService;

    @GetMapping({"/","/login"})
    public String index(Model modelo) {

        modelo.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu(Model modelo) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        System.out.println(username+"el username  es ");
        com.adjcv01.adjcv01.Models.User userLooged=userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
        String aux;
        String aux2=userLooged.getRol();
        System.out.println(userLooged.getRol()+" el rol es ");
        if (aux2.equals("USER")){
            System.out.println("usuario lohueado como usuario");
            aux="user";
        }
        else{

            aux="inicio";
        }
       // modelo.addAttribute("user",user);
        return aux;
    }

    /*
    @RequestMapping(value="productos", method = RequestMethod.POST)
    public String createProducto(@ModelAttribute("productos") Producto p, Model modelo){
        productoRepository.save(p);
        return "redirect:/productos";
    }*/
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
