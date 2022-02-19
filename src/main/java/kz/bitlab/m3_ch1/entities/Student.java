package kz.bitlab.m3_ch1.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_name", length = 100)
    private String name;

    @Column(name = "student_birthday", length = 50)
    private String birthday;

    @ManyToOne(fetch = FetchType.EAGER)
    private City city;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Sport> sport;
}
