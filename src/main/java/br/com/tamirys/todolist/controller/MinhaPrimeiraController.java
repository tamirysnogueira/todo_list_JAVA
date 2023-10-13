package br.com.tamirys.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller //utiliza quando quer criar páginas. Retornar algo sem ser objetos
@RestController //Quando se constrói APIs e quer retornar status code e etc
@RequestMapping("/primeiraRota") //rota http onde irá ser acessada a classe 


// tipo da classe pode ser: class, interface e etc
public class MinhaPrimeiraController {

    @GetMapping("/")
    // modificador (private, public, etc são tipos de acesso) - tipo de retorno da mensagem - nome do método
    public String primeiraMensagem() {
        return "Funcionou";
    }
}
