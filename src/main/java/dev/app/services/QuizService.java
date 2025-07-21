package dev.app.services;

import dev.app.model.Question;
import dev.app.model.Quiz;
import dev.app.repos.QuestionRepo;
import dev.app.repos.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepo questionRepo;

    public Quiz createQuiz(String category, int numOfQuestions, String title) {
        List<Question> questions = questionRepo.findRandomQuestionsByCategory(numOfQuestions, category);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        return quizRepository.save(quiz);
    }
}
