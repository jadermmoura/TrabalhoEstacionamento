/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.estacionamento.controle;

import br.edu.ifrs.estacionamento.DAO.VeiculoDAO;
import br.edu.ifrs.estacionamento.modelo.Veiculo;
import java.util.List;
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
@RequestMapping(path = "/api/veiculo/")
public class VeiculoControle {
    
    @Autowired
    VeiculoDAO veiculoDAO;
    
    @RequestMapping(path = "",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Veiculo> listar (){
        return veiculoDAO.findAll();
        
    }
    @RequestMapping(path = "placa/{placa}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public boolean verificaPlaca (@PathVariable ("placa") String placa){
        Optional<Veiculo> veiculo = veiculoDAO.findAllByPlaca(placa);
        return veiculo.isPresent();
        
    }
     
    @RequestMapping(path = "",method = RequestMethod.POST)
    public Veiculo inserir (@RequestBody Veiculo veiculo){
        veiculo.setId(0);
        return veiculoDAO.save(veiculo);
    }
    
}
