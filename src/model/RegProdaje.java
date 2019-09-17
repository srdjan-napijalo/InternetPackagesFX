package model;
import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegProdaje {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id", 1);
    private final StringProperty ime = new SimpleStringProperty(this, "ime", "");
    private final StringProperty adresa = new SimpleStringProperty(this, "adresa", "");  
    private final IntegerProperty brzina = new SimpleIntegerProperty(this, "brzina", 0);
    private final StringProperty protok = new SimpleStringProperty(this, "protok", "");
    private final ObjectProperty trajanjeUgovora = new SimpleObjectProperty(this, "trajanjeUgovora", TrajanjeUgovora.UNDEFINED);
    
    private final ObjectProperty<ArrayList<String>> listaGreski = new SimpleObjectProperty<>(this, "listaGreski", new ArrayList<>());
    
    public ObjectProperty<ArrayList<String>> greskeProperty() {
        return listaGreski;
    }
   
    public RegProdaje(int id, String ime, String adresa, int brzina, String protok, TrajanjeUgovora tu){
        this.id.set(id);
        this.ime.set(ime);
        this.adresa.set(adresa);
        this.brzina.set(brzina);
        this.protok.set(protok);
        this.trajanjeUgovora.set(tu);
    }
    public RegProdaje() {
    }
    public RegProdaje(String ime, String adresa){
        this.ime.set(ime);
        this.adresa.set(adresa);
    }
    
    public Integer getId(){
        return id.get();
    }
    public void setId(int id){
        this.id.set(id);
    }
    public IntegerProperty idProperty(){
        return id;
    }
    public void increment(){
        this.idProperty().set(this.idProperty().get()+1);
    }
    
    public String getIme() {
        return ime.get();
    }
    public void setIme(String ime){
        this.ime.set(ime);
    }
    public StringProperty imeProperty(){
        return ime;
    }
    
    public String getAdresa(){
        return adresa.get();
    }
    public void setAdresa(String adresa){
        this.adresa.set(adresa);
    }
    public StringProperty adresaProperty() {
        return adresa;
    }
    
    public Integer getBrzina(){
        return brzina.get();
    }
    public void setBrzina(int brzina){
       this.brzina.set(brzina);
    }
    public IntegerProperty brzinaProperty(){
        return brzina;
    }
    
    public String getProtok(){
        return protok.get();
    }
    public void setProtok(String protok){
        this.protok.set(protok);
    }
    public StringProperty protokProperty(){
        return protok;
    }
    
    public ObjectProperty<TrajanjeUgovora> trajanjeUgovoraProperty() {
        return  trajanjeUgovora;
    }
    public void setTrajanjeUgovora(TrajanjeUgovora trajanjeUgovora) {
       this.trajanjeUgovora.set(trajanjeUgovora);
    }
    public Object getTrajanjeUgovora() {
        return trajanjeUgovora.get();
    }
    
    public boolean provera(){
        boolean provera = true;
        if(!RegProdaje.isWord(ime.get())||ime.get().isEmpty())
            { 
           listaGreski.getValue().add("Niste uneli ime\n"); 
            provera=false;
            }
        if(adresa.get().isEmpty())
            {
           listaGreski.getValue().add("Niste uneli adresu\n"); 
            provera=false;
            }
        if(brzina.getValue()==0)
        {
            listaGreski.getValue().add("Niste odabrali brzinu interneta\n");
            provera=false;
        }
         if(protok.get().isEmpty())
        {     

            listaGreski.getValue().add("Niste odabrali protok interneta\n");
            provera=false;
        }
         if(trajanjeUgovora.get().toString().isEmpty()){
             listaGreski.getValue().add("Niste odabrali rok ugovorne obaveze");
             provera=false;
         }
         
             return provera;
        }
    

   @Override
    public String toString() {
        return  "\nID: "+ id.get()+ "\n"
                + "Ime: " + ime.get() + " \n"
                + "Adresa: " + adresa.get() + "\n"
                + "Brzina: " + brzina.get() + "\n"
                + "Protok: "+ protok.get() + "\n"
                + "Trajanje ugovorne obaveze: "
                + trajanjeUgovora.get().toString();
    }   
    
     //HELPER FUNCTIONS- IS CHAR LETTER/DIGIT
                        public static boolean isCharLetter(char c){
   			 if (c >= '0' && c <= '9') 
   			 	return false;
				else  return true;	
			};

		

			public static boolean isWord(String sl){
				boolean provera=false;
				if(sl.isEmpty()||sl.length()<2) return false;
				for(int i =0; i<sl.length(); i++){
					if(isCharLetter(sl.charAt(i))) provera = true;
						else{ provera = false;
							break;
					}
				}return provera;
			};


			public static String capitalize(String str){
                            return str.substring(0, 1).toUpperCase()+str.substring(1);
			};

  
    
}
