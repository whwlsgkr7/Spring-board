<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginout" value="${loginId==null ? 'Login' : 'Logout'}"/>
<c:set var="loginoutlink" value="${loginId==null ? '/login/login' : 'login/logout'}"/>
<c:set var="sign" value="${loginId==null ? '' : 'display:none;'}"/>
<c:set var="mypage" value="${loginId==null ? 'display:none;' : ''}"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/> ">
    <link rel="stylesheet" href="<c:url value='/css/loginForm.css'/> ">
    <title>mypage</title>
</head>
<body>
    <nav>
        <h1 hidden>메뉴바</h1>
        <ul>
            <li>CAU community</li>
            <li><a href="<c:url value='/'/> ">Home</a></li>
            <li><a href="<c:url value='/board/list'/> ">Board</a></li>
            <li><a href="<c:url value='${loginoutlink}'/>">${loginout}</a></li>
            <li style="${sign}"><a href="<c:url value='/sign/add'/> ">sign up</a></li>
            <li style="${mypage}"><a href="<c:url value='/login/modify'/> ">MyPage</a></li>
        </ul>
    </nav>

    <section>
        <h1 hidden>회원정보수정 폼</h1>
        <form action="<c:url value='/login/modify'/>" method="post" onsubmit="return formCheck(this)">
            <h2>Edit Information</h2>
            <div id="msg"></div>
            <label>아이디: <input type="text" name="id" value="${userDto.id}" readonly="readonly"></label>
            <label>비밀번호: <input type="text" name="pwd" value="${userDto.pwd}" placeholder="비밀번호 6자리이상"></label>
            <label>이름: <input type="text" name="name" value="${userDto.name}" placeholder="이름"></label>
            <label>휴대전화번호: <input type="text" name="phoneNumber" value="${userDto.phoneNumber}" placeholder="휴대전화번호"></label>
            <label>이메일: <input type="text" name="email" value="${userDto.email}" placeholder="이메일"></label>
            <button>등록</button>
        </form>
    </section>

    <script>
        function formCheck(frm){
            if(frm.pwd.value.length<7){
                document.querySelector("#msg").innerHTML = "비밀번호는 6자리 이상 입력해주세요."
                return false;
            }

        }
    </script>
    </body>
</html>