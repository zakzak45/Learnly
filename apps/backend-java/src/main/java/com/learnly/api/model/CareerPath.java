package com.learnly.api.model;

import java.util.List;

public record CareerPath(
        String id,
        String name,
        String demand,
        List<String> sectors,
        List<String> starterSkills
) {
}
