package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

@Component
public class ProdJDBCTemplate {
	
             private JdbcTemplate jdbcTemplateObject;
	
	                 @Autowired
	                 public void setJdbcTemplaeObject(JdbcTemplate jdbcTemplateObject) {
		                         this.jdbcTemplateObject = jdbcTemplateObject;
		
	                            }
	                 
	                 //REGISTRAZIONE NUOVO RECORD NELLA TABELLA 
	
	                 public int ins_Prodotto( String nome, String descrizione, double prezzo, String img, int qnt) {
		                        String query = "INSERT INTO prodotti( nome, descrizione, prezzo, img, qnt) VALUES (?, ?, ?, ?, ?)";
				    
		                               return jdbcTemplateObject.update( query, nome, descrizione, prezzo, img, qnt);
			
	                           }
	                 
	                
	                 // CANCELLAZIONE DI UN RECORD DALLA TABELLA
	                 public int delete(String nome) {
	             	            String query = "DELETE FROM 'Prodotti' WHERE nome=?";
	             		   
	             		              return jdbcTemplateObject.update(query, nome);
	             	           }
	                 
	                 
	                 //RECUPER RECORD E MAPPATURA SU LISTA PRODOTTI
	                 
	                 public ArrayList<prodotti> getProdotti(){
	                  	    ResultSet rs1 = null;
	                  	
	                 
	                                       String query = "SELECT * FROM  Prodotti";
	                                              return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<prodotti>>() {
	                         	
	                                       @Override
	                                       public ArrayList<prodotti> extractData(ResultSet rs) throws SQLException {
	                             	              ArrayList <prodotti> listaProdotti = new ArrayList<>();
	                             	
	                             	              while (rs.next()) {
	                             		 
	                             		                 prodotti p1= new prodotti();
	                             		     
	                             		                          p1.setNome(rs.getString("nome"));
	                             		                          p1.setDescrizione(rs.getString("descrizione"));
	                             		                          p1.setPrezzo(rs.getDouble("prezzo"));
	                             		                          p1.setImg(rs.getString("img"));
	                             		                          p1.setQnt(rs.getInt("qnt"));
	                             		 
	                             		                          listaProdotti.add(p1);
	                             		 
	                             	                              }
	                             	 
	                             	                               return listaProdotti;  //era il nostro listaeventi
	                                                } 
	                                              }
	                                           );              
	                                         }
	                 
	                 // AGGIORNAMENTO QUANTITA DISPONIBILI PER LA VENDITA
	                 public int updateQnt(int qnt, String nome) {
	                	 String query= "UPDATE '' SET qnt=qnt-? WHERE nome=?";
	                	 return jdbcTemplateObject.update(query, qnt, nome);
	                 }
	                 
	                 
	                 
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	

	