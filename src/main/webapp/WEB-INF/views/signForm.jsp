<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId==null ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId==null ? 'Login' : 'Logout'}"/>
<c:set var="sign" value="${loginId==null ? '' : 'display:none;'}"/>
<c:set var="mypage" value="${loginId==null ? 'display:none;' : ''}"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/loginForm.css'/>">
    <title>signForm</title>
</head>
<body>
    <nav id="menu">
        <h1 hidden="hidden">메뉴바</h1>
        <ul>
            <li id="logo">CAU community</li>
            <li><a href="<c:url value='/'/>">Home</a></li>
            <li><a href="<c:url value='/board/list'/>">Board</a></li>
            <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
            <li style="${sign}"><a href="<c:url value='/sign/add'/>">Sign up</a></li>
            <li style="${mypage}"><a href="<c:url value="/login/modify"/>">MyPage</a></li>
<%--            <li><a href=""><i class="fa fa-search"></i></a></li>--%>
        </ul>
    </nav>

    <div>
        <h1 hidden="hidden">회원가입 폼</h1>
        <form action="<c:url value='/sign/add'/>" method="post" onsubmit="return formCheck(this);">
            <h2>SignUp</h2>
            <div id="msg">
                <c:if test="${not empty param.msg}">
                    <i>${param.msg}</i>
                </c:if>
            </div>
            <input type="text" name="id" placeholder="아이디"><br> <!-- name 속성은 서버로 데이터를 전송할 때, 그 데이터를 식별하는 데 사용 -->
            <input type="text" name="pwd" placeholder="비밀번호 6자리 이상"><br>
            <input type="text" name="name" placeholder="이름"><br>
            <input type="text" name="phoneNumber" placeholder="휴대전화번호"><br>
            <input type="text" name="email" placeholder="이메일"><br>
            <label><input type="checkbox" name="isAdmin" value="false">관리자</label> <!-- label은 폼 요소에 이름을 붙이는것이다. -->
            <button>회원가입</button>
        </form>
    </div>

    <script>
        function formCheck(frm) {
            if (frm.id.value.length < 3) {
                setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
                return false;
            }
            return true;
        }
        function setMessage(msg, element){
            document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;

            if(element) {
                element.select();
            }
        }

    </script>

</body>
</html>
