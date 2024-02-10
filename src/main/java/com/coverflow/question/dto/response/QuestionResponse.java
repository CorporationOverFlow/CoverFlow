package com.coverflow.question.dto.response;

import com.coverflow.question.domain.Question;

import java.util.UUID;

public record QuestionResponse(
        Long id,
        String title,
        String content,
        int count,
        Long companyId,
        UUID memberId
) {

    public static QuestionResponse from(final Question question) {
        return new QuestionResponse(
                question.getId(),
                question.getTitle(),
                question.getContent(),
                question.getCount(),
                question.getCompany().getId(),
                question.getMember().getId()
        );
    }
}