package net.javaguides.springboot.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    private int id;

    private String firstName;

    private String lastName;
}
