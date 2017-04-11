<!DOCTYPE html>
<html>
    <head>
        <title>Customer Support</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>All Post</h2>

        <security:authorize access="hasRole('ADMIN')">    
            <a href="<c:url value="/user" />">Manage User Accounts</a><br /><br />
            
        </security:authorize>
            <a href="<c:url value="/poll/create" />">Create new poll</a>
        <a href="<c:url value="/ticket/create" />">New Post</a>  
        <a href="<c:url value="/ticket/listlecture" />">lecture</a>
        <a href="<c:url value="/ticket/listlab" />">lab</a>
        <a href="<c:url value="/ticket/listother" />">other</a>
        <br /><br />
        <c:choose>
            <c:when test="${fn:length(ticketDatabase) == 0}">
                <i>0 Post.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${ticketDatabase}" var="entry">
                    Post #${entry.key}:
                    <a href="<c:url value="/ticket/view/${entry.key}" />">
                        <c:out value="${entry.value.subject}" /></a>
                    (Post by: <c:out value="${entry.value.customerName}" />)
                    <hr>
                    <hr>
                </c:forEach>
                hidden post start<br>
                here is lecture<br>
                <c:forEach items="${ticketDatabase}" var="entry2">
                    <c:if test="${entry2.value.categories == 'lecture'}" >
                        Post #${entry2.key}:
                        <a href="<c:url value="/ticket/view/${entry2.key}" />"> 
                            <c:out value="${entry2.value.subject}" /></a>
                        (Post by: <c:out value="${entry2.value.customerName}" />)<br>
                    </c:if>
                </c:forEach>
                <hr>

                here is lab<br>
                <c:forEach items="${ticketDatabase}" var="entry3">
                    <c:if test="${entry3.value.categories == 'lab'}" >
                        Post #${entry3.key}:
                        <a href="<c:url value="/ticket/view/${entry3.key}" />"> 
                            <c:out value="${entry3.value.subject}" /></a>
                        (Post by: <c:out value="${entry3.value.customerName}" />)</br>
                    </c:if>
                </c:forEach>
                <hr>
                here is other<br>
                <c:forEach items="${ticketDatabase}" var="entry4">
                    <c:if test="${entry4.value.categories == 'other'}" >
                        Post #${entry4.key}:
                        <a href="<c:url value="/ticket/view/${entry4.key}" />"> 
                            <c:out value="${entry4.value.subject}" /></a>
                        (Post by: <c:out value="${entry4.value.customerName}" />)</br>
                    </c:if>
                </c:forEach>            
                <hr>
                <security:authorize access="hasRole('ADMIN') or principal.username=='${entry.value.customerName}'">            
                    [<a href="<c:url value="/ticket/edit/${entry.key}" />">Edit</a>]
                </security:authorize>
                <security:authorize access="hasRole('ADMIN')">            
                    [<a href="<c:url value="/ticket/delete/${entry.key}" />">Delete</a>]
                </security:authorize>
                <br /><br />

            </c:otherwise>
        </c:choose>
          <br>      
            <c:choose>
               <c:when test="${fn:length(pollTicketDatabase) == 0}">
                <i>0 Poll.</i>
            </c:when>
                  <c:otherwise>
                      <c:forEach items="${pollTicketDatabase}" var="entry5">
                           Poll #${entry5.key}:
                        <a href="<c:url value="/poll/view/${entry5.key}" />"> 
                            <c:out value="${entry5.value.pollSubject}" /></a><br>
                      </c:forEach>
                         </c:otherwise>  
                </c:choose>
                
    </body>
</html>