package com.notfound.lpickbackend.wiki.query.controller;

import com.notfound.lpickbackend.wiki.query.dto.response.ReviewResponse;
import com.notfound.lpickbackend.wiki.query.service.WikiReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "위키 리뷰 컨트롤러", description = "위키 리뷰 조회 관련 컨트롤러")
public class WikiReviewQueryController {

    private final WikiReviewQueryService wikiReviewQueryService;

    @GetMapping("/wiki/{wikiId}/review")
    @Operation(summary = "위키 리뷰 조회", description = "특정 위키의 리뷰 리스트 조회 기능")
    public ResponseEntity<Page<ReviewResponse>> getReviewListInWiki(
            @PathVariable("wikiId") String wikiId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @Pattern(
                    regexp = "createdAt,(asc|desc)|star,(asc|desc)",
                    message = "sort 기본값은 createdAt,desc(최신순)입니다. sort는 'createdAt,asc', 'createdAt,desc', 'star,asc', 'star,desc' 중 하나거나 존재하지 않아야 합니다."
            )
            @RequestParam(required = false) String sortParam
    ) {

        Sort sort;

        if(sortParam == null) {
            sort = Sort.by("createdAt").descending();
        }
        else {
            String[] sortParamArr = sortParam.split(",");

            if("asc".equals(sortParamArr[1])) sort = Sort.by(sortParamArr).ascending();
            else sort = Sort.by(sortParamArr).descending();
        }

        Page<ReviewResponse> reviewList = wikiReviewQueryService.getReviewResponseListInWiki(PageRequest.of(page, size, sort), wikiId);

        return ResponseEntity.status(HttpStatus.OK).body(reviewList);
    }



}
