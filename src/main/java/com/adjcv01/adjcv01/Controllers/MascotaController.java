package com.adjcv01.adjcv01.Controllers;
import com.adjcv01.adjcv01.Models.Mascota;
import com.adjcv01.adjcv01.Models.Propietario;
import com.adjcv01.adjcv01.Models.Vacuna;

import com.adjcv01.adjcv01.Repositories.MascotaRepository;
import com.adjcv01.adjcv01.Repositories.PropietarioRepository;
import com.adjcv01.adjcv01.Repositories.VacunaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;
@Controller
public class MascotaController {
    @Autowired MascotaRepository mascotaRepository;

    @Autowired PropietarioRepository propietarioRepository;

    @Autowired VacunaRepository vacunaRepository;

    @RequestMapping(value="**/filterPropietario",method = RequestMethod.POST)
    public String filter2(Model modelo, @RequestParam("propAux")Integer propAux){
        Optional<Propietario> propietario = propietarioRepository.findById(propAux);
        Propietario prop=propietario.get();
        modelo.addAttribute("propAux",propAux);
        modelo.addAttribute("listarMascotas",mascotaRepository.findByPropietario(prop));
        if(propAux.equals("0")){
            return mascotas(modelo);
        }
        modelo.addAttribute("propietarios",propietarioRepository.findAll());
        return ("mascotas");
    }

    @RequestMapping(value="**/filterEspecie",method = RequestMethod.POST)
    public String filter(Model modelo, @RequestParam("catAux")String catAux){
        modelo.addAttribute("catAux",catAux);
        modelo.addAttribute("listarMascotas",mascotaRepository.findByEspecie(catAux));
        if(catAux.equals("Todo")){

            return mascotas(modelo);
        }
       // modelo.addAttribute("especies",mascotaRepository.findAll());
        modelo.addAttribute("especies",mascotaRepository.findDistinctMascotaFromDb());

        return ("mascotas");
    }


    @RequestMapping(value="/mascotas", method = RequestMethod.GET)
    public String mascotas(Model modelo){
        Iterable<Mascota> mascotas = mascotaRepository.findAll();
        modelo.addAttribute("espAux","Todo");
        modelo.addAttribute("listarMascotas", mascotas);
        modelo.addAttribute("propietarios",propietarioRepository.findAll());
        modelo.addAttribute("especies",mascotaRepository.findDistinctMascotaFromDb());


        modelo.addAttribute("mascota",new Mascota());
        modelo.addAttribute("vacunas",vacunaRepository.findAll());
        return("mascotas");
    }



    @RequestMapping(value="/mascota/new", method = RequestMethod.GET)
    public String newMascota(Model modelo) {
        List<Mascota> mascotas = (List) mascotaRepository.findAll();
        //modelo.addAttribute("mascotas", clubRepository.findAll());
        modelo.addAttribute("mascota", new Mascota());
        modelo.addAttribute("mascotas",mascotaRepository.findAll());
        modelo.addAttribute("propietarios",propietarioRepository.findAll());
        return "newMascota";
    }

    @RequestMapping(value="mascotas", method = RequestMethod.POST)
    public String createMascota(@ModelAttribute("mascotas") Mascota a, Model modelo){
        mascotaRepository.save(a);
        return "redirect:/mascotas";
    }


    @RequestMapping(value="elimMascota/{idMascota}")
    public String elimMascota(@PathVariable Integer idMascota,Model modelo){
        mascotaRepository.deleteById(idMascota);
        return "redirect:/mascotas";
    }
    @RequestMapping(value="/mascotaVacuna/{idMascota}", method = RequestMethod.GET) //este es elDetalle
    public String mascotaVacuna(@PathVariable Integer idMascota, Model modelo){
        Optional<Mascota> mascota = mascotaRepository.findById(idMascota);

        Iterable<Vacuna> vacunas=vacunaRepository.findAll();

        Mascota a = mascota.get();
        modelo.addAttribute("mascota",a);
        //modelo.addAttribute("vacunas",vacunas);
        List<Integer> list=new LinkedList();
        for(Vacuna o : a.getVacunas()){
            list.add(o.getIdVacuna());
        }
        if (a.getVacunas().isEmpty()){
            modelo.addAttribute("vacunas",vacunaRepository.findAll());
        }
        else{
            modelo.addAttribute("vacunas",vacunaRepository.findByIdVacunaNotIn(list));

        }

        return "mascotaVacuna";
    }
    @RequestMapping(value = "vacunaMascota", method = RequestMethod.POST)
    public String vacunaMascota(@ModelAttribute ("mascota") Mascota c, Model model){
        Optional<Mascota> editoagain;
        editoagain= mascotaRepository.findById(c.getIdMascota());
        Mascota nuevo = editoagain.get();
        List<Vacuna>vacunas=nuevo.getVacunas();
        vacunas.addAll(c.getVacunas());
        nuevo.setVacunas(c.getVacunas());
        nuevo.setVacunas(vacunas);
        mascotaRepository.save(nuevo);
        return "redirect:/mascotas";
    }




    @RequestMapping(value = "/editMascota/{idMascota}")
    public String editMascota(@PathVariable Integer idMascota, Model model){
        Optional<Mascota> edito = mascotaRepository.findById(idMascota);
        List<Mascota> mascotas  = (List) mascotaRepository.findAll();
        Mascota c = edito.get();
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("mascota",c);
        model.addAttribute("propietarios",propietarioRepository.findAll());
        return "editMascota";
    }

    @RequestMapping(value = "MascotaActualizar", method = RequestMethod.POST)
    public String MascotaActualizar(@ModelAttribute ("mascota") Mascota c, Model model){
        Optional<Mascota> editoagain = mascotaRepository.findById(c.getIdMascota());
        Mascota nuevo = editoagain.get();
        nuevo.setIdMascota(c.getIdMascota());
        nuevo.setEdadMascota(c.getEdadMascota());
        nuevo.setEspecie(c.getEspecie());
        nuevo.setNombreMascota(c.getNombreMascota());
        nuevo.setPropietario(c.getPropietario());
        mascotaRepository.save(nuevo);
        return "redirect:/mascotas";
    }



}
