package com.niroj.resumeanalyzer.service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.niroj.resumeanalyzer.model.AnalysisResult;

@Service
public class ResumeAnalyzerService {

    private final TextExtractor textExtractor;

    public ResumeAnalyzerService(TextExtractor textExtractor) {
        this.textExtractor = textExtractor;
    }

    public AnalysisResult analyze(
            MultipartFile resumeFile,
            MultipartFile jdFile,
            String resumeText,
            String jdText
    ) {

        System.out.println("🔥 ANALYZE METHOD CALLED");

        String resume = getText(resumeFile, resumeText).toLowerCase().trim();
        String jd = getText(jdFile, jdText).toLowerCase().trim();

        System.out.println("RESUME TEXT: [" + resume + "]");
        System.out.println("JD TEXT: [" + jd + "]");

        // 🚨 Validation
        if (resume.isBlank()) {
            return new AnalysisResult(0, Set.of(), Set.of("resume text missing"));
        }

        if (jd.isBlank()) {
            return new AnalysisResult(0, Set.of(), Set.of("jd text missing"));
        }

        Set<String> resumeSkills = new HashSet<>();
        Set<String> jdSkills = new HashSet<>();

        // 🔥 FIXED MATCHING (WORD BOUNDARY + SAFE REGEX)
        for (String skill : SkillRepository.getAllSkills()) {

            String regex = "\\b" + Pattern.quote(skill.toLowerCase()) + "\\b";

            if (Pattern.compile(regex).matcher(resume).find()) {
                resumeSkills.add(skill);
            }

            if (Pattern.compile(regex).matcher(jd).find()) {
                jdSkills.add(skill);
            }
        }

        // 🚨 Safety check
        if (jdSkills.isEmpty()) {
            return new AnalysisResult(
                    0,
                    Set.of(),
                    Set.of("no recognizable skills found in JD")
            );
        }

        // ✅ Matched
        Set<String> matchedSkills = new HashSet<>(resumeSkills);
        matchedSkills.retainAll(jdSkills);

        // ✅ Missing
        Set<String> missingSkills = new HashSet<>(jdSkills);
        missingSkills.removeAll(resumeSkills);

        // ✅ Score
        double score = ((double) matchedSkills.size() / jdSkills.size()) * 100;

        AnalysisResult result = new AnalysisResult(score, matchedSkills, missingSkills);

        // 🤖 Suggestions
        Set<String> suggestions = new HashSet<>();

        if (!missingSkills.isEmpty()) {
            suggestions.add("Add or highlight these skills: " + String.join(", ", missingSkills));
        }

        if (!resume.contains("project")) {
            suggestions.add("Add a Projects section with measurable impact");
        }

        if (!resume.matches(".*\\d+.*")) {
            suggestions.add("Quantify achievements (e.g., improved performance by 30%)");
        }

        if (score >= 80) {
            suggestions.add("Strong match! Tailor your resume summary for this role");
        }

        result.setSuggestions(suggestions);

        // 🧪 Logs
        System.out.println("Resume Skills: " + resumeSkills);
        System.out.println("JD Skills: " + jdSkills);
        System.out.println("Matched: " + matchedSkills);
        System.out.println("Missing: " + missingSkills);
        System.out.println("Score: " + score);

        return result;
    }

    private String getText(MultipartFile file, String fallbackText) {

        if (fallbackText != null && !fallbackText.isBlank()) {
            return fallbackText;
        }

        if (file != null && !file.isEmpty()) {
            String extracted = textExtractor.extract(file);
            if (extracted != null && !extracted.isBlank()) {
                return extracted;
            }
        }

        return "";
    }
}