package dev.app.controllers;

import dev.app.model.Question;
import dev.app.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all-questions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category")
    public List<Question> getQuestionsByCategoryUsingRequestParam(@RequestParam(name = "category") String category){
        //Note: The url has to be /api/question/category?category=<Your category>
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/questions")
    public String addQuestions(@RequestBody List<Question> questions){
        questionService.addQuestions(questions);
        return questions.size() + " questions added";
    }

    @PostMapping
    public String addQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
        return "Question added";
    }

}
