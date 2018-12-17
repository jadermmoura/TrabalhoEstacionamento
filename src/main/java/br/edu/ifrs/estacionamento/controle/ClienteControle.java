/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.estacionamento.controle;

import br.edu.ifrs.estacionamento.DAO.ClienteDAO;
import br.edu.ifrs.estacionamento.DAO.VeiculoDAO;
import br.edu.ifrs.estacionamento.erro.NaoEncontrado;
import br.edu.ifrs.estacionamento.modelo.Cliente;
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

@RestController
@RequestMapping(path = "/api/cliente")
public class ClienteControle {
    
    @Autowired
    ClienteDAO clienteDAO;
    @Autowired
    VeiculoDAO veiculoDAO;
    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Cliente pesquisarPeloId(@PathVariable int id) {
        Optional<Cliente> clienteId = clienteDAO.findAllById(id);
        if (clienteId.isPresent()) {
            return clienteId.get();
        } else {
            throw new NaoEncontrado("Id não encontrado");
        }
    }
    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Iterable<Cliente> listar() {
        return clienteDAO.findAll();
        
    }
    
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Cliente inserir(@RequestBody Cliente cliente) {
        cliente.setId(0);
        if (cliente.getCpf() == null || cliente.getCpf().equals("")
         || cliente.getNome() == null || cliente.getNome().equals("")) {
        }
        return clienteDAO.save(cliente);
        
    }
    
    @RequestMapping(path = "/nome/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Cliente> buscarPeloNome(@PathVariable("nome") String nome) {
        return clienteDAO.findByNome(nome);
    }
    
    @RequestMapping(path = "/editar/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Cliente editar(@PathVariable int id, @RequestBody Cliente clienteNovo) {
        clienteNovo.setId(id);
        Cliente clienteAntigo = this.pesquisarPeloId(id);
        clienteAntigo.setNome(clienteNovo.getNome());
        clienteAntigo.setCpf(clienteNovo.getCpf());
        
        return clienteDAO.save(clienteAntigo);
        
    }
    
    @RequestMapping(path = "/{id}/veiculo/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo inserirVeiculoNoCliente(@PathVariable int id,
            @RequestBody Veiculo veiculo) {
        veiculo.setId(0);
        Veiculo veiculoSalvo = veiculoDAO.save(veiculo);
        Cliente cliente = this.pesquisarPeloId(id);
        clienteDAO.save(cliente);
        return veiculoSalvo;
        
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void apagar(@PathVariable int id) {
        if (clienteDAO.existsById(id)) {
            clienteDAO.deleteById(id);
        } else {
            throw new NaoEncontrado("Não encontrado");
        }
    }
    
}
