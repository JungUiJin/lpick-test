package com.notfound.lpickbackend.wiki.query.repository;

import com.notfound.lpickbackend.wiki.command.application.domain.WikiBookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WikiBookmarkQueryRepository extends JpaRepository<WikiBookmark, String> {

    boolean existsByWiki_WikiIdAndOauth_oauthId(String wikiId, String oauthId);

    boolean existsById(String bookmarkId);

    Optional<WikiBookmark> findByWiki_wikiIdAndOauth_oauthId(String wikiId, String oauthId);

    @EntityGraph(attributePaths = {"wiki"})
    Page<WikiBookmark> findAllByOauth_OauthId(String oauthId, Pageable pageable);
}
