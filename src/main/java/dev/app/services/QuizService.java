package dev.app.services;

import dev.app.model.Question;
import dev.app.model.QuestionWrapper;
import dev.app.model.Quiz;
import dev.app.model.QuizSelection;
import dev.app.repos.QuestionRepo;
import dev.app.repos.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Question question: questions){
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionWrappers.add(questionWrapper);
        }

        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(Integer id, List<QuizSelection> selectedAnswers) {
        int score = 0;
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        int i = 0;
        for (QuizSelection selection: selectedAnswers){
            if (selection.getAnswer().equals(questions.get(i).getRightAnswer())){
                score++;
            }
            i++;
        }

        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
