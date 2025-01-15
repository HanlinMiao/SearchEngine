package searchEnginePackage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        // Getting keyword from the frontend
        String keyword = request.getParameter("keyword");
        // Setting up connection to the database
        Connection connection = DatabaseConnection.getConnection();

        try {
            // Getting the top 30 results after running the ranking query
            ResultSet resultSet = connection.createStatement().executeQuery("select pageTitle, pageLink, (length(pageText)-length(replace(lower(pageText), \"" + keyword.toLowerCase() + "\", \"\")))/length(\"" + keyword.toLowerCase() + "\") as countoccurrence from pages order by countoccurrence desc limit 30;");
            ArrayList<SearchResult> results = new ArrayList<SearchResult>();
            // Transferring values from resultSet to result ArrayList
            while (resultSet.next()) {
                SearchResult searchResult = new SearchResult();
                searchResult.setTitle(resultSet.getString("pageTitle"));
                searchResult.setLink(resultSet.getString("pageLink"));
                results.add(searchResult);
            }
            request.setAttribute("results", results);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            request.setAttribute("out", out);
            request.getRequestDispatcher("search.jsp").forward(request, response);
        } catch (SQLException | ServletException exception) {
            exception.printStackTrace();
        }
    }
}
