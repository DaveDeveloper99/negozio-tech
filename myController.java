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
	                             ArrayList<prodotti> listaComputer = prodJDBCTemp.getProdotti("computer");
	                             ArrayList<prodotti> listaSmartphone = prodJDBCTemp.getProdotti("smartphone");
	                             ArrayList<prodotti> listaMacchineFotografiche = prodJDBCTemp.getProdotti("macchinefotografiche");
	                             ArrayList<prodotti> listaSmartTV = prodJDBCTemp.getProdotti("smarttv");
	                             
	                             listaI.clear();
	                             listaO.clear();
	                             
	                             sommaOrdini = 0;
	               
	                                        model.addAttribute("listaC", listaComputer);
	                                        model.addAttribute("listaS", listaSmartphone);
	                                        model.addAttribute("listaMF", listaMacchineFotografiche);
	                                        model.addAttribute("listaTV", listaSmartTV);
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
	              				                                @RequestParam("qnt") int qnt,
	              				                                @RequestParam("tipologia") String tipologia) {
	              			
	              			prodJDBCTemp.ins_Prodotto(nome, descrizione, prezzo, img, qnt, tipologia);
	              			
	              			            return ResponseEntity.ok("Prodotto " +  nome  + " aggiunto con successo!");

	                  
	                  }
	                      
	                      
	                      @PostMapping("/delete")
	              		  public ResponseEntity<String> getDelete(@RequestParam("nome") String nome,
	              				                                  @RequestParam("tipologia") String tipologia){
	              			ArrayList<prodotti> lista= new ArrayList();
	              			
	              			    prodJDBCTemp.delete(nome, tipologia);
	                                     	
	              			                 return ResponseEntity.ok("Prodotto " +  nome  + " cancellato con successo!");
	              		}
	                      
	                      
	                      @PostMapping("/process")
	                      public String process(@RequestParam("qnts") String[] listaQnt, Model model) {
	                          ArrayList<Ordini> listaOrdiniComputer = new ArrayList<>();
	                          ArrayList<Ordini> listaOrdiniSmartphone = new ArrayList<>();
	                          ArrayList<Ordini> listaOrdiniMF = new ArrayList<>();
	                          ArrayList<Ordini> listaOrdiniTV = new ArrayList<>();
	                          
	                          double somma = 0;

	                          ArrayList<prodotti> listaC = prodJDBCTemp.getProdotti("computer");
	                          ArrayList<prodotti> listaS = prodJDBCTemp.getProdotti("smartphone");
	                          ArrayList<prodotti> listaMF = prodJDBCTemp.getProdotti("macchinefotografiche");
	                          ArrayList<prodotti> listaTV = prodJDBCTemp.getProdotti("smarttv");

	                          // Gestione della categoria computer
	                          for (int i = 0; i < listaC.size(); i++) {
	                              if (!listaQnt[i].equals("0")) {
	                                  Ordini O1 = new Ordini();
	                                  O1.setNome(listaC.get(i).nome);
	                                  O1.setQnt(Integer.parseInt(listaQnt[i]));
	                                  
	                                  listaOrdiniComputer.add(O1);
	                                  
	                                  somma += listaC.get(i).getPrezzo() * O1.getQnt();
	                              }
	                          }

	                          // Gestione della categoria smartphone
	                          for (int i = 0; i < listaS.size(); i++) {
	                              if (!listaQnt[i].equals("0")) {
	                                  Ordini O1 = new Ordini();
	                                  O1.setNome(listaS.get(i).nome);
	                                  O1.setQnt(Integer.parseInt(listaQnt[i]));
	                                  
	                                  listaOrdiniSmartphone.add(O1);
	                                  
	                                  somma += listaS.get(i).getPrezzo() * O1.getQnt();
	                              }
	                          }

	                          // Gestione della categoria macchine fotografiche
	                          for (int i = 0; i < listaMF.size(); i++) {
	                              if (!listaQnt[i].equals("0")) {
	                                  Ordini O1 = new Ordini();
	                                  O1.setNome(listaMF.get(i).nome);
	                                  O1.setQnt(Integer.parseInt(listaQnt[i]));
	                                  
	                                  listaOrdiniMF.add(O1);
	                                  
	                                  somma += listaMF.get(i).getPrezzo() * O1.getQnt();
	                              }
	                          }

	                          // Gestione della categoria smart TV
	                          for (int i = 0; i < listaTV.size(); i++) {
	                              if (!listaQnt[i].equals("0")) {
	                                  Ordini O1 = new Ordini();
	                                  O1.setNome(listaTV.get(i).nome);
	                                  O1.setQnt(Integer.parseInt(listaQnt[i]));
	                                  
	                                  listaOrdiniTV.add(O1);
	                                  
	                                  somma += listaTV.get(i).getPrezzo() * O1.getQnt();
	                              }
	                          }

	                          // Passa i dati al modello
	                          model.addAttribute("listaC", listaOrdiniComputer);
	                          model.addAttribute("listaS", listaOrdiniSmartphone);
	                          model.addAttribute("listaMF", listaOrdiniMF);
	                          model.addAttribute("listaTV", listaOrdiniTV);
	                          model.addAttribute("somma", somma);

	                          return "carello";
	                      }

	
}
