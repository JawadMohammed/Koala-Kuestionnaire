package org.example.controllers;

import org.example.models.TextAnswer;
import org.example.models.MultipleChoiceAnswer;
import org.example.models.MultiSelectAnswer;
import org.example.models.RangeAnswer;
import org.example.repositories.TextAnswerRepository;
import org.example.repositories.MultipleChoiceAnswerRepository;
import org.example.repositories.MultiSelectAnswerRepository;
import org.example.repositories.RangeAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SubmissionController {

    @Autowired
    private TextAnswerRepository textAnswerRepository;

    @Autowired
    private MultipleChoiceAnswerRepository multipleChoiceAnswerRepository;

    @Autowired
    private MultiSelectAnswerRepository multiSelectAnswerRepository;

    @Autowired
    private RangeAnswerRepository rangeAnswerRepository;

    // List all submissions
    @GetMapping("/submissions")
    public String listSubmissions(Model model) {
        Set<Integer> submissionIds = new HashSet<>();
        submissionIds.addAll(textAnswerRepository.findDistinctSubmissionIds());
        submissionIds.addAll(multipleChoiceAnswerRepository.findDistinctSubmissionIds());
        submissionIds.addAll(multiSelectAnswerRepository.findDistinctSubmissionIds());
        submissionIds.addAll(rangeAnswerRepository.findDistinctSubmissionIds());

        model.addAttribute("submissionIds", submissionIds);
        return "submissions"; // Template name
    }

    // View submission details
    @GetMapping("/submissions/{id}")
    public String viewSubmissionDetails(@PathVariable("id") Integer submission_id, Model model) {
        List<TextAnswer> textAnswers = textAnswerRepository.findBySubmissionId(submission_id);
        List<MultipleChoiceAnswer> mcAnswers = multipleChoiceAnswerRepository.findBySubmissionId(submission_id);
        List<MultiSelectAnswer> msAnswers = multiSelectAnswerRepository.findBySubmissionId(submission_id);
        List<RangeAnswer> rangeAnswers = rangeAnswerRepository.findBySubmissionId(submission_id);

        model.addAttribute("submission_id", submission_id);
        model.addAttribute("textAnswers", textAnswers);
        model.addAttribute("mcAnswers", mcAnswers);
        model.addAttribute("msAnswers", msAnswers);
        model.addAttribute("rangeAnswers", rangeAnswers);

        return "submissionDetails"; // Template name
    }
}
