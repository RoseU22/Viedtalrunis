package Telefons;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class SazinasLietotne extends Lietotne {


	public SazinasLietotne(String appVards, int appIzmers) {
		super(appVards, appIzmers);
	}

	//Atribūti
	private String Vards, Uzvards, TelNr, dzimGads;
	
	
	//Setter Metodes
	
	public void setVards(String Vards){
		this.Vards = Vards;
	}
	
	public void setUzvards(String Uzvards){
		this.Uzvards = Uzvards;
	}
	
	public void setTelNr(String TelNr){
		this.TelNr = TelNr;
	}
	
	public void setDzimGads(String dzimGads){
		this.dzimGads = dzimGads;
	}
	
	//Getter metodes
	
	public String getVards(){
		return Vards;
	}
	
	public String getUzvards(){
		return Uzvards;
	}
	
	public String getTelNr(){
		return TelNr;
	}
	
	public String getDzimGads(){
		return dzimGads;
	}
	
	static int meklet(ArrayList<SazinasLietotne> LietotajuMasivs, String Vards, String nosaukums){
		for(int i=0; i<LietotajuMasivs.size(); i++){
			if(LietotajuMasivs.get(i).getVards().equalsIgnoreCase(Vards)){
				return i;
			}
		}
		
		JOptionPane.showMessageDialog(null, "Lietotājs ar vārdu: "+Vards+" netika atrasts", nosaukums, JOptionPane.WARNING_MESSAGE);
		return -1;
		
	}
	
	public static void SazLietotne(int izmers, String nosaukums){
		
		Lietotne lietotne = new Lietotne(nosaukums, izmers);
		lietotne.IeladetAplikaciju(izmers);
			
	    String izvele;
	    String[] Sazinasdarbibas = {"Pievienot lietotāju", "Noņemt lietotāju", "Kontaktu saraksts", "Iziet no lietotnes"};
	    ArrayList<SazinasLietotne> LietotajuMasivs = new ArrayList<SazinasLietotne>();
	    
	    do{
	    	
	    	izvele = (String)JOptionPane.showInputDialog(null,"Izvēlies darbību", nosaukums, JOptionPane.QUESTION_MESSAGE, null, Sazinasdarbibas, Sazinasdarbibas[0]);
	    	
	    	switch(izvele){
					
				case "Pievienot lietotāju":
					
					//Pievienot lietotāju
					
					SazinasLietotne lietotajs = new SazinasLietotne(izvele, izmers);
					
					lietotajs.setVards(JOptionPane.showInputDialog(null, "Ievadi personas vārdu!", nosaukums, JOptionPane.PLAIN_MESSAGE));
					
					lietotajs.setUzvards(JOptionPane.showInputDialog(null, "Ievadi personas uzvārdu!", nosaukums, JOptionPane.PLAIN_MESSAGE));
					
					String tNr = "";
					do{
						
						tNr = JOptionPane.showInputDialog(null, "Ievadi personas tālrui formātā +000 00000000", nosaukums, JOptionPane.PLAIN_MESSAGE);
						
					}while(!Pattern.matches("^[+]{1}[0-9]{3} [0-9]{8}$", tNr));
					lietotajs.setTelNr(tNr);
					
					String dzimGads="";
					do{
						
						dzimGads = JOptionPane.showInputDialog(null, "Ievadi personas dzimšanas gadu, formātā 00/00/0000", nosaukums, JOptionPane.PLAIN_MESSAGE);
						
					}while(!Pattern.matches("^[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}$", dzimGads));
					lietotajs.setDzimGads(dzimGads);
					
					LietotajuMasivs.add(lietotajs);
					
					break;
					
					
				case "Noņemt lietotāju":
					
					if(LietotajuMasivs.size()>0){
						
						int kuruDzest = meklet(LietotajuMasivs, JOptionPane.showInputDialog(null, "Ievadi lietotāja vārdu!", nosaukums, JOptionPane.PLAIN_MESSAGE), nosaukums);
						
						if(kuruDzest != -1){
							JOptionPane.showMessageDialog(null, "Lietotājs: "+LietotajuMasivs.get(kuruDzest).getVards()+" "+LietotajuMasivs.get(kuruDzest).getUzvards()+" tika noņemts no saraksta", nosaukums, JOptionPane.INFORMATION_MESSAGE);
							LietotajuMasivs.remove(kuruDzest);
						}
					}else
						JOptionPane.showMessageDialog(null, "Sarakstā nav neviens lietotājs", nosaukums, JOptionPane.INFORMATION_MESSAGE);
					
					break;
					
				
				case "Kontaktu saraksts":
					
					String str = "Lietotāju skaits: "+LietotajuMasivs.size()+
					"\n__________________________________\n";
					
					for(int i=0; i<LietotajuMasivs.size(); i++){
						str += "\nVārds: "+LietotajuMasivs.get(i).getVards()+"\nUzvārds: "+LietotajuMasivs.get(i).getUzvards()+"\nTelefona Nr: "+LietotajuMasivs.get(i).getTelNr()+
						"\nDzimšanas diena: "+LietotajuMasivs.get(i).getDzimGads()+
						"\n__________________________________\n";
					}
					
					JTextArea ta = new JTextArea(str, 10, 40);
					ta.setEditable(false);
					JScrollPane sp = new JScrollPane(ta);
					sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					JOptionPane.showMessageDialog(null, sp, nosaukums, JOptionPane.PLAIN_MESSAGE);
					
					break;
	    		
			case "Iziet no lietotnes":
				JOptionPane.showMessageDialog(null, "Tika iziets no saziņas lietotnes");
				break;	
	    	
	    	}
	    	
	    }while(!izvele.equals("Iziet no lietotnes"));
	}
	
  }