
package Telefons;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Lietotne{
	public JFrame frame = new JFrame();

	//Lietotnes atribūti
	public String appVards;
	public int appIzmers;
	
	public Lietotne(String appVards, int appIzmers) {
		this.appVards = appVards;
		this.appIzmers = appIzmers;
	}
	
	//Setter metodes
	public void setAppName(String Vards) {
		this.appVards = Vards;
	}
	public void setAppSize(int Izmers) {
		this.appIzmers = Izmers;
	}
	
	//Getter metodes
	public String getAppName() {
		return appVards;
	}
	public int getAppSize() {
		return appIzmers;
	}
		
		
	JProgressBar ProgressBar;
		
	Lietotne(int izmers){
			
	ProgressBar = new JProgressBar(0,izmers);
	ProgressBar.setBounds(40,40,160,30);
	ProgressBar.setValue(0);
	ProgressBar.setStringPainted(true);
	
	frame.add(ProgressBar);
	frame.setSize(250,150);
	frame.setLocation(850,450);
	frame.setLayout(null);
		
	}
			
	public void Ladesana(int izmers) {
		
		for(int i = 0; i <= izmers; i+=10) {
			
			ProgressBar.setValue(i);
			try {
				
				Thread.sleep(150);
				
			}catch(Exception e) {
				
				System.out.println(e);
				
			}
		}
	}
		
	public void IeladetAplikaciju(int izmers) {
		JOptionPane.showMessageDialog(null, "Tiek sagatavota aplikācija darbam!");
		
		Lietotne ProgressBarObject = new Lietotne(izmers);
		ProgressBarObject.frame.setVisible(true);
		ProgressBarObject.Ladesana(izmers);
		
		JOptionPane.showMessageDialog(null, "Aplikācija veiksmīgi ielādējās!");
		ProgressBarObject.frame.setVisible(false);
	}
	
	public static void ParaditAplikacijas() {
		
		String izvele;
		String[] lietotnes = {"Saziņas Lietotne", "Spēle", "Aizvērt lietotnes"};
		
		Lietotne Aplikacija = new Lietotne("", 0);
		
		do{
			 
			 izvele = (String)JOptionPane.showInputDialog(null, "Izvēlies lietotni", "Izvēle", JOptionPane.QUESTION_MESSAGE, null, lietotnes, lietotnes[0]);
			 
			 switch(izvele){
			 
			 case "Saziņas Lietotne":
				 
				 Aplikacija.setAppName("Saziņas lietotne");
				 Aplikacija.setAppSize(520);
				 
				 SazinasLietotne.SazLietotne(Aplikacija.getAppSize(), Aplikacija.getAppName());
				 
				 break;
				 
			 case "Spēle":
				 
				 Aplikacija.setAppName("Spēle");
				 Aplikacija.setAppSize(1000);
				 
				 Spele.spele(Aplikacija.getAppSize(), Aplikacija.getAppName());
				 
				 break;
				 
			 }
			 
		 }while(!izvele.equals("Aizvērt lietotnes"));
	}
}
