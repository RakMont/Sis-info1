package com.adjcv01.adjcv01.Controllers;

import com.adjcv01.adjcv01.Models.Authority;
import com.adjcv01.adjcv01.Models.Propietario;
import com.adjcv01.adjcv01.Models.User;
import com.adjcv01.adjcv01.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/user/new", method = RequestMethod.GET)
    public String newUser(Model modelo) {


        modelo.addAttribute("user", new User());
        return "/User/register";
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("users") User p, Model modelo) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        //El String que mandamos al metodo encode es el password que queremos encriptar.
        System.out.println(bCryptPasswordEncoder.encode("1234"));
        String aux;
        aux=p.getPassword();
        aux=bCryptPasswordEncoder.encode(aux);
        System.out.println(bCryptPasswordEncoder.encode("encripted new password " + aux));
        p.setPassword(aux);
        Set<Authority> au2=new HashSet<Authority>();

        Authority au=new Authority();
        au.setAuthority("ROLE_USER");
        au.setId(2);
        au2.add(au);
        p.setAuthority(au2);
        p.setRol("USER");
        userRepository.save(p);
        return "redirect:/";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model modelo) {
        Iterable<User> users = userRepository.findAll();

        modelo.addAttribute("users", userRepository.findAll());
        return ("User/users");
    }
}