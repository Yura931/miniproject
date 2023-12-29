package sideproject.authservice.auth.dto.response;

public record ReIssueAccessTokenResponse (
        String token
) {
    public static ReIssueAccessTokenResponse from(String token) {
        return new ReIssueAccessTokenResponse(token);
    }
}
