/**
 * 
 */
package applicat;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


/**
 * Conntects to db_masrermind and Manipulates the "joueur" dataset
 * @author hp
 */
public class SqlJoueur {
	
	
	/**
	 * @param c Connection
	 * @param st Statement
	 * @param res ResultSet
	 */
	private Connection c;
	public Statement st;
	private ResultSet res;
	/**
	 * Default Constructor
	 * @throws ClassNotFoundException Exception
	 * @throws SQLException Exception
	 */
	public SqlJoueur() throws ClassNotFoundException, SQLException {
		c  = MyConnection.getConnection();
		if(c != null) {
			try {
			st = c.createStatement();
			
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Inserts new row in DB if Primary key is not already used else return informative message about the matter
	 * @param pseudo pseudo
	 * @param nom name
	 * @param prenom family name
	 * @param email email
	 * @param genre sexe
	 * @throws ClassNotFoundException Exception
	 * @throws SQLException Exception
	 */
	public void DbInsert(String pseudo,String nom,String prenom,String email,String genre) throws ClassNotFoundException, SQLException{
		
		if(pseudo.equals("")|| nom.equals("")||prenom.equals("")|| email.equals("")) {
			JOptionPane.showMessageDialog (null, "Verifier les inputs","WARNING", 0);
		}
		else {
			try {
				st.addBatch("insert into joueur " + "values('"+pseudo+"',"+0+",'"+nom+"','"+prenom+"',"+"DATE(NOW())"+",'"+email+"','"+genre+"',"+10+")");
				st.executeBatch();
			}catch(BatchUpdateException e) {
				JOptionPane.showMessageDialog (null, "Le pseudo '"+pseudo+"' deja Existant!" ,"WARNING", 0);
			}finally {
				stop();
			}
		}
	}
	
	/**
	 * Updates row where there is pseudo , returns message if there is a problem
	 * @param pseudo
	 * @param idrole  0 (if 'joueur') or 1 (if 'admin)
	 * @param nom new name
	 * @param prenom new family name
	 * @param email new email
	 * @param genre new sexe
	 * @throws ClassNotFoundException Exception
	 * @throws SQLException Exception
	 */
	public void DbUpdate(String pseudo,int idrole, String nom,String prenom,String email,String genre) throws ClassNotFoundException, SQLException{
		
		try {
			String query = "UPDATE joueur "+ "SET idrole = "+idrole +", nom = '"+nom+"', prenom = '"+prenom+"', dateinscri = DATE(NOW()), email = '"+email+"', genre = '"+genre+"'"
					+ " WHERE pseudo = '"+pseudo+"'";
			st.addBatch(query);
			st.executeBatch();
		}catch(BatchUpdateException e) {
			JOptionPane.showMessageDialog (null, "Probleme lors du modification" ,"WARNING", 0);
		}finally {
			//stop();
		}
	}
	
	/**
	 * Updates player score
	 * @param pseudo
	 * @param score new score
	 * @throws SQLException Exception
	 */
	public void DbUpdate_score(String pseudo, int score) throws SQLException {
		
		try {
			String query = "UPDATE joueur "+ "SET nbessai = "+score
					+ " WHERE pseudo = '"+pseudo+"'";
			st.addBatch(query);
			st.executeBatch();
		}catch(BatchUpdateException e) {
			JOptionPane.showMessageDialog (null, "Probleme lors du modification" ,"WARNING", 0);
		}finally {
			//stop();
		}
		
	}
	/**
	 * Deletes player
	 * @param pseudo
	 */
	public void DbDelete(String pseudo) {
		
		String query = "delete from joueur where pseudo = '"+pseudo+"'";
		try {
			st.addBatch(query);
			st.executeBatch();
			//stop();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog (null, "Probleme lors du supression" ,"WARNING", 0);
		}
		
	}
	/**
	 * Getter
	 * @param wanted the wanted column
	 * @return ResultSet , the wanted column
	 * @throws SQLException Exception
	 */
	public ResultSet getSmthng(String wanted) throws SQLException {
		
		return st.executeQuery("select " + wanted +" from joueur");
	}
	/**
	 * Getter
	 * @param wanted the wanted column
	 * @param cond String, condition on wanted column values
	 * @return ResultSet
	 * @throws SQLException Exception
	 */
	public ResultSet getSmthng(String wanted, String cond) throws SQLException {
		return st.executeQuery("select " + wanted +" from joueur where "+wanted+" = '"+cond+"'");
	}
	/**
	 * Getter
	 * @param wanted the wanted column
	 * @param cond int, condition on wanted column
	 * @return ResultSet
	 * @throws SQLException Exception
	 */
	public ResultSet getSmthng(String wanted, int cond) throws SQLException {
		return st.executeQuery("select " + wanted +" from joueur where "+wanted+" = "+cond);
	}
	/**
	 * Getter Pseudo column for existance check
	 * @param cond wanted pseudo
	 * @return ResultSet
	 * @throws SQLException Exception
	 */
	public ResultSet getPseudo(String cond) throws SQLException {
		
		return getSmthng("pseudo",cond);
	}
	/**
	 * Checks the existance of pseudo
	 * @param pseudo , pseudo we want to check the existance of
	 * @return true if it exists
	 * @throws SQLException Exception
	 */
	public boolean pseudoExist(String pseudo) throws SQLException {
		
		return getPseudo(pseudo).first();
		
	}
	/**
	 * Checks if a player is Admin
	 * @param pseudo player to check
	 * @return true if player is Admin
	 * @throws SQLException Exception
	 */
	public boolean isAdmin(String pseudo) throws SQLException {
		
		return (st.executeQuery("select pseudo from joueur where pseudo = '"+pseudo+"' and idrole = 1").first());
		
	}
	/**
	 * Looks for a player
	 * @param pseudo
	 * @return ResultSet
	 * @throws SQLException Exception
	 */
	public ResultSet recherche(String pseudo) throws SQLException {
		return st.executeQuery("select * from joueur where pseudo = '"+pseudo+"'");
	}
	/**
	 * Getter
	 * @return ResultSet of the players ordered by their score, from highest to lowest
	 * @throws SQLException Exception
	 */
	public ResultSet getSortedScore() throws SQLException {
		
		res = st.executeQuery("select * from joueur order by nbessai asc");
		if(res.first()) {
			return res;
		}
		else return null;
	}
	
	/**
	 * Closes the Statement and ResultSet
	 * @throws SQLException Exception
	 * @throws NullPointerException Exception
	 */
	public void stop() throws SQLException, NullPointerException {
		if(res!= null) {res.close();}
		if (st != null) {st.close();}
	}
	
}
