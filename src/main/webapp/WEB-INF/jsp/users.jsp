<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<a href="/">Back to index</a>
 <table border=1>
     <jstl:forEach var="page" items="${users}">
         <tr>
             <td>
                 ${page.id}
             </td>
             <td>
                 ${page.name}
             </td>
             <td>
                 ${page.userGroup}
             </td>
         </tr>
     </jstl:forEach>
 </table>
</body>
</html>
