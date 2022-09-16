package com.example.pp_3_1_5_restclient;

import com.example.pp_3_1_5_restclient.client.Communication;
import com.example.pp_3_1_5_restclient.config.MyConfig;
import com.example.pp_3_1_5_restclient.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;

//@SpringBootApplication
public class Pp315RestClientApplication {

    public static void main(String[] args) {

//        SpringApplication.run(Pp315RestClientApplication.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        // ПОЛУЧИТЬ ВСЕХ ПОЛЬЗОВТЕЛЕЙ
        String allUsers = communication.getAllUsers();

        // ДОБАВИТЬ НОВОГО ПОЛЬЗОВАТЕЛЯ
        User user = new User(3L, "James", "Brown", (byte) 25);
        communication.addUser(user);

        // РЕДАКТИРОВАТЬ ПОЛЬЗОВАТЕЛЯ
        user.setName("Thomas");
        user.setLastName("Shelby");
        communication.updateUser(user);

        //УДАЛИТЬ ПОЛЬЗОВАТЕЛЯ
        communication.deleteUser(user, 3L);

        // ВЫВОД КОДА
        System.out.println(communication.getCode());
        System.out.println(communication.getCode().length());

    }

}
