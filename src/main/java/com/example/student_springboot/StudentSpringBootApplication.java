package com.example.student_springboot;

import com.example.student_springboot.entities.Payment;
import com.example.student_springboot.entities.PaymentStatus;
import com.example.student_springboot.entities.PaymentType;
import com.example.student_springboot.entities.Student;
import com.example.student_springboot.repositories.PaymentRepository;
import com.example.student_springboot.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class StudentSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentSpringBootApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        PaymentRepository paymentRepository){
        return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).
                    firstName("Nouhaila").
                    code("1234543").programId("IIR").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).
                    firstName("Leila").
                    code("123433").programId("GC").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).
                    firstName("Imane").
                    code("1234093").programId("IFA").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).
                    firstName("HAMZA").
                    code("1200043").programId("IEE").build());
            PaymentType[] paymentTypes = PaymentType.values();
            Random random = new Random();
            studentRepository.findAll().forEach(student ->
                    {
                        for (int i = 0 ; i < 10; i++){
                            int index = random.nextInt(paymentTypes.length);
                            Payment payment = Payment.builder()
                                    .amount(1000+(int)(Math.random()+20000))
                                    .type(paymentTypes[index])
                                    .status(PaymentStatus.CREATED)
                                    .date(LocalDate.now()).student(student)
                                    .build();
                            paymentRepository.save(payment);
                        }
                    }
                    );
        };
    }
}
