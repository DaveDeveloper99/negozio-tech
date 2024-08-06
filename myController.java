package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.MessagingException;

@Controller
public class myController {
    
	
	//LISTA DI PRODOTTI ORDIANATI
	ArrayList<Ordini> listaO = new ArrayList();
	double sommaOrdini= 0;
	
	//LISTA IMMAGINI
	ArrayList<String> listaI = new ArrayList();
	
	
	                       //INTERAZIONE CON IL DATABASE
                           private final ProdJDBCTemplate prodJDBCTemp;



	                       //COSTRUTTORE CHE INIETTA ProdJDBCTEmplate.
	                       @Autowired
	                       public myController(ProdJDBCTemplate prodJDBCTemp) {
		                         this.prodJDBCTemp= prodJDBCTemp;
	                       }
	
	
	
	                      // RECUPERO LISTA ORDINI E LORO AGGIUNTA AL MODELLO
	                      @GetMapping("/")
	                      public String getNegozio(Model model) {
	                             ArrayList<prodotti> lista = prodJDBCTemp.getProdotti();
	                             
	                             listaI.clear();
	                             listaO.clear();
	                             
	                             sommaOrdini = 0;
	               
	                                        model.addAttribute("lista", lista);
	                                        return "index"; //INSERISCI LA PAGINA HTML PRINCIPALE DELLO STORE
	
	                     }
	                      
	                      
	                      // MAPPATURA PER LA GESTIONE DI PRODOTTI IN INGRESSO MAGAZZINO
	                      @GetMapping("/gestione")
	                      public String getGestione(@RequestParam(value = "message", required = false) String message, Model model) {
	                          if (message != null) {
	                              model.addAttribute("message", message);
	                          }
	                          return "InsProdotti";  // INSERISCI LA PAGINA HTML PER LA GESTIONE DEI PRODOTTI IN INGRESSO
	                      }
	                      
	                      
	                      
	                      @PostMapping("/submit")
	              		  public ResponseEntity<String> getSubmit(@RequestParam("nome") String nome, 
	              				                                @RequestParam("descrizione") String descrizione,
	              				                                @RequestParam("prezzo") double prezzo, 
	              				                                @RequestParam("img") String img,
	              				                                @RequestParam("qnt") int qnt) {
	              			
	              			prodJDBCTemp.ins_Prodotto(nome, descrizione, prezzo, img, qnt);
	              			
	              			            return ResponseEntity.ok("Prodotto " +  nome  + " aggiunto con successo!");

	                  
	                  }
	                      
	                      
	                      @PostMapping("/delete")
	              		  public ResponseEntity<String> getDelete(@RequestParam("nome") String nome){
	              			
	              			     prodJDBCTemp.delete(nome);
	              			
	              			                 return ResponseEntity.ok("Prodotto " +  nome  + " cancellato con successo!");
	              		}
	                      
	                      
	                      @PostMapping("/process")
	              		public String getProcess(@RequestParam ("qnts") String[] listaQnt, Model model) {
	              			
	              			ArrayList<Ordini> listaOrdini= new ArrayList();
	              			
	              			double somma=0;
	              			ArrayList<prodotti> lista= prodJDBCTemp.getProdotti();
	              			
	              			for(int i=0; i<lista.size(); i++) {
	              				
	              				if( !listaQnt[i].equals("0")) {
	              					listaI.add(lista.get(i).img);
	              					Ordini O1= new Ordini();
	              					O1.setNome(lista.get(i).nome);
	              					O1.setQnt(Integer.parseInt(listaQnt[i]));
	              				
	              					listaOrdini.add(O1);
	              					
	              					somma+= lista.get(i).getPrezzo() * O1.getQnt();
	              					sommaOrdini += lista.get(i).getPrezzo() * O1.getQnt();
	              				}
	              			}
	              			
	              			model.addAttribute("lista", listaOrdini);
	              			model.addAttribute( "somma",somma);
	              			
	              			return ("");
	              		}
	
}
