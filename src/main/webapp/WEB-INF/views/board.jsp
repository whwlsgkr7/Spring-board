<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!-- sessionScope는 JSP에서 사용하는 EL의 일부로 HTTP 세션 스코프에 저장된 속성에 접근하는 데 사용된다. sessionScope를 사용하여
세션에 저장된 데이터에 간편하게 접근하고, 그 값을 JSP 페이지에 표시하거나 조작할 수 있다. JSP에서 세션 스코프는 사용자가 웹 애플리케이션과 상호작용하는
동안 지속되는 데이터를 저장하는 공간이다. 예를 들어, 사용자가 로그인할 때 사용자의 ID나 이름과 같은 정보를 세션에 저장하고, 이후의 요청에서 이 정보를
재사용할 수 있다. -->
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId==null ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId==null ? 'Login' : 'Logout'}"/>
<c:set var="sign" value="${loginId==null ? '' : 'display:none;'}"/>
<c:set var="mypage" value="${loginId==null ? 'display:none;' : ''}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/boardmain.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>

</head>

<body>
    <nav id="menu">
        <h1 hidden>메뉴</h1>
        <ul>
            <li id="logo">CAU community</li>
            <li><a href="<c:url value='/'/>">Home</a></li>
            <li><a href="<c:url value='/board/list'/>">Board</a></li>
            <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
            <li style="${sign}"><a href="<c:url value='/sign/add'/>">Sign up</a></li>
            <li style="${mypage}"><a href="<c:url value="/login/modify"/>">MyPage</a></li>
    <%--        <li><a href=""><i class="fa fa-search"></i></a></li>--%>
        </ul>
    </nav>


    <div class="container">
        <h1 class="writing-header">게시판 ${mode=="new" ? "글쓰기" : "읽기"}</h1>
        <form id="form" class="frm" action="" method="post">
            <input type="hidden" name="bno" value="${boardDto.bno}">

            <input name="title" type="text" value="${boardDto.title}" placeholder="  제목을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}><br>
            <textarea name="content" rows="20" placeholder=" 내용을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}>${boardDto.content}</textarea><br>

            <c:if test="${mode eq 'new'}">
                <button type="button" id="writeBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 등록</button>
            </c:if>
            <c:if test="${mode ne 'new'}">
                <button type="button" id="writeNewBtn" class="btn btn-write"><i class="fa fa-pencil"></i> 글쓰기</button>
            </c:if>
            <c:if test="${boardDto.writer eq loginId}">
                <button type="button" id="modifyBtn" class="btn btn-modify"><i class="fa fa-edit"></i> 수정</button>
                <button type="button" id="removeBtn" class="btn btn-remove"><i class="fa fa-trash"></i> 삭제</button>
            </c:if>
            <button type="button" id="listBtn" class="btn btn-list"><i class="fa fa-bars"></i> 목록</button>
            <button type="button" id="replyBtn" class="btn btn-reply"><i class="fa fa-bars"></i> 댓글보기</button>

        </form>
    </div>



    <script>
        let msg = "${msg}";
        if(msg=="WRT_ERR") alert("게시물 등록에 실패하였습니다. 다시 시도해 주세요.");
        if(msg=="MOD_ERR") alert("게시물 수정에 실패하였습니다. 다시 시도해 주세요.");
    </script>

    <script>
        $(document).ready(function(){ // window.onload랑 비슷
            let formCheck = function() {
                let form = document.getElementById("form");
                if(form.title.value=="") {
                    alert("제목을 입력해 주세요.");
                    form.title.focus();
                    return false;
                }

                if(form.content.value=="") {
                    alert("내용을 입력해 주세요.");
                    form.content.focus();
                    return false;
                }
                return true;
            }
            // JQuery(writeNewBtn) == $(writeNewBtn) $기호는 jQuery를 의미
            // var writeNewBtn = document.querySelector("#writeNewBtn");
            // $(writeNewBtn)를 줄여서 $("#writeNewBtn") 이렇게 쓴다.
            $("#writeNewBtn").on("click", function(){ // writeNewBtn.onclick = function(){location.href="";}
                location.href="<c:url value='/board/write'/>";
            });

            $("#writeBtn").on("click", function(){
                let form = $("#form");
                form.attr("action", "<c:url value='/board/write'/>");
                form.attr("method", "post");

                if(formCheck())
                    form.submit();
            });

            $("#modifyBtn").on("click", function(){
                let form = $("#form");
                let isReadonly = $("input[name=title]").attr('readonly');

                // 1. 읽기 상태이면, 수정 상태로 변경
                if(isReadonly=='readonly') {
                    $(".writing-header").html("게시판 수정");
                    $("input[name=title]").attr('readonly', false);
                    $("textarea").attr('readonly', false);
                    $("#modifyBtn").html("<i class='fa fa-pencil'></i> 등록");
                    return;
                }

                // 2. 수정 상태이면, 수정된 내용을 서버로 전송
                form.attr("action", "<c:url value='/board/modify${searchCondition.queryString}'/>");
                form.attr("method", "post");
                if(formCheck())
                    form.submit();
            });

            $("#removeBtn").on("click", function(){
                if(!confirm("정말로 삭제하시겠습니까?")) return;

                let form = $("#form");
                form.attr("action", "<c:url value='/board/remove${searchCondition.queryString}'/>");
                form.attr("method", "post");
                form.submit();
            });

            $("#listBtn").on("click", function(){
                location.href="<c:url value='/board/list${searchCondition.queryString}'/>";
            });

            $("#replyBtn").on("click", function(){
                location.href="<c:url value='/board/commentList?bno=${param.bno}'/>"
            })
        });
    </script>
</body>
</html>