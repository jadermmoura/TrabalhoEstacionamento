/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.estacionamento.controle;

import br.edu.ifrs.estacionamento.DAO.AtendenteDAO;
import br.edu.ifrs.estacionamento.DAO.ClienteDAO;
import br.edu.ifrs.estacionamento.DAO.EstadiaDAO;
import br.edu.ifrs.estacionamento.DAO.VeiculoDAO;
import br.edu.ifrs.estacionamento.modelo.Atendente;
import br.edu.ifrs.estacionamento.modelo.Cliente;
import br.edu.ifrs.estacionamento.modelo.Estadia;
import br.edu.ifrs.estacionamento.modelo.Veiculo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jader
 */
@RestController
@RequestMapping(path = "/api/estadia")
public class EstadiaControle {

    @Autowired
    AtendenteDAO atendeDAO;

    @Autowired
    ClienteDAO clienteDAO;

    @Autowired
    EstadiaDAO estadiaDAO;

    @Autowired
    VeiculoDAO veiculoDao;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Iterable<Estadia> listar() {
        return estadiaDAO.findAll();

    }

    @RequestMapping(path = "/salvar/atendente/{idAtendente}/cliente/{idCliente}/veiculo/{idVeiculo}/", method = RequestMethod.POST)
    public Estadia inserir(@RequestBody Estadia estadia, @PathVariable int idAtendente, @PathVariable int idCliente, @PathVariable int idVeiculo) {
        estadia.setId(0);
        Cliente c = clienteDAO.findAllById(idCliente).get();
        Atendente a = atendeDAO.findAllById(idAtendente).get();
        Veiculo v = veiculoDao.findAllById(idVeiculo).get();
        LocalDate hoje = LocalDate.now();
        estadia.setCliente(c);
        estadia.setAtendente(a);
        estadia.setVeiculo(v);
        estadia.setDiaEntrada(hoje);
        return estadiaDAO.save(estadia);

    }

}
