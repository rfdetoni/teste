package exceptions;

import lombok.Getter;

@Getter
public enum Errors {
    INCIDENT_NOT_FOUND("Incident not found"),
    NOT_FOUND("Not found"),
    UNAUTHORIZED("Unauthorized"),
    FORBIDDEN("Forbidden"),
    BAD_REQUEST("Bad request"),
    CONFLICT("Conflict"),
    INTERNAL_SERVER_ERROR("Internal server error");

    private final String message;

    Errors(String message) {
        this.message = message;    }
}
