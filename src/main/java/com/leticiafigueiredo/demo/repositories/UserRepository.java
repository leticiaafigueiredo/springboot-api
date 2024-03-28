package com.leticiafigueiredo.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leticiafigueiredo.demo.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> { //conecta com a classe User que tem o id tipo Long
    
    //esse metodo tem varias funcoes para busca na tabela como:
    // - findById(List<User> findById(Long id);
    // - fingByPassword
    // - findByTask
    // ...
}
