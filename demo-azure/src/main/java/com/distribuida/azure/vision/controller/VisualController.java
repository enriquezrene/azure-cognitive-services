package com.distribuida.azure.vision.controller;

import com.distribuida.azure.vision.servicio.AnalisisImagenServicio;
import com.distribuida.azure.vision.dto.DatosVision;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VisualController {

    @RequestMapping(value = "/visual", method = RequestMethod.GET)
    public String cargarPagina(Model model) {
        model.addAttribute("datos", new DatosVision());
        model.addAttribute("resultado", "");
        return "visual";
    }

    @RequestMapping(value = "/visual", method = RequestMethod.POST)
    public String analizar(@ModelAttribute DatosVision datos, Model model) {
        String url = datos.getUrl();
        model.addAttribute("datos", new DatosVision());
        model.addAttribute("resultado", new AnalisisImagenServicio().demo(url));
        return "visual";
    }
}

//https://upload.wikimedia.org/wikipedia/commons/1/12/Broadway_and_Times_Square_by_night.jpg
//https://upload.wikimedia.org/wikipedia/commons/4/47/Jungle.jpg
//https://upload.wikimedia.org/wikipedia/commons/5/54/One-of-the-photos-taken-b-013.jpg
