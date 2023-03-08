package com.procesos.inventario.services;
import com.procesos.inventario.models.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    public User getUser(Long id){
        User usuario = new User();
        usuario.setId(1L);
        usuario.setFirstName("David");
        usuario.setLastName("Garcia");
        usuario.setAddress("carrera 11 #5-44");
        usuario.setEmail("david@gmail.com");
        usuario.setPassword("1234");
        usuario.setBirthday(new Date(2002/11/25));


        return usuario;
    }
}
