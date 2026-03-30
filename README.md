

# 🚀 Resume Analyzer (ATS-Style)

<p align="center">
<b>
A full-stack ATS-style Resume Analyzer that compares resumes with job descriptions, calculates match percentage, highlights missing skills, and provides AI-style improvement suggestions.
</b>
</p>

<p align="center">
<b>Spring Boot • Java • JavaScript • Apache PDFBox • Apache POI</b>
</p>

---

## 📘 PROJECT OVERVIEW

Modern companies rely on **Applicant Tracking Systems (ATS)** to filter resumes before a human recruiter ever reviews them. These systems primarily use keyword matching, skill alignment, and formatting rules, often rejecting qualified candidates without providing any feedback.

This project simulates **real-world ATS screening behavior** by:

* Extracting and normalizing resume content
* Comparing skills using an ATS-style repository
* Calculating match percentage
* Identifying missing skills
* Generating **AI-style improvement suggestions**

👉 The goal is to help candidates understand ATS decisions and optimize their resumes before applying.

---

## ⭐ FEATURES

<table width="100%">
<tr>
<td width="33%" align="center" valign="top">

### 📄 ATS Resume Matching

* Resume vs Job Description
* Keyword-based evaluation
* Real ATS-style logic

</td>
<td width="33%" align="center" valign="top">

### 🔍 Skill Gap Analysis

* Matched skills
* Missing skills
* Clear visual feedback

</td>
<td width="33%" align="center" valign="top">

### 📁 Multi-Format Support

* PDF resumes
* DOCX resumes
* Pasted text input

</td>
</tr>

<tr>
<td width="33%" align="center" valign="top">

### 🤖 AI Resume Suggestions

* Improvement tips
* Skill highlighting
* Recruiter-style feedback

</td>
<td width="33%" align="center" valign="top">

### 📊 Match Percentage Scoring

* ATS-style scoring
* Quick evaluation
* Easy comparison

</td>
<td width="33%" align="center" valign="top">

### 🎯 Transparent ATS Insights

* No black-box decisions
* Explainable results
* Real screening behavior

</td>
</tr>
</table>

---

## 🛠️ TECH STACK

<table width="100%">
<tr>

<td width="33%" valign="top" align="center">

### 🔧 BACKEND

<i>Business logic, ATS processing, APIs</i>

* Java 17
* Spring Boot
* REST APIs
* Apache PDFBox (PDF parsing)
* Apache POI (DOCX parsing)
* Maven

</td>

<td width="33%" valign="top" align="center">

### 🎨 FRONTEND

<i>User interface & client-side logic</i>

* HTML
* CSS
* JavaScript
* Fetch API

</td>

<td width="33%" valign="top" align="center">

### 🧰 TOOLS & UTILITIES

<i>Development, testing & configuration</i>

* Git & GitHub
* Postman
* VS Code / IntelliJ
* application.properties
* pom.xml

</td>

</tr>
</table>

---

## 🏗️ SYSTEM ARCHITECTURE

<p align="center">
  <img src="img/Architecture.png" width="100%" />
</p>

<p align="center">
<i>
End-to-end ATS workflow showing resume upload, document parsing, text normalization, skill matching, scoring, AI suggestions, and frontend rendering.
</i>
</p>

---

## 🖥️ FRONTEND SCREENS & USER EXPERIENCE

<table width="100%">
<tr>
<td align="center" valign="top">


---

## 📂 PROJECT STRUCTURE

```
├── src/
│   └── main/
│       └── java/com/yourpackage/resumeanalyzer/
├── img/
│   ├── Architecture.png
│   ├── webpage.png
├── pom.xml
```

---

