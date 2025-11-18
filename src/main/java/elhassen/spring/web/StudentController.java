package elhassen.spring.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/call")
    public ResponseEntity<?> call(){
        return ResponseEntity.ok("Yap!");
    }
}