package plugins.notify;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.PGConnection;
import org.postgresql.PGNotification;

import utils.ClearCache;

/**
 * Thread whish listen notification
 * @author Jean BOUDET
 */
public class NotifyListen extends Thread 
{
	private PGConnection pgconn;

	public NotifyListen(Connection conn) throws SQLException, ClassNotFoundException
	{
		this.pgconn = (PGConnection) conn;
		Statement stmt = conn.createStatement();
        stmt.execute("LISTEN clear");
        stmt.close();
	}
	
	public void run()
	{
		while (true) {
			try {
				PGNotification notifications[] = pgconn.getNotifications();
				if (notifications != null)
                {
                    for (int i = 0; i < notifications.length; i++) {
                    	PGNotification notification = notifications[i];
                    	
                    	if (notification.getName().equals("clear")) {
                    		String[] taxons = notification.getParameter().split(",");
                    		for (int j = 0; j < taxons.length; j++) {
                    			ClearCache.removeFor("taxon", taxons[j]);
                    		}
                    	}
                    }
                }
			
				Thread.sleep(500);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
