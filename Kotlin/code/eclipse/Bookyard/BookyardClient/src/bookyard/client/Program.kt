package bookyard.client

import javax.swing.SwingUtilities;

public class Program {
	
	public fun main(args : Array<String>) {
		
		SwingUtilities.invokeLater(LoginDialogEventLoop());
		
	}
}