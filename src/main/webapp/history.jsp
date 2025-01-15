<%@ page import="searchEnginePackage.HistoryResult" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="resultTable">
        <table border=2>
            <tr>
                <th>
                    Keyword
                </th>
                <th>
                    link
                </th>
            </tr>
            <%
                ArrayList<HistoryResult> historyResults = (ArrayList<HistoryResult>)request.getAttribute("results");
                for (HistoryResult result:historyResults){
            %>
                <tr>
                    <td>
                        <%out.println(result.getKeyword()); %>
                    </td>
                    <td>
                        <a href="<%out.println(result.getLink()); %>"><%out.println(result.getLink()); %></a>
                    </td>
                </tr>
            <%}%>
        </table>
    </div>
</body>
</html>
