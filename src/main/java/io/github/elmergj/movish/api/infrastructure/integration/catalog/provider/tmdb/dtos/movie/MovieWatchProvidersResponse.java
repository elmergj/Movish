package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * DTO for the streaming providers by country endpoint.
 * TECHNICAL CRITERION: A Map is used for the 'results' property because the keys
 * are dynamic (2-letter country codes).
 */
public record MovieWatchProvidersResponse(

        Long id,

        // The map where the key is the country code (e.g., "AR", "AE")
        Map<String, CountryWatchProvidersDto> results
) {

    /**
     * Represents the available viewing options in a specific country.
     * They are grouped by access type: subscription, rental, or purchase.
     */
    public record CountryWatchProvidersDto(

            // Direct link to the TMDB page with viewing details
            String link,

            // Services included in monthly subscriptions (Netflix, Prime, etc.)
            List<ProviderDetailDto> flatrate,

            // Services where the movie can be rented individually
            List<ProviderDetailDto> rent,

            // Services where the movie can be purchased digitally
            List<ProviderDetailDto> buy
    ) {}

    /**
     * Technical details of the service provider.
     * DESIGN CRITERION: This object is highly reusable.
     * 'displayPriority' is included in case the frontend needs to sort
     * logos according to their relevance in that market.
     */
    public record ProviderDetailDto(

            @JsonProperty("logo_path")
            String logoPath,

            @JsonProperty("provider_id")
            Integer providerId,

            @JsonProperty("provider_name")
            String providerName,

            @JsonProperty("display_priority")
            Integer displayPriority
    ) {}
}