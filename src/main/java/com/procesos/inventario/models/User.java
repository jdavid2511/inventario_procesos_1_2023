package com.procesos.inventario.models;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String FirstName;
    private String LastName;
    private String Address;
    private String Email;
    private String Password;
    private Date Birthday;
}
