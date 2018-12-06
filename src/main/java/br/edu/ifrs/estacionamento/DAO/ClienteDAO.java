/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.estacionamento.DAO;

import br.edu.ifrs.estacionamento.modelo.Cliente;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jader
 */
@Repository
public interface ClienteDAO  extends CrudRepository<Cliente, Integer>{

    public Iterable<Cliente> findAll();

    public Iterable<Cliente> findByNome(String nome);

    public Optional<Cliente> findAllById(int id);
    
    
}
