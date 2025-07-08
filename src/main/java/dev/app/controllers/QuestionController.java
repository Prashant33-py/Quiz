package dev.app.controllers;

import dev.app.model.Question;
import dev.app.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all-questions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category")
    public ResponseEntity<List<Question>> getQuestionsByCategoryUsingRequestParam(@RequestParam(name = "category") String category){
        //Note: The url has to be /api/question/category?category=<Your category>
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/questions")
    public ResponseEntity<String> addQuestions(@RequestBody List<Question> questions){
        return questionService.addQuestions(questions);
    }

    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

}
