package dev.app.controllers;

import dev.app.model.Quiz;
import dev.app.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Value("${sample.user}")
    private String sampleUser;

    @GetMapping("/sample-user")
    public void printSampleUser(){
        System.out.println(sampleUser);
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int numOfQuestions, @RequestParam String title) {
        return new ResponseEntity<>(quizService.createQuiz(category, numOfQuestions, title), HttpStatus.CREATED);
    }
}
