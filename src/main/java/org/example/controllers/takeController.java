package org.example.controllers;

import org.example.models.*;
import org.example.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class takeController {

    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    private final TextAnswerRepository textAnswerRepository;

    @Autowired
    private final Range_questionRepository range_questionRepository;
    @Autowired
    private final RangeAnswerRepository rangeAnswerRepository;

    @Autowired
    private final MultipleChoiceRepository multipleChoiceRepository;
    @Autowired
    private final MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;

    @Autowired
    private final MultiSelectRepository multiSelectRepository;
    @Autowired
    private final MultiSelectAnswerRepository multiSelectAnswerRepository;



    public takeController(QuestionRepository qp,TextAnswerRepository taR,
                          Range_questionRepository rqR, RangeAnswerRepository raR,
                          MultipleChoiceRepository mcR , MultipleChoiceAnswerRepository mcaR,
                          MultiSelectRepository msR, MultiSelectAnswerRepository msaR ){
        this.questionRepository = qp;
        this.textAnswerRepository = taR;
        this.range_questionRepository = rqR;
        this.rangeAnswerRepository = raR;
        this.multipleChoiceRepository = mcR;
        this.multipleChoiceAnswerRepository = mcaR;
        this.multiSelectRepository = msR;
        this.multiSelectAnswerRepository = msaR;

    }
    @GetMapping("/nukeeverything")
    public void nuke(){
        questionRepository.deleteAll();
        range_questionRepository.deleteAll();
        multiSelectRepository.deleteAll();
        multipleChoiceRepository.deleteAll();
    }
    @GetMapping ("/take/addRand")
    public String randfill(){
        Question randQuestion1 = new Question();
        randQuestion1.setQuestion_prompt("this is a random TEXT question to be displayed");
        randQuestion1.setQuestionType(QuestionType.TEXT);
        randQuestion1.setQ_order(1);
        randQuestion1.setSid(617);

        questionRepository.save(randQuestion1);


        Question randQuestion2 = new Question();
        randQuestion2.setQuestion_prompt("this is a random RANGE question to be displayed");
        randQuestion2.setQuestionType(QuestionType.RANGE);
        randQuestion2.setQ_order(2);
        randQuestion2.setSid(617);

        questionRepository.save(randQuestion2);
        System.out.println(randQuestion2.getQid());

        Range_question rangeQues = new Range_question();
        rangeQues.setQid(randQuestion2.getQid());
        rangeQues.setStart(1);
        rangeQues.setEnd(10);

        range_questionRepository.save(rangeQues);

        Question randQuestion3 = new Question();
        randQuestion3.setQuestion_prompt("this is a random MULTIPLE CHOICE question to be displayed");
        randQuestion3.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        randQuestion3.setQ_order(3);
        randQuestion3.setSid(617);

        questionRepository.save(randQuestion3);


        MultipleChoice mc1 = new MultipleChoice();
        mc1.setQid(randQuestion3.getQid());
        mc1.setOption_prompt("MC Option 1");


        MultipleChoice mc2 = new MultipleChoice();
        mc2.setQid(randQuestion3.getQid());
        mc2.setOption_prompt("MC Option 2");


        multipleChoiceRepository.save(mc1);
        multipleChoiceRepository.save(mc2);


        Question randQuestion4 = new Question();
        randQuestion4.setQuestion_prompt("this is a random MULTIPLE SELECT question to be displayed");
        randQuestion4.setQuestionType(QuestionType.MULTI_SELECT);
        randQuestion4.setQ_order(4);
        randQuestion4.setSid(617);

        questionRepository.save(randQuestion4);


        MultiSelect ms1 = new MultiSelect();
        ms1.setQid(randQuestion4.getQid());
        ms1.setOption_prompt("MS Option 1");

        MultiSelect ms2 = new MultiSelect();
        ms2.setQid(randQuestion4.getQid());
        ms2.setOption_prompt("MS Option 2");

        multiSelectRepository.save(ms1);
        multiSelectRepository.save(ms2);

        return "take";

    }
    @ModelAttribute("questionTypes")
    public QuestionType[] getQuestionTypes() {
        return QuestionType.values();
    }
    @GetMapping("/take/{survey_id}")
    public String takeASurvey(Model model, @PathVariable Long survey_id){

        List<Question> testqs = questionRepository.findBySid(survey_id);
        List<List<String>> listOfLists;
        listOfLists = new ArrayList<List<String>>();

        for (Question q : testqs) {
            switch(q.getQuestionType()) {
                case QuestionType.TEXT:
                    listOfLists.add(new ArrayList<>());
                    break;
                case QuestionType.RANGE:
                    ArrayList<String> rangeoptions = new ArrayList();
                    rangeoptions.add(String.valueOf(range_questionRepository.findByqid(q.getQid()).getFirst().getStart()));
                    rangeoptions.add(String.valueOf(range_questionRepository.findByqid(q.getQid()).getFirst().getEnd()));
                    listOfLists.add(rangeoptions);
                    break;
                case QuestionType.MULTI_SELECT:
                    ArrayList<String> msoptions = new ArrayList();
                    for (MultiSelect m: multiSelectRepository.findByqid(q.getQid())){
                        msoptions.add(m.getOption_prompt());
                    }
                    listOfLists.add(msoptions);
                    break;
                case QuestionType.MULTIPLE_CHOICE:
                    ArrayList<String> mcoptions = new ArrayList();
                    for (MultipleChoice m: multipleChoiceRepository.findByqid(q.getQid())){
                        mcoptions.add(m.getOption_prompt());
                    }
                    listOfLists.add(mcoptions);
                    break;

                default:
                    break;
            }
        }


        model.addAttribute("questions", testqs);
        model.addAttribute("options", listOfLists);
        model.addAttribute("surveyId", survey_id);

        return "takeASurvey";

    }

    @PostMapping("/take/{survey_id}")
    public String taken(Model model, @PathVariable Long survey_id, @RequestParam Map<String, String> formData) {
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            questionRepository.findById(Long.valueOf(entry.getKey())).ifPresent(
                    question -> {
                        switch(question.getQuestionType()) {
                            case QuestionType.TEXT:
                                TextAnswer ta = new TextAnswer();
                                ta.setQ_id(Integer.parseInt(entry.getKey()));
                                ta.setAnswer(entry.getValue());
                                textAnswerRepository.save(ta);
                                break;

                            case QuestionType.RANGE:
                                RangeAnswer ra = new RangeAnswer();
                                ra.setQ_id(Integer.parseInt(entry.getKey()));
                                ra.setAnswer(Integer.parseInt(entry.getValue()));
                                rangeAnswerRepository.save(ra);


                                break;
                            case QuestionType.MULTI_SELECT:
                                MultiSelectAnswer ms = new MultiSelectAnswer();
                                ms.setQ_id(Integer.parseInt(entry.getKey()));
                                ms.setAnswer(entry.getValue());
                                multiSelectAnswerRepository.save(ms);

                                break;
                            case QuestionType.MULTIPLE_CHOICE:
                                MultipleChoiceAnswer mc = new MultipleChoiceAnswer();
                                mc.setQ_id(Integer.parseInt(entry.getKey()));
                                mc.setAnswer(entry.getValue());
                                multipleChoiceAnswerRepository.save(mc);
                                break;

                            default:
                                break;
                        }
                    }


            );

            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        model.addAttribute("surveyId", survey_id);
        return "taken";
    }
    @GetMapping("/take")
    public String askSurvey(){
        return "take";
    }

    @PostMapping("/take")
    public String findSurvey(@RequestParam Long survey_id){
        return "redirect:/take/"+survey_id;
    }

}
