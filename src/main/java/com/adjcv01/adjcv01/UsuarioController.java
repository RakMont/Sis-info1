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
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value="/listarUsuario", method = RequestMethod.GET)
    public String usuarios(Model modelo){
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        modelo.addAttribute("listarUsuario", usuarios);
        return("usuarios");
    }

    @RequestMapping(value="/usuario/new", method = RequestMethod.GET)
    public String newUsuario(Model modelo){
        return "newUsuario";
    }

    @RequestMapping(value="usuarios", method = RequestMethod.POST)
    public String createUsuario(@ModelAttribute("usuarios") Usuario s, Model modelo){
        usuarioRepository.save(s);
        return "redirect:/listarUsuario";
    }

    @RequestMapping(value="/usuariosh/{id}", method = RequestMethod.GET) //este es elDetalle
    public String usuariosh(@PathVariable Integer id, Model modelo){
        Optional<Usuario> captura = usuarioRepository.findById(id);
        //va a trabajar con MunicipioRepository
        Usuario m = captura.get();
        modelo.addAttribute("usu",m);//El modelo le va a enviar como mun, el m municipio
        return "detalleUsuario";//Se va a mostrar en esta pagina
    }

    @RequestMapping(value="elimUsu/{id}")
    public String elimUsu(@PathVariable Integer id,Model modelo){
        usuarioRepository.deleteById(id);
        return "redirect:/listarUsuario";
    }

    @RequestMapping(value = "/editUsuario/{id}")
    public String editUsuario(@PathVariable Integer id, Model model){
        Optional<Usuario> edito = usuarioRepository.findById(id);
        Usuario m = edito.get();
        model.addAttribute("usu",m);
        return "editUsuario";
    }

    @RequestMapping(value = "usuarioActualizar", method = RequestMethod.POST)
    public String usuarioActualizar(@ModelAttribute ("usuraio") Usuario m, Model model){
        Optional<Usuario> editoagain = usuarioRepository.findById(m.getId());
        Usuario nuevo = editoagain.get();
        nuevo.setId(m.getId());
        nuevo.setNombre(m.getNombre());
        nuevo.setFecnac(m.getFecnac());
        usuarioRepository.save(nuevo);
        return "redirect:/listarUsuario";
    }
}
