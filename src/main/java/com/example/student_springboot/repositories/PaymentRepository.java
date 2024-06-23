package com.example.student_springboot.repositories;

import com.example.student_springboot.entities.Payment;
import com.example.student_springboot.entities.PaymentStatus;
import com.example.student_springboot.entities.PaymentType;
import com.example.student_springboot.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
