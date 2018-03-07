package com.distribuida.azure.speech.controller;

import com.distribuida.azure.speech.dto.DatosSpeech;
import com.distribuida.azure.speech.servicio.TextoAVozServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpeechController {

    @RequestMapping(value = "/speech", method = RequestMethod.GET)
    public String cargarPagina(Model model) {
        model.addAttribute("datos", new DatosSpeech());
        return "speech";
    }

    @RequestMapping(value = "/speech", method = RequestMethod.POST)
    public String analizar(@ModelAttribute DatosSpeech datosSpeech, Model model) {
        model.addAttribute("datos", new DatosSpeech());
        new TextoAVozServicio().leerMensaje(datosSpeech.getMensaje());
        return "speech";
    }
}
