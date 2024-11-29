package com.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String id;
    String fullname;
    String password;
    String email;
    String phone;
    Boolean role;
    Boolean gender;
    Date birthday;

}
