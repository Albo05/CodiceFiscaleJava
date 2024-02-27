import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class CodiceFiscale {
    private String cfCognome;
    private String cfNome;
    private int cfAnnoNascita;
    private String cfMeseNascita;
    private String cfGiornoNascita_Sesso;
    private String cfLuogoNascita;
    private String cfLetteraControllo;
    private String codiceFiscale;
    // Array contenenti consonanti e vocali
    private final char[] consonanti =
            {'b','c','d','f','g','h','l','m','n','p','q','r','s','t','v','w','x','y','j','k','z'};
    private final char[] vocali = {'a','e', 'i', 'o', 'u'};
    // COSTRUTTORE PER CREARE UN OGGETTO SEMPLICE
    public CodiceFiscale() {
    }
    // COSTRUTTORE PER CREARE UN OGGETTO E DEFINIRNE I VALORI DEGLI ATTRIBUTI IMMEDIATAMENTE
    public CodiceFiscale(String cognome, String nome, String dataDiNascita,
                         String sesso, String luogoNascita) {
        this.cfCognome = calcolaCognome(cognome);
        this.cfNome = calcolaNome(nome);
        this.cfAnnoNascita = calcolaAnnoNascita(dataDiNascita);
        this.cfMeseNascita = calcolaMeseNascita(dataDiNascita);
        this.cfGiornoNascita_Sesso = calcolaGiorno_Sesso(dataDiNascita, sesso);
        this.cfLuogoNascita = calcolaLuogoNascita(luogoNascita);
        try {
            this.cfLetteraControllo =
                    calcolaLetteraControllo(calcolaCodiceFiscaleIncompleto(cognome, nome,
                            dataDiNascita, sesso, luogoNascita));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getCfCognome() {
        return cfCognome;
    }
    public String getCfNome() {
        return cfNome;
    }
    public int getCfAnnoNascita() {
        return cfAnnoNascita;
    }
    public String getCfMeseNascita() {
        return cfMeseNascita;
    }
    public String getCfGiornoNascita_Sesso() {
        return cfGiornoNascita_Sesso;
    }
    public String getCfLuogoNascita() {
        return cfLuogoNascita;
    }
    public String getCfLetteraControllo() {
        return cfLetteraControllo;
    }
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    /* METODI PER CONFRONTARE UNA STRINGA CON UN ARRAY DI CONSONANTI O VOCALI.
    * Si crea una nuova stringa contenente solo i valori ricercati, della
   lunghezza richiesta con "outputLength"
    */
    public String charEqualsArray(String inputString, int outputLength, char[]
            array) {
        String outputString = "";
        inputString = inputString.toLowerCase();
        for(int i = 0; i < inputString.length() && outputString.length() <
                outputLength; i++) {
            for(int j = 0; j < array.length && outputString.length() <
                    outputLength; j++) {
                if(inputString.charAt(i) == array[j]) outputString +=
                        inputString.charAt(i);
            }
        }
        return outputString;
    }
    public String charEqualsArray2(String inputString, int outputLength, char[]
            array) {
        String outputString = "";
        inputString = inputString.toLowerCase();
        boolean b = true;
        for(int i = 0; i < inputString.length() && outputString.length() <
                outputLength; i++) {
            for(int j = 0; j < array.length && outputString.length() <
                    outputLength; j++) {
                if (inputString.charAt(i) == array[j]) {
                    if ((b == true) && (outputString.length() == 1)) {
                        b = false;
                        continue;
                    }
                    outputString += inputString.charAt(i);
                }
            }
        }
        return outputString;
    }
    /* METODO PER IL CALCOLO DEL COGNOME
    * Si effettua la reiterazione in quanto è possibile avere dei cognomi con
   meno di 3 consonanti ed in quel caso
    * si devono ricercare le vocali.
    * Se non sono presenti abbastanza vocali da raggiungere una lunghezza totale
    di 3 lettere allora si aggiunge "X".
    */
    public String calcolaCognome(String inputCognome) {
        String cognome = charEqualsArray(inputCognome, 3, consonanti);
        if (cognome.length() < 3) cognome += charEqualsArray(inputCognome, 3 -
                cognome.length(), vocali);
        if (cognome.length() < 3) cognome += "x";
        return cognome.toUpperCase();
    }
    /* METODO PER IL CALCOLO DEL NOME
    * Ha la stessa procedura del metodo per il cognome con l'unica differenza
   che richiede solo la prima, terza e quarta consonante.
    * Bisogna quindi usare il secondo metodo per la verifica della stringa in
   input che permette di saltare la seconda
    * consonante trovata.
    */
    public String calcolaNome(String inputNome) {
        String nome = charEqualsArray2(inputNome, 3, consonanti);
        if (nome.length() < 3) nome = calcolaCognome(inputNome);
        return
                nome.toUpperCase();
    }
    /* METODO PER IL CALCOLO DELL'ANNO DI NASCITA
    * Prendendo la stringa dell'anno di nascita in input con formato
   "dd/MM/yyyy" crea una substringa con esclusivamente
    * gli ultimi 2 valori dell'anno.
    */
    public int calcolaAnnoNascita(String dataDiNascita) {
        cfAnnoNascita = Integer.parseInt(dataDiNascita.substring(8));
        return cfAnnoNascita;
    }
    /* METODO PER IL CALCOLO DEL MESE.
    * Ho dovuto effettuare uno switch in quanto, in base al mese di nascita
   viene attribuita una lettera.
    */
    public String calcolaMeseNascita(String dataDiNascita) {
        String mese = dataDiNascita.substring(3, 5);
        switch (mese) {
            case "01":
                cfMeseNascita = "A";
                break;
            case "02":
                cfMeseNascita = "B";
                break;
            case "03":
                cfMeseNascita = "C";
                break;
            case "04":
                cfMeseNascita = "D";
                break;
            case "05":
                cfMeseNascita = "E";
                break;
            case "06":
                cfMeseNascita = "H";
                break;
            case "07":
                cfMeseNascita = "L";
                break;
            case "08":
                cfMeseNascita = "M";
                break;
            case "09":
                cfMeseNascita = "P";
                break;
            case "10":
                cfMeseNascita = "R";
                break;
            case "11":
                cfMeseNascita = "S";
                break;
            case "12":
                cfMeseNascita = "T";
                break;
            default:
                JOptionPane.showMessageDialog(new JFrame(), "INSERITO VALORE MESE INVALIDO");
            throw new IllegalArgumentException("Unexpected value: " + mese);
        }
        return cfMeseNascita;
    }
    /* METODO PER IL CALCOLO DEL GIORNO E DEL SESSO.
    * Viene presa in input la data di nascita che viene estrapolata nello stesso
    modo dei metodi precedenti (substring)
    * per poi aggiungere 40 se il soggetto è una donna, o tenere il valore
   originale se è un uomo.
    */
    public String calcolaGiorno_Sesso(String dataDiNascita, String sesso) {
        int i = 0;
        cfGiornoNascita_Sesso = dataDiNascita.substring(0, 2);
        i = Integer.parseInt(cfGiornoNascita_Sesso);
        if (sesso.equalsIgnoreCase("F")) {
            i += 40;
        }
        cfGiornoNascita_Sesso = String.format("%d", i);
        return cfGiornoNascita_Sesso;
    }
    /* METODO PER IL CALCOLO DEL LUOGO DI NASCITA.
    * Qui ho dovuto scaricare il file con tutti i 2800 comuni italiani (file
   "comuni.txt") accanto ai quali è presente
    * il codice corrispondente.
    * Ho effettuato una lettura del file con il BufferedReader in quanto è in
   grado di leggere una linea intera e non solo un char alla volta.
    * Quando viene trovato il comune corrispondente il programma estrapola solo
   gli ultimi 4 caratteri della riga, con substring
    * che sono quelli contenenti il codice identificativo.
    */
    public String calcolaLuogoNascita(String luogoDiNascita) {
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new
                    FileReader("directory"));
                    String l;
            while((l = inputStream.readLine()) != null ) {
                if(l.contains(luogoDiNascita.toUpperCase())) {
                    cfLuogoNascita = l.substring(l.length() - 4);
                }
            }
            if (cfLuogoNascita == null) {
                BufferedReader inputStream2 = null;
                try {
                    inputStream2 = new BufferedReader(new
                            FileReader("directory"));
                            String l2;
                    while ((l2 = inputStream2.readLine()) != null) {
                        if
                        (l2.toLowerCase().contains(luogoDiNascita.toLowerCase())) cfLuogoNascita =
                                l2.substring(l2.length() - 4);
                    }
                }finally {
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cfLuogoNascita;
    }
    /* Questo metodo mi era necessario per calcolare l'ultima lettera del CF, la
   lettera di controllo.
    * La quale è calcolabile solo dal codice fiscale incompleto tramite un
   algoritmo.
    */
    public String calcolaCodiceFiscaleIncompleto(String cognome, String nome,
                                                 String dataDiNascita, String sesso, String luogoNascita) throws IOException {
        String codiceFiscaleIncompleto = calcolaCognome(cognome) +
                calcolaNome(nome) + calcolaAnnoNascita(dataDiNascita) +
                calcolaMeseNascita(dataDiNascita) + calcolaGiorno_Sesso(dataDiNascita, sesso) +
                calcolaLuogoNascita(luogoNascita);
        return codiceFiscaleIncompleto;
    }
    /* METODO PER IL CALCOLO DELLA LETTERA DI CONTROLLO.
    * Questo metodo estrapola l'algoritmo per il calcolo della lettera che è il
   seguente:
    * Dividere il codice fiscale in 2 stringhe, una con i caratteri in posizione
    pari e l'altra con quelli in posizione dispari.
    * Ad ogni carattere è corrispondente un valore numerico, il valore cambia se
    il carattere era in posizione pari o dispari
    * (Ho dovuto creare due file  "letteraControlloDispari.txt" e
   "letteraControlloPari.txt" per poi usare il metodo del bufferedReader per leggere e
    confrontare il valore,
    * in quanto uno switch sarebbe risultato in molte più linee di codice)
    * Una volta trovati tutti i valori numerici, essi vengono sommati e poi
   divisi per 26 con l'operazione MODULO per tenerne solo il resto.
    * Il valore del resto viene poi confrontato con una tabella, ad ogni valore
   è corrispondente una lettera quindi fino ad un valore di 25.
    * Si è stato una sbatta allucinante pensare a come farlo senza database e
   senza altri metodi.
    */
    public String calcolaLetteraControllo(String codiceFiscaleIncompleto) {
        String pari = "";
        String dispari = "";
        int letteraControllo = 0;
        for (int i = 0; i < codiceFiscaleIncompleto.length(); i++) {
            if ((i % 2) == 0 ) dispari += codiceFiscaleIncompleto.charAt(i);
            else pari += codiceFiscaleIncompleto.charAt(i);
        }
        BufferedReader inputStreamPari = null;
        BufferedReader inputStreamDispari = null;
        try {
            String l;
            for (int i = 0; i < pari.length(); i++) {
                inputStreamPari = new BufferedReader(new
                        FileReader("directory"));
                while ((l = inputStreamPari.readLine()) != null) {
                    if (l.startsWith(String.format("%c",
//
                            pari.charAt(i)))) letteraControllo += Integer.parseInt(l.substring(l.length() -
                            2).trim());
                }
            }
            for (int i = 0; i < dispari.length(); i++) {
                inputStreamDispari = new BufferedReader(new
                        FileReader("directory"));
                while ((l = inputStreamDispari.readLine()) != null) {
                    if (l.startsWith(String.format("%c",
                            dispari.charAt(i)))) letteraControllo += Integer.parseInt(l.substring(l.length() -
                            2).trim());
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }finally {
            if ((inputStreamPari != null) && (inputStreamDispari != null)) {
                try {
                    inputStreamPari.close();
                    inputStreamDispari.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        letteraControllo = letteraControllo % 26;
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new
                    FileReader("directory"));
                    String l;
            while ((l = inputStream.readLine()) != null) {
                if (l.startsWith(String.format("%d", letteraControllo))) {
                    cfLetteraControllo =
                            Character.toString(l.charAt(l.length() - 1));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cfLetteraControllo;
    }
    /* Primo metodo per il calcolo finale, potete usarlo se avete usare il
   costruttore della classe creando l'oggetto e dando un valore a tutti gli attributi.
    * In caso contrario potete usare il secondo metodo che richiede di passargli
    Scaricato da Alberto Fantinato (albertofantinato05@gmail.com)
   lOMoARcPSD|38102537
    i dati degli attributi.
    * Questo metodo è utile quando si va a creare una nuova anagrafica, basta
   creare un oggetto e passare i dati al costruttore, lui richiama tutti i metodi
   della classe.
    */
    public String calcolaCodiceFiscale() {
        String cognome = this.getCfCognome();
        String nome = this.getCfNome();
        int annoNascita = this.getCfAnnoNascita();
        String meseNascita = this.getCfMeseNascita();
        String giornoNascita_sesso = this.getCfGiornoNascita_Sesso();
        String luogoNascita = this.getCfLuogoNascita();
        String letteraControllo = this.getCfLetteraControllo();
        codiceFiscale = (cognome + nome + annoNascita + meseNascita +
                giornoNascita_sesso + luogoNascita + letteraControllo).toUpperCase();
        return codiceFiscale;
    }
    /* Metodo per il calcolo finale, utile nel caso si voglia calcolare il codice
    fiscale passando i dati direttamente.
    * Quindi come metodo di calcolo codice fiscale come quelli sui siti
   internet.
    * Per una nuova anagrafica è più comodo usare il metodo precedente.
    */
    public String calcolaCodiceFiscale(String cognome, String nome, String
            dataDiNascita, String sesso, String luogoNascita) throws IOException {
        String codiceFiscale = calcolaCodiceFiscaleIncompleto(cognome, nome,
                dataDiNascita, sesso, luogoNascita) +
                calcolaLetteraControllo(calcolaCodiceFiscaleIncompleto(cognome, nome,
                        dataDiNascita, sesso, luogoNascita));
        return codiceFiscale;
    }
    /* Per permettere al programma di funzionare correttamente bisogna dichiarare
    "throws IOException"
    * o il programma chiederà di mettere in un blocco try e catch i metodi della
    classe.
    * In quanto alcuni usare i file reader e possono lanciare delle eccezzioni.
    * Cancellate pure il main in quanto non serve, l'ho tenuto per fare dei test
    e verificare che funzionasse tutto.
    */
    public static void main(String[] args) {
    }
}