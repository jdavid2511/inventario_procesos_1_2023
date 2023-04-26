package com.procesos.inventario.controllers;

import com.procesos.inventario.models.User;
import com.procesos.inventario.services.UserServiceImp;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping (value = "user/{id}")
    public ResponseEntity findUserById (@PathVariable Long id){
         Map response = new HashMap();
         try {
             return new ResponseEntity(userServiceImp.getUser(id), HttpStatus.OK);
         }catch (Exception e){
             response.put("status","404");
             response.put("massage", "no se necontro el usuario");
             return new ResponseEntity(response, HttpStatus.MULTI_STATUS);
         }
    }

    @PostMapping(value = "/user")
    public  ResponseEntity saveUser(@RequestBody User user){
        Map response = new HashMap();
        Boolean userResp = userServiceImp.createUser(user);

        if (userResp == true) {
            response.put("status","201");
            response.put("massage", "se creo el usuario");
           return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        response.put("status","400");
        response.put("massage","Hubo un error al crear el usuario");
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping (value = "users")
    public ResponseEntity allUsers (){
        Map response = new HashMap();
        try {
            return new ResponseEntity(userServiceImp.allUsers(), HttpStatus.OK);
        }catch (Exception e){
            response.put("status","404");
            response.put("massage", "no se encontraron los usuario");
            return new ResponseEntity(response, HttpStatus.MULTI_STATUS);
        }
    }

    @PutMapping(value = "/user/{id}")
    public  ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        Map response = new HashMap();
        Boolean userDB = userServiceImp.updateUser(id, user);
        try {
            if (userDB == null) {
                response.put("status", "201");
                response.put("massage", "No encontro usuario");
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            response.put("status", "201");
            response.put("massage", "se actualizo el usuario");
            return new ResponseEntity(userServiceImp.getUser(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", "201");
            response.put("massage", "No se actualizo el usuario");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "auth/login")
    public ResponseEntity login (@RequestBody User user){
        Map response = new HashMap();
        try {
            return new ResponseEntity(userServiceImp.login(user), HttpStatus.ACCEPTED);
        }catch (Exception e){
            response.put("status","404");
            response.put("massage",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
}
