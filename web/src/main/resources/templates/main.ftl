<html>
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <#include "common-header.ftl">

</head>
<body>
<h1>Main application page</h1>
<div>Hello, ${username}! You are logged in.</div>
<br/>
<a href="/logout">Logout</a>
</body>
</html>