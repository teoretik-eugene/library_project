package ru.eugene.SecondProjectBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.eugene.SecondProjectBoot.configs.SpringConfig;
import ru.eugene.SecondProjectBoot.models.PCB;
import ru.eugene.SecondProjectBoot.models.Schematic;
import ru.eugene.SecondProjectBoot.models.Student;
import ru.eugene.SecondProjectBoot.models.Subject;

import javax.persistence.Entity;

@SpringBootApplication
public class SecondProjectBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondProjectBootApplication.class, args);
//		System.out.println("Testing");
//		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//
////		Subject subject = context.getBean("pcb", PCB.class);
////		System.out.println("subject: " + subject.getSubjectName() +
////				"\nrequired literature: " + subject.getRequiredLiterature());
//
//		Student student = context.getBean(Student.class);
//		System.out.println(student.getSubject());
	}

}
