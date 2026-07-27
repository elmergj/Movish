package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider;

import org.springframework.context.annotation.Profile;

@Profile("dev") //Test: test only
public class FakeTitleDetails {

/*    public static Media getFakeTitleDetailsMovie() {
        return new Media(
                MediaId.from("597"),
                new Movie(120),
                "Titanic",
                LocalDate.of(1997, 12, 18),
                MediaType.MOVIE,
                "tt0120338",
                MediaAverageRating.of(8.0),
                MediaAverageRating.of(7.903),
                new MediaGenre[]{MediaGenre.ACTION, MediaGenre.ROMANCE}
        );
    }*/

/*    public static Media getFakeTitleDetailsTV(){
        return new Media(
                MediaId.from("37680"),
                new TvShow(
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                "Suits",
                LocalDate.of(2011, 6, 23),
                MediaType.TV_SHOW,
                "tt1632701",
                MediaAverageRating.of(8.4),
                MediaAverageRating.of(8.219),
                new MediaGenre[]{MediaGenre.ACTION}
        );
    }*/
}
