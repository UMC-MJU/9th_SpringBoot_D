package com.naho.umc9th.domain.mission.dto;

public class MemberMissionReqDTO {

    public record CreateDTO(
            Long memberId,
            Long missionId
    ){}
}
