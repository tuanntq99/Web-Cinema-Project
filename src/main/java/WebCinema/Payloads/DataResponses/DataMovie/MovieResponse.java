package WebCinema.Payloads.DataResponses.DataMovie;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieResponse {
    int id;
    int movieDuration;
    Date endTime;
    Date premiereDate;
    String description;
    String director;
    String image;
    String heroImage;
    String language;
    String name;
    String trailer;
    boolean isActive;
}
