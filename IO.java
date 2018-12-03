
import java.util.Scanner;

public class IO
{
	private UserInterface UInterface; 
	private TextInterface GUI_Text;
	private GUI_1 GUI_Erick;
	private GUI_2 GUI_Jeremy;
	private GUI_3 GUI_Cecilia;
	
	
	
    public static final int OPTION_0 = 0;
    public static final int OPTION_1 = 1;
    public static final int OPTION_2 = 2;
    public static final int OPTION_3 = 3;
    
    public IO()
    {
    	GUI_Text = new TextInterface();
    	GUI_Erick = new GUI_1();
    	GUI_Jeremy = new GUI_2();
    	GUI_Cecilia = new GUI_3();
    	//UInterface = GUI_Jeremy;
    	UInterface = GUI_Text;
    }
    
    public void display(String s)
    {
    	UInterface.display(s);
    }

    public String getLine()
    {
    	return UInterface.getLine();
    }

    public void selectInterface(int GUI)
    {
    	switch(GUI)
    	{
    	case OPTION_0:
    		UInterface = GUI_Text;
    		return;
    	case OPTION_1:
    		UInterface = GUI_Erick;
    		return;
    	case OPTION_2:
    		UInterface = GUI_Jeremy;
    		return;
    	case OPTION_3:
    		UInterface = GUI_Cecilia;
    		return;
    	default:
    		return;
    	}
    	
    }


}//end public class IO
