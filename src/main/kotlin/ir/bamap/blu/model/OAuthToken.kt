package ir.bamap.blu.model

import com.fasterxml.jackson.annotation.JsonAlias

class OAuthToken(
    @JsonAlias(value = ["accessToken", "access_token"])
    val accessToken: String = "",

    @JsonAlias(value = ["refreshToken", "refresh_token"])
    val refreshToken: String = "",

    val scope: String = "",

    @JsonAlias(value = ["tokenType", "token_type"])
    val tokenType: String = "",

    @JsonAlias(value = ["expiresIn", "expires_in"])
    val expiresIn: Long = 0,

    @JsonAlias(value = ["refreshExpiresIn", "refresh_expires_in"])
    val refreshExpiresIn: Long = 0
) {
}