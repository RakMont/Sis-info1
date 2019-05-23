package com.adjcv01.adjcv01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@Controller
public class MunicipioController {
    @Autowired
    private MunicipioRepository municipioRepository;

    @RequestMapping(value="/listar", method = RequestMethod.GET)
    public String municipios(Model modelo){
        Iterable<Municipio> municipios = municipioRepository.findAll();
        modelo.addAttribute("listamunicipios", municipios);
        return("municipios");
    }

    @RequestMapping(value="/municipio/new", method = RequestMethod.GET)
    public String newMunicipio(Model modelo){
        return "newMunicipio";
    }

    @RequestMapping(value="municipios", method = RequestMethod.POST)
    public String create(@ModelAttribute("municipios") Municipio m, Model modelo){
        municipioRepository.save(m);
        return "redirect:/listar";
    }

    @RequestMapping(value="/municipiosh/{idMunicipio}", method = RequestMethod.GET) //este es elDetalle
    public String municipiosh(@PathVariable Integer idMunicipio, Model modelo){
        Optional<Municipio> captura = municipioRepository.findById(idMunicipio);
        //va a trabajar con MunicipioRepository
        Municipio m = captura.get();
        modelo.addAttribute("mun",m);//El modelo le va a enviar como mun, el m municipio
        return "detalle";//Se va a mostrar en esta pagina
    }

    @RequestMapping(value="elimMun/{idMunicipio}")
    public String elimMun(@PathVariable Integer idMunicipio,Model modelo){
        municipioRepository.deleteById(idMunicipio);
        return "redirect:/listar";
    }

    @RequestMapping(value = "/editmunicipio/{idMunicipio}")
    public String editmunicipio(@PathVariable Integer idMunicipio, Model model){
        Optional<Municipio> edito = municipioRepository.findById(idMunicipio);
        Municipio m = edito.get();
        model.addAttribute("muni",m);
        return "editmunicipio";
    }

    @RequestMapping(value = "municipioActualizar", method = RequestMethod.POST)
    public String municipioActualizar(@ModelAttribute ("municipio") Municipio m, Model model){
        Optional<Municipio> editoagain = municipioRepository.findById(m.getIdMunicipio());
        Municipio nuevo = editoagain.get();
        nuevo.setIdMunicipio(m.getIdMunicipio());
        nuevo.setNombre(m.getNombre());
        nuevo.setFecha(m.getFecha());
        municipioRepository.save(nuevo);
        return "redirect:/listar";
    }
}


