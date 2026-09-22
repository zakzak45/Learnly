package com.learnly.api.model;

public enum VerificationStatus {
    SELF_REPORTED,   // student marked it done, not flagged
    PENDING_REVIEW,  // spot-check flagged it, awaiting admin action
    VERIFIED,        // admin confirmed it
    REJECTED         // admin rejected it — doesn't count toward readiness
}
