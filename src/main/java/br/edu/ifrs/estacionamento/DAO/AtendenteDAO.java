/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.estacionamento.DAO;

import br.edu.ifrs.estacionamento.modelo.Atendente;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jader
 */
@Repository
public interface AtendenteDAO extends CrudRepository<Atendente, Integer> {
    
    public Iterable<Atendente> findAll();

    public Optional<Atendente> findAllById(int id);
    
    
    

}
