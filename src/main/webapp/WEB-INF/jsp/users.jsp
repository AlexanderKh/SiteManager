<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
 <table border=1, >
     <jstl:forEach var="user" items="${users}">
         <tr>
             <td>
                 ${user.getName()}
             </td>
             <td>
                 ${user.getUserGroup()}
             </td>
         </tr>
     </jstl:forEach>
 </table>
</body>
</html>
