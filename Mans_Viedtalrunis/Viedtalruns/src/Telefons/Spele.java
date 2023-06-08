package Telefons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Spele extends Lietotne{
	

	public Spele(String appVards, int appIzmers) {
		super(appVards, appIzmers);
	}

	//Atribūti
	private String vards;
	private int rezultats;
	private int atkartojumi;
	//Setter metodes
	
	public void setVardu(String vards){
		this.vards = vards;
	}
	
	public void setRezultats(int rezultats){
		this.rezultats = rezultats;
	}
	
	public void setAtkartojumus(int atkartojumi){
		this.atkartojumi = atkartojumi;
	}
	
	//Getter metodes
	
	public String getVardu(){
		return vards;
	}
	
	public int getRezultats(){
		return rezultats;
	}
	
	public int getAtkartojumus(){
		return atkartojumi;
	}
	
	public static void spele(int izmers, String nosaukums) {
		
		Lietotne lietotne = new Lietotne(nosaukums, izmers);
		lietotne.IeladetAplikaciju(izmers);
		
		boolean SpeletAtkal = true;
		int Atmineti = 0;
		int Atkartojumi = -1;
		
		Spele lietotajs = new Spele(nosaukums, Atkartojumi);
		
		lietotajs.setVardu(JOptionPane.showInputDialog(null, "Ievadi savu username:", nosaukums, JOptionPane.PLAIN_MESSAGE));

        while (SpeletAtkal) {
        	Atkartojumi++;
            String[] Vardi = {"roka", "galva", "ierocis", "zupa", "telefons","zivs","zieds","skudra","virve","dators","bite","cepums","uguns","lauva","pirts","laiva","latvija","violets","kamera","koks"};

            // Izvēlās nejauši izvēlētu vārdu no masīva
            int NejaussIndekss = (int) (Math.random() * Vardi.length);
            String IzveletaisVards = Vardi[NejaussIndekss].toUpperCase();
            
            StringBuilder UzminetaisVards = new StringBuilder();
            for (int i = 0; i < IzveletaisVards.length(); i++) {
            	UzminetaisVards.append('_');
            }

            int Meiginajumi = 3;

            // Spēlē spēli, līdz kamēr vārds ir uzminēts vai nav atlicis neviens mēģinājums
            while (Meiginajumi > 0 && UzminetaisVards.toString().contains("_")) {
                String Pazinojums = "Vārds: " + UzminetaisVards + "\n";
                String ievade = JOptionPane.showInputDialog(null, Pazinojums + "Ievadi burtu: (Meiģinājumi atlikuši: " + Meiginajumi + "):", nosaukums, JOptionPane.PLAIN_MESSAGE);

                if (ievade == null || ievade.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nepareiza ievade! Lūdzu ieraksti burtu.", nosaukums, JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                char UzminetaisBurts = Character.toUpperCase(ievade.charAt(0));

                // Pārbauda vai izvēlētajā vārdā ir uzminētais burts
                boolean Atrasts = false;
                for (int i = 0; i < IzveletaisVards.length(); i++) {
                    if (IzveletaisVards.charAt(i) == UzminetaisBurts) {
                    	UzminetaisVards.setCharAt(i, UzminetaisBurts);
                    	Atrasts = true;
                    }
                }

                if (!Atrasts) {
                    JOptionPane.showMessageDialog(null, "Nepareizs minējums!", nosaukums, JOptionPane.WARNING_MESSAGE);
                    Meiginajumi--;
                }
            }

            if (UzminetaisVards.toString().contains("_")) {
                JOptionPane.showMessageDialog(null, "Jums beidzās mēģinājumi! Vārds bija: " + IzveletaisVards, nosaukums, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Apsveicam, "+lietotajs.getVardu()+"! Jūs uzminējāt vārdu: " + IzveletaisVards, nosaukums, JOptionPane.PLAIN_MESSAGE);
                Atmineti++;
                Atkartojumi--;
            }

            int Izvele = JOptionPane.showConfirmDialog(null, "Vai vēlaties spēlēt vēlreiz?", "Spēlēt vēlreiz?", JOptionPane.YES_NO_OPTION);
            SpeletAtkal = (Izvele == JOptionPane.YES_OPTION);
        }
        
        lietotajs.setRezultats(Atmineti);
        lietotajs.setAtkartojumus(Atkartojumi);
       
        File fails = new File("rezultati.txt");
        	
        try {
            FileWriter fw = new FileWriter(fails, true);
            PrintWriter pw = new PrintWriter(fw);
            
            pw.write(lietotajs.getVardu()+" ("+lietotajs.getAtkartojumus()+" Atkartojumi ) : "+lietotajs.getRezultats()+" Punkti\n");
        	pw.close();  
            
        }catch(IOException e) {
        	JOptionPane.showMessageDialog(null, "Kļūda ierakstot failā!.");
        }
        
        BufferedReader br = null;
        String teksts = "";
        
        try {
        	String str;
        	
            br = new BufferedReader(new FileReader("rezultati.txt"));
        	
        	while((str = br.readLine()) != null){
        		teksts += str+"\n";
        	}
        	
        	JOptionPane.showMessageDialog(null, teksts);
        }catch(IOException e) {
        	JOptionPane.showMessageDialog(null, "Kļūda nolasot failu!");
        }
        
        try {
        	if (br != null)
        		br.close();
        }catch(IOException e) {
        	JOptionPane.showMessageDialog(null, "Kļūda aizverot failu!");
        }
        
	}
	
}
