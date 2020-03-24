/**
 * This is a class
 * Created 2020-03-10
 *
 * @author Magnus Silverdal
 */
import java.sql.*;

public class DataBase {
    public static void main(String[] args) {
        try {
            // Set up connection to database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://"+DatabaseLoginData.DBURL + ":" + DatabaseLoginData.port + "/" + DatabaseLoginData.DBname +
                            "? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    DatabaseLoginData.user, DatabaseLoginData.password);

            // Setup statement
            Statement stmt = conn.createStatement();
            // Create query and execute
            int x = 1;
            String strSelect = "select body from story where id="+x;
            System.out.println("The SQL statement is: " + strSelect + "\n");
            String linkSelect = "select story_id, target_id, description from links where id="+x;


            ResultSet rset = stmt.executeQuery(strSelect);

            // Loop through the result set and print


            int rowCount = 0;
            while(rset.next()) {
                String body = rset.getString("body");
                System.out.println(body);
                ++rowCount;

            }
            ResultSet link = stmt.executeQuery(linkSelect);
            while(link.next()){
                String storyId = link.getString("story_id");
                String targetId = link.getString("target_id");
                String description = link.getString("description");
                System.out.println(storyId+" "+targetId+" "+description);
            }
            System.out.println("Total number of records = " + rowCount);

            // Close conn and stmt
            conn.close();
            stmt.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}

