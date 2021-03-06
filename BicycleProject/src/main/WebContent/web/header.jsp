<%@page import="database.MemberDto" %>
<%@page import="database.MemberDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>동행하는 사람들...</title>
    <script src="./js/apiLogin.js"></script>
</head>

<body>
<!-- 헤더 -->
<div class="container-fluid" style="padding-top: 0px; padding-bottom: 0px; margin-left: 0px; margin-right: 0px;">
    <div class="jumbotron" style="padding-top: 0px; padding-bottom: 0px; margin-left: 0px; margin-right: 0px;">

        <div class="row justify-content-between">
            <div class="col-lg-4">
                <img alt="ㅁㄴㅇ" src="./image/headerlogo/로고이미지.gif">
            </div>
            <!-- 로고 텍스트 이미지 직사각형 수정 요망 넓이 크게 -->
            <div class="col-lg-4 text-center">
                <a href="./main.jsp"><img src="./image/headerlogo/로고텍스트.gif"
                                          alt="ㅁㄴㅇ"></a>
            </div>
            <div class="col-lg-1"></div>

            <%
                String userid = (String) session.getAttribute("userID");
                MemberDao dao = new MemberDao();
                if (session.getAttribute("userID") == null) {
            %>
            <div class="col-lg-3 align-self-center">
                <a href="login.jsp" class="btn btn-uni btn-sm mr-3">&nbsp;로그인&nbsp;</a>
                <a href="signup.jsp" class="btn btn-uni btn-sm">회원가입</a>
            </div>
            <%
            } else if (session.getAttribute("userID") != null) {
            %>
            <div class="col-lg-3 col-sm-3 align-self-center">
                <p><%=dao.getUserInfo(userid).getMember_name()%>님 반갑습니다~ <a href="logout.jsp"
                                                                            class="btn btn-uni btn-sm"
                                                                            onclick="kakaoLogout()">로그아웃</a>
            </div>

            <%
                }
            %>


        </div>
    </div>

    <div class="row">

        <div class="col-lg-1"></div>

        <!-- 주카테고리 -->

        <div class="col-lg-4">
            <ul class="nav nav-pills justify-content-around display-5">
                <%
                    int group = 0;
                    if (session.getAttribute("userGroup") != null) {
                        group = (Integer) session.getAttribute("userGroup");
                    } else {
                        group = 0;
                    }
                    if (group != 5) {
                %>
                <li><a href="BoardA.jsp?categoty_sm=자전거 종류" class="navbar-link text-dark">지식정보</a></li>
                <%
                } else if (group == 5) {
                %>
                <li><a href="admin_BoardA.jsp" class="navbar-link text-dark">지식정보</a></li>
                <%
                    }
                %>
                <li>|</li>
                <li><a href="./BoardB_Q.jsp" class="navbar-link text-dark">소통</a></li>
                <li>|</li>
                <%
                    if (session.getAttribute("userGroup") != null) {
                        group = (Integer) session.getAttribute("userGroup");
                    } else {
                        group = 0;
                    }
                    if (group != 5) {
                %>
                <li><a href="BoardC_S.jsp?pagesize=0" class="navbar-link text-dark">중고거래</a></li>
                <%
                } else if (group == 5) {
                %>
                <li><a href="BoardC_S.jsp?pagesize=0" class="navbar-link text-dark">중고거래</a></li>
                <%
                    }
                %>
                <li>|</li>
                <%
                    if (session.getAttribute("userGroup") != null) {
                        group = (Integer) session.getAttribute("userGroup");
                    } else {
                        group = 0;
                    }
                    if (group != 5) {
                %>
                <li><a href="challenge.jsp" class="navbar-link text-dark">챌린지</a></li>
                <%
                } else if (group == 5) {
                %>
                <li><a href="admin_challenge.jsp?check=0" class="navbar-link text-dark">챌린지</a></li>
                <%
                    }
                %>
            </ul>
        </div>

    </div>

    <!-- hr -->
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-lg-10">
            <hr>
        </div>
        <div class="col-lg-1"></div>
    </div>
</div>
</body>
