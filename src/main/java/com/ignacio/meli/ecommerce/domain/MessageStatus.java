package com.ignacio.meli.ecommerce.domain;

public enum MessageStatus {

    RECEIVED,        // Mensaje recién llegado desde ML
    AUTO_REPLIED,    // Respondido automáticamente por IA
    DERIVED_WHATSAPP,// Derivado a WhatsApp
    PENDING_REVIEW   // Requiere revisión humana
}