package io.github.elmergj.movish.api.application.library;

import io.github.elmergj.movish.api.domain.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultTitleInternalIdentityResolver implements TitleInternalIdentityResolver {

    public final TitleRepository titleRepository;
//
//    @Override
//    public MediaId resolveByExternalIds(Collection<MediaExternalId> mediaExternalIds) {
//        return null; //Todo: complete process
//    }
}
