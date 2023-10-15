package com.enock.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
	UserModel findByUsername(String username); // spring data cria o metodo apenas chamando o atributo e oq 
	

}
