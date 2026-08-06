package io.github.elmergj.movish.api.interfaces.rest.catalog;

import io.github.elmergj.movish.api.application.catalog.search.SearchMediaQuery;
import io.github.elmergj.movish.api.application.catalog.MediaCatalogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class CatalogController {

    private final MediaCatalogService mediaCatalogService;

    @GetMapping()
    public ResponseEntity<MediaSearchResponse> searchMediaByQuery(@Valid MediaSearchQuery request) {
        var command = new SearchMediaQuery(request.query(), request.page(), request.size());

        var results = mediaCatalogService.searchMediaByQuery(command);

//        var response = new MediaSearchResponse(results);

        return ResponseEntity.ok(null);
    }
}
