package kz.bitlab.m3_ch1.controllers;

import kz.bitlab.m3_ch1.entities.Sport;
import kz.bitlab.m3_ch1.entities.Student;
import kz.bitlab.m3_ch1.service.CityService;
import kz.bitlab.m3_ch1.service.SportService;
import kz.bitlab.m3_ch1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CityService cityService;

    @Autowired
    private SportService sportService;

    @Autowired
    private Student emptyStudent;

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("student", emptyStudent);
        return "index";
    }

    @PostMapping(value = "/add-student")
    public String addStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{studentId}")
    public String getStudentById(Model model, @PathVariable(name = "studentId") Long id) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("sports", sportService.getAllSports());
        return "/details";
    }

    @PostMapping(value = "/update-student/{studentId}")
    public String updateStudent(@PathVariable(name = "studentId") Long studentId,
                                @ModelAttribute("student") Student student) {
        student.setId(studentId);
        studentService.updateStudent(student);
        return "redirect:/";
    }

    @PostMapping(value = "/add-sport")
    public String addSport(@RequestParam(name = "studentId") Long studentId,
                           @RequestParam(name = "sportId") Long sportId) {
        Sport sportArt = sportService.getSportById(sportId);
            if (sportArt != null) {
                Student student = studentService.getStudentById(studentId);
                if (student != null) {
                    List<Sport> sports = student.getSport();
                    if (sports == null) {
                        sports = new ArrayList<>();
                    }
                    sports.add(sportArt);
                    studentService.updateStudent(student);
                    return "redirect:/details/" + studentId;
                }
            }
        return "redirect:/";
    }

    @GetMapping(value = "/delete-student/{studentId}")
    public String deleteStudent(@PathVariable(name = "studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/";
    }
}
