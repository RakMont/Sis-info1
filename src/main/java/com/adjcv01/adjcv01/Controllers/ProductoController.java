package com.adjcv01.adjcv01.Controllers;

import com.adjcv01.adjcv01.Models.Producto;
import com.adjcv01.adjcv01.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

    @Controller
    public class ProductoController {

        @Autowired
        ProductoRepository productoRepository;


        @RequestMapping(value="/producto/new", method = RequestMethod.GET)
        public String newProducto(Model modelo) {

            modelo.addAttribute("producto", new Producto());
            return "/Producto/newProducto";
        }

        @RequestMapping(value="productos", method = RequestMethod.POST)
        public String createProducto(@ModelAttribute("productos") Producto p, Model modelo){
            productoRepository.save(p);
            return "redirect:/productos";
        }
        @RequestMapping(value = "/producto/upload-image", method = RequestMethod.POST)
        public ResponseEntity uploadImage(Model model, @RequestParam("foto") MultipartFile file) throws Exception{
            File convFile = new File("src/main/resources/static/img/"+file.getOriginalFilename());

            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            Blob blob = new SerialBlob(file.getBytes());

            fos.write(file.getBytes());
            fos.close();
            System.out.println(convFile.getName());
            return ResponseEntity.ok("localhost:8090/img/" + file.getOriginalFilename());
        }

        @RequestMapping(value="/productos", method = RequestMethod.GET)
        public String productos(Model modelo){
            Iterable<Producto> productos = productoRepository.findAll();

            modelo.addAttribute("productos",productoRepository.findAll());
            return("Producto/productos");
        }


/*
    @RequestMapping(value="/detalle/{id}", method = RequestMethod.GET)
    public String detallePropietario(@PathVariable Integer id, Model modelo){
        Optional<Propietario> captura = propietarioRepository.findById(id);
        //va a trabajar con MunicipioRepository
        Propietario c = captura.get();
        modelo.addAttribute("propietario",c);
        return "detallePropietario";
    }

    @RequestMapping(value="elimPropietario/{id}")
    public String elimPropietario(@PathVariable Integer id,Model modelo){
        propietarioRepository.deleteById(id);
        return "redirect:/propietarios";
    }

    @RequestMapping(value = "/editPropietario/{id}")
    public String editPropietario(@PathVariable Integer id, Model model){
        Optional<Propietario> edito = propietarioRepository.findById(id);

        List<Propietario> propietarios  = (List) propietarioRepository.findAll();
        Propietario c = edito.get();
        model.addAttribute("propietarios", propietarios);
        model.addAttribute("propietario",c);
        return "editPropietario";
    }

    @RequestMapping(value = "PropietarioActualizar", method = RequestMethod.POST)
    public String PropietarioActualizar(@ModelAttribute ("propietario") Propietario c, Model model){
        Optional<Propietario> editoagain = propietarioRepository.findById(c.getId());
        Propietario nuevo = editoagain.get();
        nuevo.setId(c.getId());
        nuevo.setNombre(c.getNombre());
        nuevo.setEdad(c.getEdad());
        propietarioRepository.save(nuevo);
        return "redirect:/propietarios";
    }
    */
}
