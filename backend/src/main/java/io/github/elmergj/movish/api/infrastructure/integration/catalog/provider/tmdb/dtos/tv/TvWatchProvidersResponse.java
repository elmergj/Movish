package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv;

/*
  DTO representing the streaming, rental, and purchase availability for a title.
  * <p><b>Implementation consideration: </b> Internally, this endpoint's data is provided via
  a partnership with <b>JustWatch</b>. TMDB aggregates this information and
  requires downstream consumers to respect JustWatch's attribution terms.</p>
  * <p><b>Legal Compliance:</b> To maintain API access, any UI displaying this
  data must credit JustWatch as the source. This class serves as a reminder
  that these specific fields are subject to external branding requirements
  enforced by the provider.</p>
  * @see <a href="https://developer.themoviedb.org/reference/tv-series-watch-providers">TMDB Documentation</a>
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;


// DTO for streaming, purchase, and rental providers by country.
public record TvWatchProvidersResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("results") Map<String, CountryWatchProviders> results
) {

    // Container of available options in a specific country.
    public record CountryWatchProviders(
            @JsonProperty("link") String link,
            @JsonProperty("flatrate") List<ProviderDetail> flatrate,
            @JsonProperty("buy") List<ProviderDetail> buy,
            @JsonProperty("rent") List<ProviderDetail> rent
    ) {}

        //Provider technical detail(e.g. HBO Max, Netflix)
        public record ProviderDetail(
            @JsonProperty("logo_path") String logoPath,
            @JsonProperty("provider_id") Integer providerId,
            @JsonProperty("provider_name") String providerName,
            @JsonProperty("display_priority") Integer displayPriority
    ) {}
}