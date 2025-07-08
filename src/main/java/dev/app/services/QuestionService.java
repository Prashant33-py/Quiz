package dev.app.services;

import dev.app.model.Question;
import dev.app.repos.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public void addQuestion(Question question) {
        questionRepo.save(question);
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepo.findAllByCategory(category);
    }

    public void addQuestions(List<Question> questions) {
        questionRepo.saveAll(questions);
    }
}
