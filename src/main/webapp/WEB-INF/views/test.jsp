<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>comment</title>--%>
<%--    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>--%>
<%--</head>--%>

<%--<body>--%>
<%--    <div>${param.bno}번 게시판 댓글</div>--%>
<%--    <div>--%>
<%--        <label>comment: <input type="text" name="comment"></label><br>--%>
<%--        <button id="sendBtn" type="button">SEND</button>--%>
<%--        <button id="modBtn" type="button">수정</button>--%>
<%--    </div>--%>

<%--    <div id="commentList"></div>--%>

<%--    <div id="replyForm" style="display: none"> <!-- 대댓글 입력창 -->--%>
<%--        <input type="text" name="replyComment">--%>
<%--        <button id="wrtReBtn" type="button">등록</button>--%>
<%--    </div>--%>




<%--    <script>--%>
<%--        let bno = 1085;--%>

<%--        $(document).ready(function(){--%>
<%--            showList(bno);--%>

<%--            $("#modBtn").click(function(){--%>
<%--                let cno = $(this).attr("data-cno");--%>
<%--                let comment = $("input[name=comment]").val() // name이 comment인 input요소의 value를 가져온다.--%>

<%--                if(comment.trim()==''){ // 댓글을 공백으로 제출하려는 경우 실행--%>
<%--                    alert("댓글을 입력해주세요.");--%>
<%--                    $("input[name=comment]").focus()--%>
<%--                    return--%>
<%--                }--%>

<%--                $.ajax({--%>
<%--                    type:'PATCH',       // 요청 메서드--%>
<%--                    url: '/ch4/comments/' + cno,  // 요청 URI--%>
<%--                    headers : { "content-type": "application/json"}, // 요청 헤더--%>
<%--                    data : JSON.stringify({cno:cno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.--%>
<%--                    success : function(result){--%>
<%--                        alert(result);--%>
<%--                        showList(bno);--%>
<%--                    },--%>
<%--                    error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수--%>
<%--                });--%>
<%--            });--%>

<%--            $("#sendBtn").click(function(){--%>
<%--                let comment = $("input[name=comment]").val() // name이 comment인 input요소의 value를 가져온다.--%>

<%--                if(comment.trim()==''){ // 댓글을 공백으로 제출하려는 경우 실행--%>
<%--                    alert("댓글을 입력해주세요.");--%>
<%--                    $("input[name=comment]").focus()--%>
<%--                    return--%>
<%--                }--%>

<%--                $.ajax({--%>
<%--                    type:'POST',       // 요청 메서드--%>
<%--                    url: '/ch4/comments?bno='+bno,  // 요청 URI--%>
<%--                    headers : { "content-type": "application/json"}, // 요청 헤더--%>
<%--                    data : JSON.stringify({bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.--%>
<%--                    success : function(result){--%>
<%--                        alert(result);--%>
<%--                        showList(bno);--%>
<%--                    },--%>
<%--                    error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수--%>
<%--                });--%>
<%--            });--%>

<%--            $("#wrtReBtn").click(function(){--%>
<%--                let comment = $("input[name=replyComment]").val() // name이 comment인 input요소의 value를 가져온다.--%>
<%--                let pcno = $("#replyForm").parent().attr("data-pcno");--%>

<%--                if(comment.trim()==''){ // 댓글을 공백으로 제출하려는 경우 실행--%>
<%--                    alert("댓글을 입력해주세요.");--%>
<%--                    $("input[name=replyComment]").focus()--%>
<%--                    return--%>
<%--                }--%>

<%--                $.ajax({--%>
<%--                    type:'POST',       // 요청 메서드--%>
<%--                    url: '/ch4/comments?bno='+bno,  // 요청 URI--%>
<%--                    headers : { "content-type": "application/json"}, // 요청 헤더--%>
<%--                    data : JSON.stringify({pcno:pcno, bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.--%>
<%--                    success : function(result){--%>
<%--                        alert(result);--%>
<%--                        showList(bno);--%>
<%--                    },--%>
<%--                    error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수--%>
<%--                });--%>

<%--                $("#replyForm").css("display", "none") // 대댓글 작성후 입력폼 다시 안보이게--%>
<%--                $("input[name=replyComment]").val('') // name이 replyComment인 input요소의 value를 빈문자열로--%>
<%--                $("#replyForm").appendTo("body");--%>
<%--            });--%>

<%--            $("#commentList").on("click", ".modBtn" ,function() { // #commentList 안에 있는 .modBtn으로 이벤트 위임(동적으로 생성되는 요소에 이벤트를 거는 방법)--%>
<%--                let cno = $(this).parent().attr("data-cno");--%>
<%--                let comment = $("span.comment", $(this).parent()).text();--%>

<%--                // 1. comment의 내용을 input에 뿌려주기--%>
<%--                $("input[name=comment]").val(comment);--%>
<%--                // 2. cno 전달하기--%>
<%--                $("#modBtn").attr("data-cno", cno);--%>
<%--            });--%>


<%--            $("#commentList").on("click", ".delBtn" ,function(){ // #commentList 안에 있는 .delBtn으로 이벤트 위임(동적으로 생성되는 요소에 이벤트를 거는 방법)--%>
<%--                let cno = $(this).parent().attr("data-cno"); // data-cno를 못찾음 왜이러지?--%>
<%--                // let cno = $(this).closest('li').attr("data-cno");--%>

<%--                // let bno = $(this).parent().attr("data-bno");--%>
<%--                $.ajax({--%>
<%--                    type:'DELETE',       // 요청 메서드--%>
<%--                    url: '/ch4/comments/' + cno + '?bno='+bno,  // 요청 URI--%>
<%--                    success : function(result){ // 서버로 부터 응답을 성공적으로 받았을때 호출될 함수. result로 받음--%>
<%--                        alert(result)--%>
<%--                        showList(bno);--%>
<%--                    },--%>
<%--                    error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수--%>
<%--                });--%>
<%--            });--%>

<%--            $("#commentList").on("click", ".replyBtn" ,function() { // #commentList 안에 있는 .modBtn으로 이벤트 위임(동적으로 생성되는 요소에 이벤트를 거는 방법)--%>
<%--                //1. replyForm을 옮기고--%>
<%--                $("#replyForm").appendTo($(this).parent())--%>
<%--                //2. 답글을 입력할 폼을 보여주고,--%>
<%--                $("#replyForm").css("display", "block") // block속성은 div처럼 새로운 줄에서 사용가능한 전체 너비를 차지한다.--%>
<%--            });--%>

<%--        });--%>

<%--        let showList = function(bno){--%>
<%--            $.ajax({--%>
<%--                type:'GET',       // 요청 메서드--%>
<%--                url: '/ch4/comments?bno='+bno,  // 요청 URI--%>
<%--                success : function(result){ // 서버로 부터 응답을 성공적으로 받았을때 호출될 함수. result로 받음--%>
<%--                    $("#commentList").html(toHTML(result)); // 응답받는 형식이 JSON이기 때문에 HTML로 출력하기위해 형변환을 해준다.--%>
<%--                },--%>
<%--                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수--%>
<%--            });--%>
<%--        }--%>

<%--        let toHTML = function (comments){--%>
<%--            let tmp = "<ul>";--%>

<%--            comments.forEach(function (comment){ // comments의 요소들이 function의 매개변수로 하나씩 들어가는거임--%>
<%--                tmp += '<li data-cno=' + comment.cno--%>
<%--                tmp += ' data-pcno=' + comment.pcno--%>
<%--                tmp += ' data-bno=' + comment.bno + '>'--%>
<%--                if(comment.cno!=comment.pcno)--%>
<%--                    tmp += 'ㄴ'--%>
<%--                tmp += ' commenter=<span class="commenter">' + comment.commenter + '</span>'--%>
<%--                tmp += ' comment=<span class="comment">' + comment.comment + '</span>'--%>
<%--                tmp += ' up-date='+comment.up_date--%>
<%--                tmp += '<button class="delBtn">삭제</button>'--%>
<%--                tmp += '<button class="modBtn">수정</button>'--%>
<%--                tmp += '<button class="replyBtn">답글</button>'--%>

<%--                tmp += '</li>'--%>
<%--            })--%>
<%--            return tmp + "</ul>";--%>
<%--        }--%>


<%--    </script>--%>
<%--</body>--%>
<%--</html>--%>