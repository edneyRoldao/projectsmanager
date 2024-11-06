package com.edney.projectsmanager.dtos;

public record MemberUpdateRequest(Long id, Boolean employee, String assignment) {
}
