import React, { useEffect, useState } from "react";
import API from "../services/api";

export default function Results() {

  const [results, setResults] = useState([]);

  useEffect(() => {
    API.get("/elections/1/results")
      .then(res => setResults(res.data));
  }, []);

  return (
    <div>
      <h2>Results</h2>

      {results.map((r, i) => (
        <div key={i}>
          {r.candidateName} - {r.votes}
        </div>
      ))}
    </div>
  );
}