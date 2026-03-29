import React, { useEffect, useState } from "react";

function Results() {
  const [results, setResults] = useState([]);
  const [error, setError] = useState("");

  const fetchResults = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/votes/results/1");
      if (!response.ok) {
        throw new Error(`HTTP ${response.status}`);
      }
      const data = await response.json();
      setResults(data);
      setError("");
    } catch (err) {
      console.error("Error fetching results:", err);
      setError("Could not load results.");
    }
  };

  useEffect(() => {
    fetchResults();
  }, []);

  return (
    <div>
      <h2>Live Results</h2>
      {error && <p>{error}</p>}
      <ul>
        {results.length === 0 ? (
          <li>No results yet.</li>
        ) : (
          results.map((result) => (
            <li key={result.candidateId}>
              {result.candidateName}: {result.voteCount} votes
            </li>
          ))
        )}
      </ul>
      <button onClick={fetchResults}>Refresh Results</button>
    </div>
  );
}

export default Results;
