package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class IssuedDeviceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeld;
    private Long deviceltemld;

    private LocalDate issuedDate;
    private LocalDate returnedDate;

    // ISSUED / RETURNED
    private String status;

    // getters & setters
}
