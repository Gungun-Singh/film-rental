package com.filmrental.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class CreateFilmRequest {
    @NotBlank(message = "Title is required") private String title;
    private String description;
    private Integer releaseYear;
    @NotNull(message = "Language ID is required") private Integer languageId;
    @NotNull(message = "Rental duration is required") @Positive private Integer rentalDuration;
    @NotNull(message = "Rental rate is required") @Positive private BigDecimal rentalRate;
    private Integer length;
    @NotNull(message = "Replacement cost is required") private BigDecimal replacementCost;
    private String rating;
}
