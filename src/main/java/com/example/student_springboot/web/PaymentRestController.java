package com.example.student_springboot.web;

import com.example.student_springboot.entities.Payment;
import com.example.student_springboot.entities.PaymentStatus;
import com.example.student_springboot.entities.PaymentType;
import com.example.student_springboot.entities.Student;
import com.example.student_springboot.repositories.PaymentRepository;
import com.example.student_springboot.repositories.StudentRepository;
import com.example.student_springboot.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController @AllArgsConstructor
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;
    @GetMapping(path = "/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }
    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> paymentByStudent(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }
    @GetMapping(path = "/payments/byStatus")
    public List<Payment> paymentByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }
    @GetMapping(path = "/payments/byType")
    public List<Payment> paymentByType(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);
    }
    @GetMapping(path = "/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }
    @GetMapping(path = "/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }
    @GetMapping(path = "/students/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findStudentByCode(code);
    }
    @GetMapping(path = "/studentsByProgramId")
    public List<Student> getStudentByProgramId(@RequestParam String programId){
        return studentRepository.findStudentByProgramId(programId);
    }

    @PutMapping(path = "/payments/{id}")
    public Payment updatePaymentStatus(PaymentStatus status, @PathVariable long id){
        return this.paymentService.updatePaymentStatus(status,id);
    }

    @PostMapping(path = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment addPayment(@RequestParam MultipartFile file,
                              LocalDate date,
                              double amount,
                              PaymentType type,
                              String studentCode) throws IOException {
        return this.paymentService.addPayment(file,date,amount,type,studentCode);
    }
    @GetMapping(path = "/payments/{id}/file", produces = {MediaType.APPLICATION_PDF_VALUE})
    public byte[] getPaymentFile(@PathVariable Long id) throws IOException {
        return this.paymentService.getPaymentFile(id);
    }
}
