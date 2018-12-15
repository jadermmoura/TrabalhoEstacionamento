/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.estacionamento.controle;

import br.edu.ifrs.estacionamento.DAO.AtendenteDAO;
import br.edu.ifrs.estacionamento.erro.CamposObrigatorios;
import br.edu.ifrs.estacionamento.erro.NaoEncontrado;
import br.edu.ifrs.estacionamento.modelo.Atendente;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jader
 */
@RestController
@RequestMapping(path = "/api/atendente")
public class AtendenteControle {

    @Autowired
    AtendenteDAO atendenteDAO;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Iterable<Atendente> listar() {
        return atendenteDAO.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Atendente pesquisarPeloId(@PathVariable int id) {
        Optional<Atendente> atendenteId = atendenteDAO.findAllById(id);
        if (atendenteId.isPresent()) {
            return atendenteId.get();
        } else {
            throw new NaoEncontrado("Id não encontrado");
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Atendente inserir(@RequestBody Atendente atendente) {
        atendente.setId(0);
        if (atendente.getNome() == null && atendente.getNome().equals("")
                || atendente.getEmail() == null && atendente.getEmail().equals("")
                || atendente.getSenha() == null && atendente.getSenha().equals("")) {
            throw new CamposObrigatorios("Não pode ficar campos em branco");
        }
        return atendenteDAO.save(atendente);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void apagar(@PathVariable int id) {
        if (atendenteDAO.existsById(id)) {
            atendenteDAO.deleteById(id);
        } else {
            throw new NaoEncontrado("Não encontrado");
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Atendente editar(@PathVariable int id, @RequestBody Atendente atendenteNovo) {
        atendenteNovo.setId(id);

        Atendente atendenteAntigo = this.pesquisarPeloId(id);
        atendenteAntigo.setNome(atendenteNovo.getNome());
        atendenteAntigo.setEmail(atendenteNovo.getEmail());
        atendenteAntigo.setSenha(atendenteNovo.getSenha());
        return atendenteDAO.save(atendenteAntigo);

    }
}
