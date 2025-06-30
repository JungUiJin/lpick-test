package com.notfound.lpickbackend.wiki.command.application.domain;

import com.notfound.lpickbackend.userinfo.command.application.domain.UserInfo;
import com.notfound.lpickbackend.wiki.command.application.dto.request.ReviewPostRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @Column(name = "review_id", nullable = false, length = 40)
    private String reviewId;

    @Column(name = "star", nullable = false)
    private Float star;

    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @Column(name="created_at", nullable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "oauth_id", nullable = false)
    private UserInfo oauth;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wiki_id", nullable = false)
    private WikiPage wiki;

    public void updateReview(ReviewPostRequest req) {
        this.star = req.getStarScore();
        this.content = req.getContent();
        this.createdAt = Instant.now();
    }

}