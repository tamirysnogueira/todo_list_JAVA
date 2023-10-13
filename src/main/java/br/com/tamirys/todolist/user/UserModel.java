package br.com.tamirys.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

//Data coloca para todos um getter e setter

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    //quando o dado for gerado, o bd terá a info de quando foi criado 
    @CreationTimestamp
    private  LocalDateTime createdAt;

    // é preciso informar como as classes irão acessar os métodos privados
    // para acessar é necessário acesar com getters 
    // se for necessário atríbuir algum valor a prpriedade privada se utiliza o setters
}
