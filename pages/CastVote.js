import React, { useState } from "react";
import API from "../services/api";

export default function CastVote() {
  const [step, setStep] = useState(1);
  const [vote, setVote] = useState({
    voterId: 1,
    electionId: 1,
    candidateId: null
  });
  const [otp, setOtp] = useState("");

  const sendOtp = async () => {
    await API.post("/vote/send-otp?userId=1");
    setStep(4);
  };

  const verifyOtp = async () => {
    await API.post(`/vote/verify-otp?userId=1&otp=${otp}`);
    await API.post("/vote/cast", vote);
    alert("Vote Cast Successfully!");
  };

  return (
    <div>
      <h2>Cast Vote</h2>

      {step === 1 && (
        <button onClick={() => setStep(2)}>Select Election</button>
      )}

      {step === 2 && (
        <button onClick={() => {
          setVote({...vote, candidateId: 101});
          setStep(3);
        }}>
          Select Candidate
        </button>
      )}

      {step === 3 && (
        <button onClick={sendOtp}>Confirm & Send OTP</button>
      )}

      {step === 4 && (
        <div>
          <input
            placeholder="Enter OTP"
            onChange={(e) => setOtp(e.target.value)}
          />
          <button onClick={verifyOtp}>Verify & Submit</button>
        </div>
      )}
    </div>
  );
}