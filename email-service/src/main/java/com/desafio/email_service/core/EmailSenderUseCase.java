package com.desafio.email_service.core;

public interface EmailSenderUseCase {

    void sendoEmail(String to, String subject, String body);
}
