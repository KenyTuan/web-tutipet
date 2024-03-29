package shop.titupet.dtos.auth;


public record AuthRes(
        String token,
        long expTime

) {
}
