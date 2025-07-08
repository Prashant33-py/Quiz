package dev.app.services;

import dev.app.model.Question;
import dev.app.repos.QuestionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    private final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching all questions", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionRepo.save(question);
            return new ResponseEntity<>("Question Added", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding question", e);
            return new ResponseEntity<>("Error adding question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepo.findAllByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching all questions", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestions(List<Question> questions) {
        try {
            questionRepo.saveAll(questions);
            return new ResponseEntity<>(questions.size() + " questions added", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding questions", e);
            return new ResponseEntity<>("Error adding questions", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
