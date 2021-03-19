<%@ page import="user.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardDetail</title>
</head>
<body>
   <table border=1>
      <tr>
         <td>제목</td>
         <td colspan="3">${bo.title}</td>
      </tr>
      <tr>
         <td>작성일</td>
         <td colspan="3">
         <fmt:formatDate value="${bo.regdate}" pattern="yyyy.MM.dd. hh:mm"/>
         </td>
      </tr>
      <tr>
         <td>작성자</td>
         <td>${bo.writerID}</td>
         <td>조회수</td>
         <td><fmt:formatNumber value="${bo.hit}" type="number" pattern="###,###"/></td>
      </tr>
      <tr>
         <td>첨부파일</td>
         <td colspan="3">
            <c:forTokens var="fileName" items="${bo.files}" delims="," varStatus="t"> <!-- split 개념 (짜르기) delims는 구분자 -->
               <c:set var="style" value=""/>
               
               <c:if test="${fn:endsWith(fileName, '.zip') }">
                  <c:set var="style" value="font-weight:bold; color:red;" />
               </c:if>
               
               <a href="${fileName}" style="${style}">${fn:toUpperCase(fileName)}</a>
               <c:if test="${!t.last}">
                  /
               </c:if>
               
            </c:forTokens>
         </td>
      </tr>
      <tr>
         <td colspan="4">${bo.content}</td>
      </tr>
      
   </table>
   <!-- 목록 버튼 -->
   <input type="button" onclick="location.href='board?f=${param.f}&q=${param.q}&p=${param.p}'" value="목록">

   <!-- 글삭제 버튼 -->
      <script>     
      function checkYN() {   
         let yn =confirm("정말 삭제하시겠습니까?");
         console.log(yn);
         if(yn==true){
            alert('글이 삭제되었습니다.');
             document.getElementById("delite").submit();
         }else{
            alert('글삭제를 취소합니다.');
         }
   }
      </script>
  
    <input type="button" onclick="checkYN()" value="글삭제"/>   
 
    <form id="delite" action="board">
         <input type ="hidden" name="id" value="${id}"/>
   </form>
   
   <!-- 댓글 -->
   <form action="detail" method="post">
   <input type ="hidden" name="pageID" value="${param.id}"/>
   <table border=1>
      <tr>
         <th>
            <input type="hidden" name="writerID" value="최유경" /><!-- 글쓴이(로그인 한 사람) -->
         </th>
         <td>
            <input type="text" name="comment" /><!-- comment -->
         </td>
         <td>
            <input type="submit" value="등록" /><!-- date -->
         </td>
      </tr>
   </table>
      
   </form>
   <c:set var="cnt" value="${count}" />
   <c:if test="${cnt == 0}">
   댓글이 없습니다.
   </c:if>
   <c:if test="${cnt != 0}">
   <table border=1>
      <tr>
         <th>글쓴이</th>
         <td>내용</td>
         <td>시간</td>
      </tr>
      
      <c:forEach var="n" items="${bc}">
      <tr>
         <th>${n.writerID}</th>
         <td>${n.comment}</td>
         <td>${n.regdate}</td>
      </tr>
      </c:forEach>
      
   </table>
   </c:if>
</body>
</html>