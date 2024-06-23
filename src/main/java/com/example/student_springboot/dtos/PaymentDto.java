package com.example.student_springboot.dtos;

import com.example.student_springboot.entities.PaymentStatus;
import com.example.student_springboot.entities.PaymentType;
import com.example.student_springboot.entities.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class PaymentDto{
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
    private Student student;
}
