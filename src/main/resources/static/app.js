async function analyzeMatch() {
    console.log("🔥 Button Clicked");

    // Safely get elements
    const loader = document.getElementById("loader");
    const result = document.getElementById("result");
    const scoreText = document.getElementById("scoreText");
    const matchedEl = document.getElementById("matched");
    const missingEl = document.getElementById("missing");
    const aiBox = document.getElementById("aiSuggestions");
    const suggestionList = document.getElementById("suggestionList");

    // Debug elements
    console.log("Elements:", {
        loader, result, scoreText, matchedEl, missingEl, aiBox, suggestionList
    });

    // Prevent crash if elements missing
    if (loader) loader.classList.remove("hidden");
    if (result) result.classList.add("hidden");

    const resumeFile = document.getElementById("resumeFile")?.files[0];
    const jdFile = document.getElementById("jdFile")?.files[0];
    const resumeText = document.getElementById("resumeText")?.value;
    const jdText = document.getElementById("jdText")?.value;

    console.log("Inputs:", { resumeFile, jdFile, resumeText, jdText });

    // Validation
    if (!resumeFile && !resumeText) {
        alert("Please upload or paste resume");
        return;
    }

    if (!jdFile && !jdText) {
        alert("Please upload or paste job description");
        return;
    }

    const formData = new FormData();
    if (resumeFile) formData.append("resumeFile", resumeFile);
    if (jdFile) formData.append("jdFile", jdFile);
    formData.append("resumeText", resumeText || "");
    formData.append("jdText", jdText || "");

    try {
        console.log("🚀 Sending request to backend...");

        const res = await fetch("/api/analyze", {
            method: "POST",
            body: formData
        });

        if (!res.ok) {
            throw new Error("Server responded with " + res.status);
        }

        const data = await res.json();
        console.log("✅ API RESPONSE:", data);

        if (loader) loader.classList.add("hidden");
        if (result) result.classList.remove("hidden");

        // ✅ SCORE
        if (scoreText) {
            scoreText.innerText = Math.round(data.score || 0) + "%";
        }

        // ✅ SKILLS
        if (matchedEl && missingEl) {
            matchedEl.innerHTML = "";
            missingEl.innerHTML = "";

            (data.matchedSkills || []).forEach(skill => {
                const li = document.createElement("li");
                li.textContent = skill;
                matchedEl.appendChild(li);
            });

            (data.missingSkills || []).forEach(skill => {
                const li = document.createElement("li");
                li.textContent = skill;
                missingEl.appendChild(li);
            });
        }

        // ✅ AI SUGGESTIONS
        if (aiBox && suggestionList) {
            suggestionList.innerHTML = "";
            aiBox.classList.remove("hidden");

            (data.suggestions || []).forEach(s => {
                const li = document.createElement("li");
                li.textContent = s;
                suggestionList.appendChild(li);
            });
        }

    } catch (err) {
        console.error("❌ ERROR:", err);
        alert("Backend error — check server logs");

        if (loader) loader.classList.add("hidden");
    }
}