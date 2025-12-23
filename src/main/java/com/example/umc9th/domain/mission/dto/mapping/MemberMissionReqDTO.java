package com.example.umc9th.domain.mission.dto.mapping;

import com.example.umc9th.domain.mission.enums.MissionState;
import jakarta.validation.constraints.NotNull;

public class MemberMissionReqDTO {
    public record AddMemberMissionDTO(
            @NotNull(message = "유저 ID는 필수 입력 값입니다.")
            Long memberId,
            @NotNull(message = "미션 ID는 필수 입력 값입니다.")
            Long missionId,
            @NotNull(message = "상태는 필수 입력 값입니다.")
            MissionState state
    ){}
}
