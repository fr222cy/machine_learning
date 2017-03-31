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
            
             <select name="testMethod">
                <option value="cross-validation">Cross-validation</option>
                <option value="percentage">Train 80% test 20%</option>
                <option value="test-set">Test set of 2017 articles</option>
            </select>
            
            <select name="shouldPreprocess">
                <option value="1">Preprocess dataset</option>
                <option value="0">No preprocessing(Raw text)</option>
            </select>
                <input type="submit" value="Submit">   
        </form>
        <h1>Hello World!</h1>
        <p>${result}</p>
    </body>
</html>
