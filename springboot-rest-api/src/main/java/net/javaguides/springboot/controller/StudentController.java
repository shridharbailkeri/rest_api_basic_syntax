package net.javaguides.springboot.controller;


import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students") // base url
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "Ramesh", "Fadatare");

        //return new ResponseEntity<>(student, HttpStatus.OK); both are same
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header", "ramesh")
                .body(student);

    }

    @GetMapping
    public  ResponseEntity<List<Student>>  getStudents() {
        List<Student> students = new ArrayList<>();
        Student student = new Student(1, "Ramesh", "Fadatare");
        Student student2 = new Student(2, "Umesh", "Fadatare");
        Student student3 = new Student(3, "Ram", "Jadaw");
        Student student4 = new Student(4, "Sanjay", "Pawar");
        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        return ResponseEntity.ok(students);
    }

    // {id} is called URI template variable
    //  // http://localhost:8080/students/1/ramesh/fadatare
    // if URI template variable name and method argument name are different then use @PathVariable("id") int studentId
    // if both are same then use  @GetMapping("students/{id}") and public Student studentPathVariable(@PathVariable int id)
    // to get path variable form request url use @PathVariable bind uri template variable into method argument
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId, @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        Student student =  new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // http://localhost:8080/students/query?id=1 u need to to copy this url in the browser in order for this to work http://localhost:8080/students/query?id=1
    // http://localhost:8080/students/query?id=1&firstName=Ramesh&lastName=Fadatare
    // ?id=1 is the query parameter, to get query parameter from the url use @RequestParam to extract the value of query parameters in a request url

    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName, @RequestParam String lastName) {
        Student student =  new Student(id, firstName, lastName);
        return ResponseEntity.ok(student); // ok method returns http status called 200 ok
    }

    @PostMapping("create") // creating new  resource
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED); // no ok method here because we need http status 201 created
    }

    // put request to update the exisitng resource
    // http://localhost:8080/students/3/update client will send resource id in url and to update information in a http put request

    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // http://localhost:8080/students/3/delete

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }

}
