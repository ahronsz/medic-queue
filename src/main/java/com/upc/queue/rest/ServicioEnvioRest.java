package com.upc.queue.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.queue.entidades.Cita;
import com.upc.queue.jms.JmsProductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/queue")
public class ServicioEnvioRest {

    @Autowired
    private JmsProductor jmsProductor;

    @PostMapping("/cita")
    public String enviar(@RequestBody Cita cita) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(cita);
            jmsProductor.send(jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Cita programada!!!";
    }
}
