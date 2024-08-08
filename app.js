window.onload = gestoreLoad;

// Creiamo un array vuoto chiamato listaO che ci serve per ottenere tutti gli elementi presenti nel db 
let listaO =  [];

// Creiamo la classe prodotto ordinato 
class prodottoOrdinato {
    constructor(name, prezzo, qnt, url) {
        this.name = name;
        this.prezzo = prezzo;
        this.qnt = qnt;
        this.url = url;
    }

    visualizza() {
        return `Nome: ${this.name} Prezzo: ${this.prezzo} Quantità: ${this.qnt}`;
    }
}

function gestoreLoad() {
    // Refresh automatico della pagina
    window.addEventListener('pageshow', function(event) {
        if (event.persisted) {
            location.reload();
        }
    });

    // Effettua una chiamata alle rotte dell'applicazione per ottenere tutti i prodotti
    const categories = ['computer', 'smartphone', 'macchinefotografiche', 'smarttv'];
    
    categories.forEach(category => {
        $.ajax({
            url: `/show?category=${category}`,  // Endpoint REST per la categoria specifica
            type: 'GET',
            success: function(data) {
                data.forEach(function(item) {
                    let p1 = new prodottoOrdinato(item.nome, item.prezzo, 0, item.url);
                    listaO.push(p1);
                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Errore nel recupero dati:', textStatus, errorThrown);
                $('#dataList').append('<li>Errore nel caricamento dei dati</li>');
            }
        });
    });

    let nodo = document.getElementById("result");
    let btn = document.getElementById("btn");
    btn.onclick = add;

    // Inizializziamo una lista per inserire i prodotti ordinati
    let lista = [];

    // Aggiunge uno o più prodotti ordinati al carrello
    function add() {
        listaO.forEach((prodotto, i) => {
            let qnt = document.getElementById(prodotto.name).value;
            if (qnt > 0) {
                let p1 = new prodottoOrdinato(prodotto.name, prodotto.prezzo, qnt, prodotto.url);
                lista.push(p1);
            }
        });
        
        console.log(lista);
        crea();
        lista = [];
    }

    // Funzione crea va a popolare il carrello con gli elementi selezionati dall'utente
    function crea() {
        // Puliamo il nodo result
        rimuoviFigli(nodo);
        
        let tot = 0;

        // Per ogni elemento nella lista, creiamo un elemento p e aggiorniamo il carrello
        lista.forEach(prodotto => {
            let nodoP = document.createElement("p");
            nodoP.textContent = prodotto.visualizza();
            nodo.appendChild(nodoP);
            tot += prodotto.prezzo * prodotto.qnt;
        });

        // Creiamo un altro nodo per stampare il totale
        let nodoT = document.createElement("p");
        nodoT.textContent = "Totale: " + tot;
        nodo.appendChild(nodoT);
    }

    // Funzione per rimuovere i figli di un nodo
    function rimuoviFigli(nodo) {
        while (nodo.childNodes.length > 0) {
            nodo.removeChild(nodo.firstChild);
        }
    }

    // Aggiungiamo un event listener al link "Carello" nella navbar
    document.getElementById("carelloLink").addEventListener("click", function() {
        crea();
        document.getElementById("carelloSection").style.display = "block";
        document.getElementById("otherSections").style.display = "none";
    });
}

// Mantieni il codice jQuery originale per lo slider e altre funzionalità non correlate
