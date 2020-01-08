package com.adjcv01.adjcv01.Controllers;
import com.adjcv01.adjcv01.Models.Propietario;
import com.adjcv01.adjcv01.Repositories.MascotaRepository;
import com.adjcv01.adjcv01.Repositories.PropietarioRepository;
import com.adjcv01.adjcv01.Repositories.VacunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;


@Controller
public class PropietarioController {
    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired PropietarioRepository propietarioRepository;

    @Autowired VacunaRepository vacunaRepository;


    @RequestMapping(value="/propietario/new", method = RequestMethod.GET)
    public String newPropietario(Model modelo) {
        List<Propietario> propietarios = (List) propietarioRepository.findAll();
        //modelo.addAttribute("mascotas", clubRepository.findAll());
        modelo.addAttribute("propietario", new Propietario());
        modelo.addAttribute("propietarios",propietarios);
        return "newPropietario";
    }

    @RequestMapping(value="propietarios", method = RequestMethod.POST)
    public String createPropietario(@ModelAttribute("propietarios") Propietario a, Model modelo){
        propietarioRepository.save(a);
        return "redirect:/propietarios";
    }


    @RequestMapping(value="/propietarios", method = RequestMethod.GET)
    public String propietarios(Model modelo){
        Iterable<Propietario> propietarios = propietarioRepository.findAll();
        //modelo.addAttribute("espAux","Todo");
        //modelo.addAttribute("listarPropietarios", propietarios);
        modelo.addAttribute("propietarios",propietarioRepository.findAll());
        return("propietarios");
    }



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
}
