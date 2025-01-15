<%@ page import="java.util.ArrayList" %>
<%@ page import="searchEnginePackage.SearchResult" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="resultTable">
        <table border=2>
            <tr>
                <th>
                    Title
                </th>
                <th>
                    Link
                </th>
            </tr>
            <%
                ArrayList<SearchResult> results = (ArrayList<SearchResult>) request.getAttribute("results");
                for(SearchResult result:results){
            %>
            <tr>
                <td><% out.println(result.getTitle()); %></td>
                <td><a href="<% out.println(result.getLink()); %>"><% out.println(result.getLink()); %></a></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
