package org.example.controllers;

import org.example.models.*;
import org.example.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("/user/{userId}/make")
    public String makeSurveyForm(@PathVariable Long userId,
                                 @RequestParam(required = false) Long surveyId,
                                 Model model) {
        Survey survey;
        if (surveyId != null) {
            // Load existing survey for editing
            survey = surveyRepository.findById(surveyId)
                    .orElseThrow(() -> new RuntimeException("Survey not found"));
        } else {
            // Create new survey
            survey = new Survey();
            survey.setUserId(userId);
        }
        model.addAttribute("survey", survey);
        return "createSurvey"; // Ensure this matches your HTML file name
    }

    @PostMapping("/user/{userId}/make")
    public String createSurvey(@PathVariable Long userId, @ModelAttribute Survey survey, Model model)  {
        survey.setUserId(userId);// Set hardcoded user ID for now
        survey.setClosed(false);
        Survey savedSurvey = surveyRepository.save(survey); // Save survey to database

        // Redirect to survey details page
        model.addAttribute("survey", savedSurvey);
        return "redirect:/surveys/" + savedSurvey.getSid();
    }

    // Step 3: Load Survey Details with Existing Questions
    @GetMapping("/surveys/{id}")
    public String getSurveyDetails(@PathVariable Long id, Model model) {
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new RuntimeException("Survey not found"));
        List<Question> questions = questionRepository.findBySid(id);
        Map<Long, List<MultipleChoice>> multipleChoiceOptionsMap = questions.stream()
                .filter(q -> q.getQuestionType() == QuestionType.MULTIPLE_CHOICE)
                .collect(Collectors.toMap(
                        Question::getQid,
                        q -> multipleChoiceRepository.findByqid(q.getQid())
                ));

        Map<Long, List<MultiSelect>> multiSelectOptionsMap = questions.stream()
                .filter(q -> q.getQuestionType() == QuestionType.MULTI_SELECT)
                .collect(Collectors.toMap(
                        Question::getQid,
                        q -> multiSelectRepository.findByqid(q.getQid())
                ));

        Map<Long, Range_question> rangeOptionsMap = questions.stream()
                .filter(q -> q.getQuestionType() == QuestionType.RANGE)
                .collect(Collectors.toMap(
                        Question::getQid,
                        q -> rangeQuestionRepository.findByqid(q.getQid()).get(0)
                ));

        model.addAttribute("survey", survey);
        model.addAttribute("questions", questions != null ? questions : List.of());
        model.addAttribute("multipleChoiceOptions", multipleChoiceOptionsMap);
        model.addAttribute("multiSelectOptions", multiSelectOptionsMap);
        model.addAttribute("rangeOptions", rangeOptionsMap);

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
        Map<Long, List<MultipleChoice>> multipleChoiceOptionsMap = questions.stream()
                .filter(q -> q.getQuestionType() == QuestionType.MULTIPLE_CHOICE)
                .collect(Collectors.toMap(
                        Question::getQid,
                        q -> multipleChoiceRepository.findByqid(q.getQid())
                ));

        Map<Long, List<MultiSelect>> multiSelectOptionsMap = questions.stream()
                .filter(q -> q.getQuestionType() == QuestionType.MULTI_SELECT)
                .collect(Collectors.toMap(
                        Question::getQid,
                        q -> multiSelectRepository.findByqid(q.getQid())
                ));

        Map<Long, Range_question> rangeOptionsMap = questions.stream()
                .filter(q -> q.getQuestionType() == QuestionType.RANGE)
                .collect(Collectors.toMap(
                        Question::getQid,
                        q -> rangeQuestionRepository.findByqid(q.getQid()).get(0)
                ));

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new RuntimeException("Survey not found"));
        model.addAttribute("questions", questions);
        model.addAttribute("survey", survey); // Add the survey to the model
        model.addAttribute("multipleChoiceOptions", multipleChoiceOptionsMap);
        model.addAttribute("multiSelectOptions", multiSelectOptionsMap);
        model.addAttribute("rangeOptions", rangeOptionsMap);
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
        questionRepository.deleteById(questionId); // Delete the question
        List<Question> questions = questionRepository.findBySid(surveyId); // Fetch updated questions
        Map<Long, List<MultipleChoice>> multipleChoiceOptionsMap = questions.stream()
                .filter(q -> q.getQuestionType() == QuestionType.MULTIPLE_CHOICE)
                .collect(Collectors.toMap(
                        Question::getQid,
                        q -> multipleChoiceRepository.findByqid(q.getQid())
                ));

        Map<Long, List<MultiSelect>> multiSelectOptionsMap = questions.stream()
                .filter(q -> q.getQuestionType() == QuestionType.MULTI_SELECT)
                .collect(Collectors.toMap(
                        Question::getQid,
                        q -> multiSelectRepository.findByqid(q.getQid())
                ));
        Map<Long, List<Range_question>> rangeOptionsMap = questions.stream()
                .filter(q -> q.getQuestionType() == QuestionType.RANGE)
                .collect(Collectors.toMap(
                        Question::getQid,
                        q -> rangeQuestionRepository.findByqid(q.getQid())
                ));

        model.addAttribute("questions", questions);
        model.addAttribute("survey", surveyRepository.findById(surveyId).orElseThrow()); // Include survey context
        model.addAttribute("multipleChoiceOptions", multipleChoiceOptionsMap);
        model.addAttribute("multiSelectOptions", multiSelectOptionsMap);
        model.addAttribute("rangeOptions", rangeOptionsMap);
        return "<ul>" +
                questions.stream().map(q -> {
                    StringBuilder questionHtml = new StringBuilder();
                    questionHtml.append("<li>")
                            .append(q.getQuestion_prompt())
                            .append(" - ")
                            .append(q.getQuestionType());

                    // Add options for MULTIPLE_CHOICE
                    if (q.getQuestionType() == QuestionType.MULTIPLE_CHOICE) {
                        questionHtml.append("<ul>");
                        List<MultipleChoice> options = multipleChoiceOptionsMap.get(q.getQid());
                        if (options != null) {
                            for (MultipleChoice option : options) {
                                questionHtml.append("<li>")
                                        .append(option.getOption_prompt())
                                        .append("</li>");
                            }
                        }
                        questionHtml.append("</ul>");
                    }

                    // Add options for MULTI_SELECT
                    if (q.getQuestionType() == QuestionType.MULTI_SELECT) {
                        questionHtml.append("<ul>");
                        List<MultiSelect> options = multiSelectOptionsMap.get(q.getQid());
                        if (options != null) {
                            for (MultiSelect option : options) {
                                questionHtml.append("<li>")
                                        .append(option.getOption_prompt())
                                        .append("</li>");
                            }
                        }
                        questionHtml.append("</ul>");
                    }

                    // Add range for RANGE questions
                    if (q.getQuestionType() == QuestionType.RANGE) {
                        List<Range_question> ranges = rangeOptionsMap.get(q.getQid());
                        if (ranges != null && !ranges.isEmpty()) {
                            Range_question range = ranges.get(0); // Get the first (or only) range
                            if (range != null) {
                                questionHtml.append("<ul><li>Range: ")
                                        .append(range.getStart())
                                        .append(" - ")
                                        .append(range.getEnd())
                                        .append("</li></ul>");
                            }
                        }
                    }

                    // Add remove button
                    questionHtml.append(" <button onclick='removeQuestion(")
                            .append(q.getQid())
                            .append(", ")
                            .append(surveyId)
                            .append(")'>Remove</button></li>");

                    return questionHtml.toString();
                }).collect(Collectors.joining()) +
                "</ul>";
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
