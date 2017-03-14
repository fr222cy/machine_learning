<%-- 
    Document   : index
    Created on : Mar 14, 2017, 3:10:38 PM
    Author     : filip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/main/" method="post">
            <select name="classifier" >
                <option value="nb">Naive Bayes</option>
                <option value="svm">Support Vector Machine</option>
            </select>
            
              <select name="setting">
                <option value="s1">Setting 1</option>
                <option value="s2">Setting 2</option>
                <option value="s3">Setting 3</option>
            </select>
                <input type="submit" value="Submit">   
        </form>
        <h1>Hello World!</h1>
        <p>${result}</p>
    </body>
</html>
