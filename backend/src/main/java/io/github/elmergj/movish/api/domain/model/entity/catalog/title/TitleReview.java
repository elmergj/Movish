package io.github.elmergj.movish.api.domain.model.entity.catalog.title;

public class TitleReview {
    String value;

    private TitleReview(String value) {
        this.value = value;
    }

    public static TitleReview from(String review) {
        return new TitleReview(review);
    }

    public String value(){
        return value;
    }
}
