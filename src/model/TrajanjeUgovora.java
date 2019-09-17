package model;
public class TrajanjeUgovora {
     
     private  String ugovor ="";
     
     public static TrajanjeUgovora ONE_YEAR = new TrajanjeUgovora("Jedna godina");
     public static TrajanjeUgovora TWO_YEARS = new TrajanjeUgovora("Dve godine");     
     public static TrajanjeUgovora UNDEFINED = new TrajanjeUgovora("");

    public TrajanjeUgovora(String ugovor) {
        this.ugovor=ugovor;
    }

    @Override
    public String toString() {
        return ugovor;
    }


    

   
     
}
