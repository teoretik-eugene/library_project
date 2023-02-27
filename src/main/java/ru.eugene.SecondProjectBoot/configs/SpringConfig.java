package ru.eugene.SecondProjectBoot.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.eugene.SecondProjectBoot.models.PCB;
import ru.eugene.SecondProjectBoot.models.Schematic;
import ru.eugene.SecondProjectBoot.models.Student;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringConfig {

    @Bean(name = "schematic")
    public Schematic schematic(){
        List<String> literature = List.of("Хорвитц и Хилл - Основы схемотехники",
                "Шерц и Монк - Введение в электронику",
                "Джонс - Основы электроники");

        return new Schematic("Schematic", literature);
    }

//    @Bean(name = "pcb")
//    public PCB pcb(){
//        List<String> literature = List.of("Пирогова - основы по платам",
//                "Учебное пособие - технология изготовления печатных плат");
//        return new PCB("PCB", literature);
//    }

    @Bean
    public Student student(){
        return new Student("Eugene", 20);
    }

}
