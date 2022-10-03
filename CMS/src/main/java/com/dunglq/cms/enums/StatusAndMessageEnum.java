package com.dunglq.cms.enums;

import lombok.Getter;

/**
 * The enum Status code and message enum.
 *
 * @author dunglq
 */
@Getter
public enum StatusAndMessageEnum {

    /**
     * Success status code and message enum.
     */
    SUCCESS(200, "Success"),
    /**
     * Created status code and message enum.
     */
    CREATED(201, "Created"),
    /**
     * Found status code and message enum.
     */
    FOUND(302, "Found"),
    /**
     * The Bad request.
     */
    BAD_REQUEST(400, "Bad request"),
    /**
     * Unauthorized status code and message enum.
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * The Not found.
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * The Method not allow.
     */
    METHOD_NOT_ALLOW(405, "Method not allow"),
    /**
     * The Time out.
     */
    TIME_OUT(408, "Time out"),
    /**
     * The System error.
     */
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    /**
     * The Service unavailable.
     */
    SERVICE_UNAVAILABLE(503, "Service unavailable");
    private final Integer statusCode;
    private final String message;

    StatusAndMessageEnum(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * To status code and message enum status code and message enum.
     *
     * @param statusCode the status code
     * @return the status code and message enum
     */
    public static StatusAndMessageEnum toStatusCodeAndMessageEnum(Integer statusCode) {
        for (StatusAndMessageEnum e : StatusAndMessageEnum.values()) {
            if (e.getStatusCode().equals(statusCode)) {
                return e;
            }
        }
        return null;
    }

    /**
     * To status code and message enum status code and message enum.
     *
     * @param message the message
     * @return the status code and message enum
     */
    public static StatusAndMessageEnum toStatusCodeAndMessageEnum(String message) {
        for (StatusAndMessageEnum e : StatusAndMessageEnum.values()) {
            if (e.getMessage().equalsIgnoreCase(message)) {
                return e;
            }
        }
        return null;
    }
}
