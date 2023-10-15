package br.com.tamirys.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    //não é preciso instanciar a classe quando se usa o static
    public static void copyNonNullProperties(Object source, Object target) {

        //consegue copiar as propriedades de um objeto para outro objeto e como terceiro parâmetro pode se passar um classe
        //utilizando dessa regra
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    //pegar todas as propriedades nulas e atribuir para dentro do BeanUtils.copyProperties e fazer a conversão para ter a mescla das informações
    public static String[] getNullPropertyNames(Object source) {

        //BeanWrapper é uma interface que fornece métodos para acessar as propriedades dos objetos
        //BeanWrapperImpl é a implementação da interface
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        //pd:pds quer dizer que vai pegar os pd que estão dentro de pds

        for(PropertyDescriptor pd:pds) {

            //obtém o valor da propriedade atual
            Object srcValue = src.getPropertyValue(pd.getName());

            //se a propriedade for nula, ela irá ser adicionada ao conjunto
            if(srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);
    }
    
}
