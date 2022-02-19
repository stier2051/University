package kz.bitlab.m3_ch1.service.impl;

import kz.bitlab.m3_ch1.entities.Student;
import kz.bitlab.m3_ch1.repository.StudentRepository;
import kz.bitlab.m3_ch1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }


    public Student getStudentById(Long id) {
        return studentRepository.getOne(id);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
