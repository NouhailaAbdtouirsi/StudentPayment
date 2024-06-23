package com.example.student_springboot.services;

import com.example.student_springboot.entities.Payment;
import com.example.student_springboot.entities.PaymentStatus;
import com.example.student_springboot.entities.PaymentType;
import com.example.student_springboot.entities.Student;
import com.example.student_springboot.repositories.PaymentRepository;
import com.example.student_springboot.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;


    public Payment addPayment(MultipartFile file,
                              LocalDate date,
                              double amount,
                              PaymentType type,
                              String studentCode) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"),"/images/","payments");
        if (!Files.exists(path)){
            Files.createDirectories(path);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"/images/","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Student student = studentRepository.findStudentByCode(studentCode);
        Payment payment = Payment.builder().
                amount(amount).
                date(date).
                type(type).
                student(student).
                file(filePath.toUri().toString()).
                build();
        return paymentRepository.save(payment);
    }
    public Payment updatePaymentStatus(PaymentStatus status, long id){
        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
    public byte[] getPaymentFile(Long id) throws IOException {
        Payment payment = paymentRepository.findById(id).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }
}
