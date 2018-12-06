/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.estacionamento.controle;

import br.edu.ifrs.estacionamento.DAO.AtendenteDAO;
import br.edu.ifrs.estacionamento.DAO.ClienteDAO;
import br.edu.ifrs.estacionamento.DAO.EstadiaDAO;
import br.edu.ifrs.estacionamento.modelo.Estadia;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jader
 */
@RestController
@RequestMapping(path = "/api/estadia/")
public class EstadiaControle {

    @Autowired
    AtendenteDAO atendeDAO;

    @Autowired
    ClienteDAO clienteDAO;

    @Autowired
    EstadiaDAO estadiaDAO;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Iterable<Estadia> listar() {
        return estadiaDAO.findAll();

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Estadia inserir(@RequestBody Estadia estadia) {
        estadia.setId(0);
        LocalDate hoje = LocalDate.now();
        LocalTime hora = LocalTime.now();
        estadia.setDiaEntrada(hoje);
        estadia.setHoraEntrada(hora);
        return estadiaDAO.save(estadia);

    }

}
