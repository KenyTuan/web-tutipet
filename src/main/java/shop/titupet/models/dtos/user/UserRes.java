package shop.titupet.models.dtos.user;

public record UserRes(
        Long id,
        String email,
        boolean gender,
        String img) {
}
