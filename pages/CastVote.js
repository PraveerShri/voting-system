import React, { useState } from "react";

function CastVote() {
  const [candidate, setCandidate] = useState("");
  const [status, setStatus] = useState("");

  const handleVote = async () => {
    if (!candidate) {
      alert("Please select a candidate!");
      return;
    }

    const candidateId = candidate === "Candidate A" ? 1 : 2;

    try {
      const response = await fetch("http://localhost:8080/api/votes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          userId: 1,
          electionId: 1,
          candidateId: candidateId
        })
      });

      const message = await response.text();
      setStatus(message);
      alert(message);
    } catch (error) {
      console.error("Error casting vote:", error);
      setStatus("Failed to cast vote. See console for details.");
    }
  };

  return (
    <div>
      <h2>Cast Your Vote</h2>
      <select value={candidate} onChange={(e) => setCandidate(e.target.value)}>
        <option value="">--Select Candidate--</option>
        <option value="Candidate A">Candidate A</option>
        <option value="Candidate B">Candidate B</option>
      </select>
      <button onClick={handleVote}>Submit Vote</button>
      {status && <p>{status}</p>}
    </div>
  );
}

export default CastVote;
