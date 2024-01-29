<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%> <!-- HTTP 세션을 사용하지 않도록 설정, 디폴트는 true임 -->
<!-- pageContext는 JSP의 내장 객체 중 하나로 다른 내장 객체를 얻어내거나, 현재 페이지의 요청과 제어권을 다른 페이지로 넘겨주는데 사용한다. 예를 들어,
네 가지 스코프(pageContext, request, session, application)에 저장된 속성을 제어할 수 있다. -->
<!-- pageContext.request.getSession(true): 이 형태는 현재 요청과 연결된 세션을 반환한다. 만약 세션이 존재하지 않는다면 새로운 세션을 생성한다.
하지만 pageContext.request.getSession(false): 이 형태는 현재 요청과 연결된 세션이 존재하는 경우에만 해당 세션을 반환한다. 세션이 존재하지 않으면
새 세션을 생성하지 않고 null을 반환한다. -->
<%--<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>--%>
<%--<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>--%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="sign" value="${loginId==null ? '' : 'display:none;'}"/>
<c:set var="mypage" value="${loginId==null ? 'display:none;' : ''}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/loginForm.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>

</head>
<body>
    <nav id="menu">
        <ul>
            <li id="logo">CAU community</li>
            <li><a href="<c:url value='/'/>">Home</a></li>
            <li><a href="<c:url value='/board/list'/>">Board</a></li>
            <li><a href="<c:url value='/login/login'/>">Login</a></li>
            <li style="${sign}"><a href="<c:url value='/sign/add'/>">Sign up</a></li>
            <li style="${mypage}"><a href="<c:url value="/login/modify"/>">MyPage</a></li>
<%--            <li><a href=""><i class="fa fa-search"></i></a></li>--%>
        </ul>
    </nav>
    <section>
        <!-- onsubmit은 폼이 제출될 때 발생할 이벤트를 지정, 여기서는 formCheck 함수를 호출 -->
        <!-- 'this'는 <form> 태그를 가리키며, 폼 제출(submit) 이벤트가 발생했을 때 그 폼 요소 자체를 formCheck 함수에 인자로 넘겨준다.
        이렇게 formCheck 함수로 전달되면, 함수 내에서 frm이라는 매개변수로 해당 폼 요소를 사용할 수 있게 된다.
        예를 들어 frm.id.value는 사용자가 입력한 아이디를, frm.pwd.value는 입력한 비밀번호를 참조할 수 있다. -->
        <form action="<c:url value='/login/login'/>" method="post" onsubmit="return formCheck(this);">
            <h1 id="title">Login</h1>
            <div id="msg">
                <!-- param은 JSP에서 사용되는 객체로, HTTP 요청과 함께 전송된 파라미터(쿼리스트링의 name=value들)들에 접근할 수 있다. -->
                <c:if test="${not empty param.msg}">
                    <i class="fa fa-exclamation-circle"> ${param.msg}</i> <!-- ${URLDecoder.decode(param.msg)} -->
                </c:if>
            </div>
            <!-- id는 쿠키의 이름이고, value는 그 쿠키에 저장된 실제 값 -->
            <input type="text" name="id" value="${cookie.id.value}" placeholder="이메일 입력" autofocus>
            <input type="password" name="pwd" placeholder="비밀번호">
            <!-- input 필드를 사용해 사용자가 로그인 폼을 제출할 때 toURL을 함께 제출 -->
            <input type="hidden" name="toURL" value="${param.toURL}">
            <button>로그인</button> <!-- <button> 태그의 type 속성이 명시되지 않으면 "submit"으로 간주된다. -->
            <div>
                <label><input type="checkbox" name="rememberId" value="on" ${empty cookie.id.value ? "":"checked"}> 아이디 기억</label> |
                <a href="">비밀번호 찾기</a> |
                <a href="<c:url value='/sign/add'/>">회원가입</a>
            </div>
        </form>
    </section>

    <script>
        function formCheck(frm) {
            let msg ='';
            if(frm.id.value.length==0) {
                setMessage('id를 입력해주세요.', frm.id);
                return false;
            }
            if(frm.pwd.value.length==0) {
                setMessage('password를 입력해주세요.', frm.pwd);
                return false;
            }
            return true;
        }
        function setMessage(msg, element){
            document.getElementById("msg").innerHTML = msg;            <%-- ` ${'${msg}'}'; --%>
            if(element) {
                element.select();
            }
        }
    </script>
</form>
</body>
</html>