/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.estacionamento.DAO;

import br.edu.ifrs.estacionamento.modelo.Estadia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jader
 */
@Repository
public interface EstadiaDAO extends CrudRepository<Estadia, Integer> {

    public Iterable<Estadia> findAll();

}
