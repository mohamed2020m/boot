package app.leeuw.demo;

import app.leeuw.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/save")
    public void save(@RequestBody Student student){
        studentRepository.save(student);
    }

    @PostMapping("/save_all")
    public void saveAll(@RequestBody List<Student> students){
        studentRepository.saveAll(students);
    }

    @PutMapping("/update/{id}")
    public void udpateStudent(@PathVariable (required = true) String id, @RequestBody Student student){
        studentRepository.updateStudent(student.getNom(), student.getPrenom(), student.getDateNaissance(), Integer.parseInt(id));

    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable (required = true) String id){
        Student s = studentRepository.findById(Integer.parseInt(id));
        studentRepository.delete(s);
    }

    @GetMapping("/all")
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable (required = true) String id){
        return studentRepository.findById(Integer.parseInt(id));
    }

    @GetMapping(value = "/count")
    public long countStudent() {
        return studentRepository.count();
    }

    @GetMapping(value = "/byYear")
    public Collection<?> findByYear() {
        return studentRepository.findNbrStudentByYear();
    }
}

