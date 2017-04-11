<!DOCTYPE html>
<html>
    <head>
        <title>Poll result</title>
    </head>
    <body>
         <h2>Poll #${pollId}: <c:out value="${poll.pollSubject}" /></h2>
           <c:forEach items="${poll.map}" var="entry">
               ${entry.key} : ${entry.value} <br>
            </c:forEach>
             <a href="<c:url value="/ticket" />">Return to main page</a>
    </body>
</html>
