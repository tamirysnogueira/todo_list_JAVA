package br.com.tamirys.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


// na interface não há implementação dos métodos, apenas a representação
//diferente da class

// <> significa que a classe recebe um generator. Generator são atributos dinâmicos 
// primeiro parâmetro: qual a classe que esse repo está representando 
// segundo parâmetro: qual tipo de ID que a entity tem

// estamos usando o JPA repository para fazer comando SQL no banco de dados 
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}
