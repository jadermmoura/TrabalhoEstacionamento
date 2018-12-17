/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.estacionamento.controle;

import br.edu.ifrs.estacionamento.DAO.VeiculoDAO;
import br.edu.ifrs.estacionamento.erro.NaoEncontrado;
import br.edu.ifrs.estacionamento.modelo.Veiculo;
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
@RequestMapping(path = "/api/veiculo")
public class VeiculoControle {
    
    @Autowired
    VeiculoDAO veiculoDAO;
    
    @RequestMapping(path = "/",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Veiculo> listar (){
        return veiculoDAO.findAll();
        
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void apagar(@PathVariable int id) {
        if (veiculoDAO.existsById(id)) {
            veiculoDAO.deleteById(id);
        } else {
            throw new NaoEncontrado("Não encontrado");
        }
    }
    
    
    @RequestMapping(path = "/verifica/{placa}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public boolean verificaPlaca (@PathVariable ("placa") String placa){
        Optional<Veiculo> veiculo = veiculoDAO.findAllByPlaca(placa);
        return veiculo.isPresent();
        
    }
    @RequestMapping(path = "/placa/{placa}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Veiculo> buscarCarroPlaca(@PathVariable("placa")String placa){
              return veiculoDAO.findByPlaca(placa);
    }
     
     @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Veiculo pesquisarPeloId(@PathVariable int id) {
        Optional<Veiculo> clienteId = veiculoDAO.findAllById(id);
        if (clienteId.isPresent()) {
            return clienteId.get();
        } else {
            throw new NaoEncontrado("Id não encontrado");
        }
    }
     @RequestMapping(path = "/", method = RequestMethod.POST)
    public Veiculo inserir(@RequestBody Veiculo veiculo) {
        veiculo.setId(0);
        if (veiculo.getPlaca()== null || veiculo.getPlaca().equals("")
        || veiculo.getModelo()== null || veiculo.getModelo().equals("")) {
        }
        return veiculoDAO.save(veiculo);
    }
    
      @RequestMapping(path = "/editar/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Veiculo editar(@PathVariable int id, @RequestBody Veiculo veiculoNovo) {
        veiculoNovo.setId(id);
        Veiculo veiculoAntigo = this.pesquisarPeloId(id);
        veiculoAntigo.setPlaca(veiculoNovo.getPlaca());
        veiculoAntigo.setModelo(veiculoNovo.getModelo());
        
        return veiculoDAO.save(veiculoAntigo);
        
    }
}
