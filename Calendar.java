

public class Calendar 
{
   /**
    * Tester class.
    * @param args
    */
   public static void main(String[] args)
   {
	   Model m = new Model();
	   DaysPanel dp = new DaysPanel(m);
	   CalendarPanel cp = new CalendarPanel(m);
	   m.attach(dp);
	   m.attach(cp);
	   Frame f = new Frame(cp, dp);
	   m.attach(f);
    }
   
}
