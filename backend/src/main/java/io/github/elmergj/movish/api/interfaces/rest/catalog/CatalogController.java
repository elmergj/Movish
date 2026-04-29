package io.github.elmergj.movish.api.interfaces.rest.catalog;

import io.github.elmergj.movish.api.application.catalog.command.SearchTitleCommand;
import io.github.elmergj.movish.api.application.catalog.TitleCatalogService;
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

    private final TitleCatalogService titleCatalogService;

    @GetMapping()
    public ResponseEntity<TitleSearchResponse> getTitleSearchResults(@Valid TitleSearchRequest request) {
        var command = new SearchTitleCommand(request.query(), request.page(), request.size());

        var results = titleCatalogService.searchTitleByQuery(command);

        var response = new TitleSearchResponse(results);

        return ResponseEntity.ok(response);
    }
}
