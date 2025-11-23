package com.example.umc9th.dto.mission;
import jakarta.validation.constraints.NotNull;

public record ChallengeMissionRequest(
    @NotNull(message = "미션 ID를 입력해주세요.")
    Long missionId
) {

}
