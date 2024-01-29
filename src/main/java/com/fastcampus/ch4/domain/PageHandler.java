package com.fastcampus.ch4.domain;

public class PageHandler {



    //    private int page;
//    private int pageSize;
//    private String option;
//    private String keyword;
    private SearchCondition sc;
    private int totalCnt; // 총 게시물 개수, sql문으로 받아 올 수 있음
    private int totalPage; // 총 페이지 개수

    private int naviSize = 10;
    private int beginPage; // 현재 네비게이션에서 첫번째 페이지 번호
    private int endPage; // 현재 네이게이션에서 마지막 페이지 번호
    private boolean showPrev;
    private boolean showNext;

    public PageHandler(int totalCnt, SearchCondition sc){
        this.totalCnt = totalCnt;
        this.sc = sc;
        doPaging(totalCnt, sc);
    }


    public void doPaging(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;

        totalPage = (int)Math.ceil(totalCnt/(double)sc.getPageSize());
        beginPage = (sc.getPage()-1)/naviSize*naviSize + 1;
        endPage = Math.min(beginPage + naviSize -1 , totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }

    void print(){
        System.out.print("page = " + sc.getPage());
        System.out.print(showPrev ? "[prev]" : "");
        for(int i=beginPage; i<=endPage; i++){
            System.out.println(i+" ");
        }
        System.out.println(showNext ? "[next]" : "");
    }


    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }


    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", naviSize=" + naviSize +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
