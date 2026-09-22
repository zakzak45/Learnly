package com.learnly.api.model;

import java.time.Instant;

public class ModuleCompletion {

    private int moduleIndex;
    private Instant completedAt;
    private VerificationStatus status;

    public ModuleCompletion() {}

    public ModuleCompletion(int moduleIndex, VerificationStatus status) {
        this.moduleIndex = moduleIndex;
        this.completedAt = Instant.now();
        this.status = status;
    }

    public int getModuleIndex() { return moduleIndex; }
    public void setModuleIndex(int moduleIndex) { this.moduleIndex = moduleIndex; }
    public Instant getCompletedAt() { return completedAt; }
    public void setCompletedAt(Instant completedAt) { this.completedAt = completedAt; }
    public VerificationStatus getStatus() { return status; }
    public void setStatus(VerificationStatus status) { this.status = status; }
}
