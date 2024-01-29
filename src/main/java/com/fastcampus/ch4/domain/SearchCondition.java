package com.fastcampus.ch4.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
    private Integer page = 1; // 현재 페이지 번호, 기본 생성자에 의해 초기화 될때 값을 지정
    private Integer pageSize = 10; // 페이지 당 게시물 수
//    private Integer offset = 0;
    private String keyword = "";
    private String option = "";

    public SearchCondition(){}
    public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
    }

    public String getQueryString(Integer page){
        // ?page=1&pageSize=10&option=T&keyword="title
        return UriComponentsBuilder.newInstance() // UriComponentsBuilder를 이용해서 동적으로 쿼리 문자열 생성
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .build().toString(); //  build() 메서드는 조립한 URI 컴포넌트를 최종적으로 UriComponents 객체로 반환
    }

    public String getQueryString(){
        return getQueryString(page);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {return (page-1)*pageSize;}


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offset=" + getOffset() +
                ", keyword='" + keyword + '\'' +
                ", option='" + option + '\'' +
                '}';
    }
}
