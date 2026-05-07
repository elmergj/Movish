package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider;

import org.springframework.context.annotation.Profile;

@Profile("dev") //Test: test only
public class FakeTitleDetails {

/*    public static Title getFakeTitleDetailsMovie() {
        return new Title(
                TitleId.from("597"),
                new Movie(120),
                "Titanic",
                LocalDate.of(1997, 12, 18),
                MediaType.MOVIE,
                "tt0120338",
                TitleRating.of(8.0),
                TitleRating.of(7.903),
                new TitleGenre[]{TitleGenre.ACTION, TitleGenre.ROMANCE}
        );
    }*/

/*    public static Title getFakeTitleDetailsTV(){
        return new Title(
                TitleId.from("37680"),
                new TvShow(
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                "Suits",
                LocalDate.of(2011, 6, 23),
                MediaType.TV_SHOW,
                "tt1632701",
                TitleRating.of(8.4),
                TitleRating.of(8.219),
                new TitleGenre[]{TitleGenre.ACTION}
        );
    }*/
}
