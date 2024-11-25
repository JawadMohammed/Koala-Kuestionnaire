package org.example.controllers;

import org.example.models.*;
import org.example.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class MakeController {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final MultipleChoiceRepository multipleChoiceRepository;
    private final MultiSelectRepository multiSelectRepository;
    private final Range_questionRepository rangeQuestionRepository;

    public MakeController(SurveyRepository surveyRepository, QuestionRepository questionRepository, MultipleChoiceRepository multipleChoiceRepository, MultiSelectRepository multiSelectRepository, Range_questionRepository rangeQuestionRepository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.multipleChoiceRepository = multipleChoiceRepository;
        this.multiSelectRepository = multiSelectRepository;
        this.rangeQuestionRepository = rangeQuestionRepository;
    }

    @GetMapping("/make")
    public String makeSurveyForm(Model model) {
        Survey survey = new Survey();
        survey.setUser_id(1); // Hardcoded user ID for now
        model.addAttribute("survey", survey); // Add empty survey object for the form
        return "createSurvey";
    }

    @PostMapping("/make")
    public String createSurvey(@ModelAttribute Survey survey, Model model) {
        survey.setUser_id(1); // Set hardcoded user ID for now
        survey.setClosed(false);
        Survey savedSurvey = surveyRepository.save(survey); // Save survey to database
        System.out.println("Saved Survey: " + savedSurvey);

        // Redirect to survey details page
        model.addAttribute("survey", savedSurvey);
        return "redirect:/surveys/" + savedSurvey.getSid(); // Redirect to the survey details
    }

    // Step 3: Load Survey Details with Existing Questions
    @GetMapping("/surveys/{id}")
    public String getSurveyDetails(@PathVariable Long id, Model model) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new RuntimeException("Survey not found"));
        List<Question> questions = questionRepository.findBySid(id);
        model.addAttribute("survey", survey);
        model.addAttribute("questions", questions != null ? questions : List.of());
        return "makePage";
    }


    @PostMapping("/questions/add")
    public String addQuestion(@RequestParam Long surveyId,
                              @RequestParam String prompt,
                              @RequestParam QuestionType questionType,
                              @RequestParam(required = false) String multiSelectOptions, // Multi-Select options
                              @RequestParam(required = false) String multipleChoiceOptions, // Multiple Choice options
                              @RequestParam(required = false) Integer minValue, // Range start value
                              @RequestParam(required = false) Integer maxValue, // Range end value
                              Model model) {

        // Step 1: Create and Save the Base Question
        Question question = new Question();
        question.setSid(surveyId);
        question.setQuestion_prompt(prompt);
        question.setQuestionType(questionType);
        questionRepository.save(question);

        // Step 2: Handle Type-Specific Fields
        switch (questionType) {
            case MULTI_SELECT:
                if (multiSelectOptions != null && !multiSelectOptions.isEmpty()) {
                    List<String> options = Arrays.asList(multiSelectOptions.split(","));
                    for (String option : options) {
                        MultiSelect multiSelect = new MultiSelect();
                        multiSelect.setQid(question.getQid());
                        multiSelect.setOption_prompt(option.trim());
                        multiSelectRepository.save(multiSelect);
                    }
                }
                break;

            case MULTIPLE_CHOICE:
                if (multipleChoiceOptions != null && !multipleChoiceOptions.isEmpty()) {
                    List<String> options = Arrays.asList(multipleChoiceOptions.split(","));
                    for (String option : options) {
                        MultipleChoice multipleChoice = new MultipleChoice();
                        multipleChoice.setQid(question.getQid());
                        multipleChoice.setOption_prompt(option.trim());
                        multipleChoiceRepository.save(multipleChoice);
                    }
                }
                break;

            case RANGE:
                if (minValue != null && maxValue != null) {
                    Range_question rangeQuestion = new Range_question();
                    rangeQuestion.setQid(question.getQid());
                    rangeQuestion.setStart(minValue);
                    rangeQuestion.setEnd(maxValue);
                    rangeQuestionRepository.save(rangeQuestion);
                }
                break;

            case TEXT:
            default:
                // No additional processing required
                break;
        }

        // Step 3: Fetch Updated Question List
        List<Question> questions = questionRepository.findBySid(surveyId);
        model.addAttribute("questions", questions);
        return "questionsList :: questionsList"; // Thymeleaf fragment
    }


    @GetMapping("/questions/type-fields")
    public String getTypeSpecificFields(@RequestParam(required = false) QuestionType questionType, Model model) {
        System.out.println("Question Type Received: " + questionType); // Debug log
        model.addAttribute("questionType", questionType);
        return "typeSpecificFields :: typeSpecificFields";
    }

    @PostMapping("/questions/remove")
    @ResponseBody
    public String removeQuestion(@RequestParam Long questionId, @RequestParam Long surveyId, Model model) {
        questionRepository.deleteById(questionId); // Deletes the question by its ID

        // Fetch updated list of questions for the survey
        List<Question> questions = questionRepository.findBySid(surveyId);
        model.addAttribute("questions", questions);

        // Return updated question list fragment
        return "questionsList :: questionsList";
    }








    /*
    @GetMapping("/testSaveSurvey")
    public void testSaveSurvey() {
        Survey survey = new Survey();
        //survey.setSid(1);
        survey.setUser_id(1);
        survey.setTitle("Test Survey");
        survey.setDescription("Test Description");
        survey.setClosed(false);
        Survey savedSurvey = surveyRepository.save(survey);
        System.out.println("Test Saved Survey: " + savedSurvey);
    }
    */
}
