package Telefons;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.UIManager;



class Viedtalrunis {
	
	String Zimols;
	int Platums;
	int Garums;
	
	public Viedtalrunis(String Zimols, int Platums, int Garums) {
		
		this.Zimols = Zimols;
		this.Platums = Platums;
		this.Garums = Garums;
	}
	
	public String getZimols() {
		return Zimols;
	}
	
	public int getPlatums() {
		return Platums;
	}
	
	public int getGarums() {
		return Garums;
	}
	
	@Override public String toString() {
		return ("Telefona ražotājs: "+this.getZimols()+"\nTelefona Platums un Garums: "+this.getPlatums()+"mm, "+this.getGarums()+"mm");
	}
	
	public static LinkedList<Viedtalrunis> NomainitTelefonu(LinkedList<Viedtalrunis> Telefons) {
		String Zimols;
		String[] Zimoli = {"Samsung","Apple","Nokia","Xiaomi","Oppo","Vivo"};
		
		int platums, garums;
		
		Zimols = (String)JOptionPane.showInputDialog(null, "Izvēlies telefona zīmolu", "Izvēle", JOptionPane.QUESTION_MESSAGE, null, Zimoli, Zimoli[0]);
		
		do {
		platums = Integer.parseInt(JOptionPane.showInputDialog("Ievadi telefona platumu"));
		}while(platums>500 || platums<100);
			
		do {
		garums = Integer.parseInt(JOptionPane.showInputDialog("Ievadi telefona garumu"));
		}while(garums>500 || garums<100);
			
		Telefons.add(new Viedtalrunis(Zimols, platums, garums));

		UIManager.put("OptionPane.minimumSize",new Dimension(platums,garums));
		
		return Telefons;
	}
	
	public static void main(String[] args) {
		
		 String izvele, teksts="";
		 String[] darbibas = {"Lietotnes","Informācija par telefonu","Nomainīt Telefonu","Apskatīt izveidotos telefonus","Izslēgt telefonu"};;
		
		 
		 LinkedList<Viedtalrunis> Telefons = new LinkedList<Viedtalrunis>();
		 Telefons.add(new Viedtalrunis("Samsung", 100, 100));
		 
		 do{
			 
			 izvele = (String)JOptionPane.showInputDialog(null, "Izvēlies darbību", "Izvēle", JOptionPane.QUESTION_MESSAGE, null, darbibas, darbibas[0]);
			 
			 switch(izvele){
			 
			 case "Lietotnes":
				 
				 Lietotne.ParaditAplikacijas();
				 
				 break;
				 
			 case "Informācija par telefonu":
				 
					JOptionPane.showMessageDialog(null, Telefons.getLast(), "Informācija", JOptionPane.INFORMATION_MESSAGE);
					
					break;
					
			case "Nomainīt Telefonu":
				
				NomainitTelefonu(Telefons);
					
				break;
				
			case "Apskatīt izveidotos telefonus":
				
				for (Viedtalrunis element: Telefons)
					teksts += element+"\n______________________________________\n";
					JOptionPane.showMessageDialog(null, teksts, "Izveidotie telefoni", JOptionPane.INFORMATION_MESSAGE);
					teksts="";
					
				break;
			 
				 
			 case "Izslēgt telefonu":
				 
				 JOptionPane.showMessageDialog(null, "Telefons tika izslēgts", "Informācija", JOptionPane.INFORMATION_MESSAGE);
				 
				 break;
				 
			 }
			 
		 }while(!izvele.equals("Izslēgt telefonu"));
		 
	  }
	
	}