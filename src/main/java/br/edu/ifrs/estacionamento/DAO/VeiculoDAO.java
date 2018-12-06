/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.estacionamento.DAO;

import br.edu.ifrs.estacionamento.modelo.Veiculo;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Jader
 */
@Repository
public interface VeiculoDAO extends CrudRepository<Veiculo, Integer>{
    
    public Iterable<Veiculo> findAll();

    public Iterable<Veiculo> save(RequestBody Veiculo);

    public Optional<Veiculo> findAllByPlaca(String placa);
    
}
