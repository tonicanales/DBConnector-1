package test;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;

import dao.DBMComments;
import dao.DBManager;
import model.Comments;
import test.TestDBManager.MockManager;

public class TestDBMComments {

	

	@Test
	public void testInsert(){
		boolean result = true;
		DBMComments dbManager =   new DBMComments("localhost", "dbTest", "comments"); 		
		Comments comments1 =getMockDBMComments(); 
		
		try {
			dbManager.connect("root","poodb"); 
			dbManager.insert(comments1);
		} catch (Exception e) {
			result = false; 
			e.printStackTrace();
			
		}finally{
			dbManager.close(); 
		}
		
		Assert.assertEquals(true,result);
		Assert.assertEquals(true,comments1.getId()!=-1); 
	}
	
	
	@Test
	public void testUpdate(){
		boolean result = true;
		DBMComments dbManager =   new DBMComments("localhost", "dbTest", "comments"); 
		Comments comments1 =getMockDBMComments();
		Comments commentsUpdated=null; 
		
		try {
			dbManager.connect("root","poodb"); 
			dbManager.insert(comments1);
			
				comments1.setMyUser("Don update"); 
				comments1.setComments("Me han actualizado"); 
				comments1.setDatum(new Date(124563)); 
				
			
			dbManager.update(comments1); 
			
			commentsUpdated = dbManager.select(comments1.getId()); 
			
			
		} catch (Exception e) {
			result = false; 
			e.printStackTrace();	
		}finally{
			dbManager.close(); 
		}
		
		Assert.assertEquals(true,result);
		
	
		Assert.assertEquals("Don update",commentsUpdated.getMyUser());
		Assert.assertEquals("Me han actualizado",commentsUpdated.getComments());
		Assert.assertEquals(true,commentsUpdated.getId()!=-1); 
		Assert.assertEquals(new Date(124563).toString(),
				comments1.getDatum().toString());  
	}

	
	
	
	@Test
	public void testGet(){
		boolean result = true;
		DBMComments dbManager =   new DBMComments("localhost", "dbTest", "comments"); 		
		Comments comments1 =getMockDBMComments(); 
		Comments results = null; 
		try {
			dbManager.connect("root","poodb"); 
			
			dbManager.insert(comments1);
			
			results = dbManager.select(comments1.getId()); 
			
		} catch (Exception e) {
			result = false; 
			e.printStackTrace();
		}finally{
			dbManager.close(); 
		}
		
		Assert.assertEquals(true,result);
		Assert.assertEquals(comments1.getMyUser(),results.getMyUser()); 
		Assert.assertEquals(comments1.getComments(),results.getComments()); 
	}
	
	
	
	

	private Comments getMockDBMComments() { 
		
		Comments comments1 = new Comments();
		comments1.setMyUser("root");
		comments1.setEmail("root@root.com"); 
		comments1.setSummary("Esto es un resumen"); 
		comments1.setComments("Esto es un comentario"); 
		comments1.setDatum(new Date(System.currentTimeMillis())); 
		comments1.setWebpage("poo.cifo"); 
		return comments1;
	}
	
	
	
	
	
	
	
}
