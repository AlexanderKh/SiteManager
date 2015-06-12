<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<a href="/">Back to index</a>
 <table border=1>
     <jstl:forEach var="permission" items="${users}">
         <tr>
             <td>
                 ${permission.id}
             </td>
             <td>
                 ${permission.name}
             </td>
             <td>
                 ${permission.userGroup}
             </td>
         </tr>
     </jstl:forEach>
 </table>
</body>
</html>
