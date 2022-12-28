package com.softgraf.farmacia.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/*
 * As transações seão controladas através de interceptadores
 * Aqui definimos uma anotação que fará a ligação entre uma classe
 * ou método com o interceptor (@Interceptor)
 */

//Epecifica que a anotação definida nesse arquivo será usado para 
//fazer a ligação (binding) entre uma classe ou método e um interceptor
@InterceptorBinding

//anotação poderá ser usada para marcar uma classe ou método
//TYPE = Classe
//METHOD = 	Método da classe
@Target({ElementType.TYPE, ElementType.METHOD})

//a anotação será armazenada no .class
@Retention(RetentionPolicy.RUNTIME) // - tempo de execução

//@Transacional deverá ser usada em conjunto com @Interceptor
public @interface Transacional {

}
