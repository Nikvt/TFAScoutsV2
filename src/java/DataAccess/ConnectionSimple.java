
package DataAccess;

import java.sql.Connection;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.Context;
import Exception.*;
import java.sql.SQLException;
import javax.naming.NamingException;

public class ConnectionSimple {
    
    private static Connection connection;
    
    private ConnectionSimple (){
        
    }
    
    public static Connection getInstance() throws ConnectionException{
        if (connection == null){
            try{
                Context ctx = new InitialContext();
                DataSource source = (DataSource) ctx.lookup("jdbc/myNyu");
                connection = source.getConnection();
            }
            catch (NamingException e){
                throw new ConnectionException( "-Naming-" + e.getMessage() );
            }
            catch (SQLException e){
                throw new ConnectionException ( "-SQL-" + e.getMessage() );
            }
        }
        return connection;
    }
}
