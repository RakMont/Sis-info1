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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class VacunaController {
    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired PropietarioRepository propietarioRepository;

    @Autowired VacunaRepository vacunaRepository;


    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String registrationInit() {
        return "inicio";
    }
    @RequestMapping(value="/vacuna/new", method = RequestMethod.GET)
    public String newVacuna(Model modelo) {
        List<Vacuna> vacunas = (List) vacunaRepository.findAll();
        //modelo.addAttribute("mascotas", clubRepository.findAll());
        modelo.addAttribute("vacuna", new Vacuna());
        modelo.addAttribute("vacunas",vacunaRepository.findAll());
        return "newVacuna";
    }

    @RequestMapping(value="vacunas", method = RequestMethod.POST)
    public String createVacuna(@ModelAttribute("vacunas") Vacuna a, Model modelo){
        vacunaRepository.save(a);
        return "redirect:/vacunas";
    }
    @RequestMapping(value="/vacunas", method = RequestMethod.GET)
    public String vacunas(Model modelo){
        Iterable<Vacuna> vacunas = vacunaRepository.findAll();
        modelo.addAttribute("listarVacunas", vacunas);
        return("vacunas");
    }



    @RequestMapping(value="elimVacuna/{idVacuna}")
    public String elimVacuna(@PathVariable Integer idVacuna, Model modelo){
        vacunaRepository.deleteById(idVacuna);
        return "redirect:/vacunas";
    }

    @RequestMapping(value = "/editVacuna/{idVacuna}")
    public String editVacuna(@PathVariable Integer idVacuna, Model model){
        Optional<Vacuna> edito = vacunaRepository.findById(idVacuna);

        List<Vacuna> vacunas  = (List) vacunaRepository.findAll();
        Vacuna c = edito.get();
        model.addAttribute("vacunas", vacunas);
        model.addAttribute("vacuna",c);
        return "editVacuna";
    }

    @RequestMapping(value = "VacunaActualizar", method = RequestMethod.POST)
    public String VacunaActualizar(@ModelAttribute ("vacuna") Vacuna c, Model model){
        Optional<Vacuna> editoagain = vacunaRepository.findById(c.getIdVacuna());
        Vacuna nuevo = editoagain.get();

        nuevo.setIdVacuna(c.getIdVacuna());
        nuevo.setNombreVacuna(c.getNombreVacuna());
        nuevo.setEnfermedad(c.getEnfermedad());
        vacunaRepository.save(nuevo);
        return "redirect:/vacunas";
    }
}
