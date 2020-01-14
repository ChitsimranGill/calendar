

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Event class of to do list.
 * Contians getter and setter methods of events.
 * @author chitsimrangill
 *
 */
public class Event implements Comparable<Event> 
{

	private DaysPanel dp;
    private String desc;
    private String date;
    private int start;
    private int end;
    private GregorianCalendar gc;

   /**
    * Constructor
    * @param theDesc - description
    * @param theDate - date of event
    * @param theStart - start time
    * @param theEnd - end time
    */
    public Event(String theDesc, String theDate, int theStart, int theEnd) 
    {
        desc = theDesc;
        date = theDate;
        start = theStart;
        end = theEnd;
        GregorianCalendar c = new GregorianCalendar(this.getYear(), this.getMonth() - 1, this.getDay());
        this.gc = c;

    }

    /**
     * Prints event
     */
    public String toString() 
    {
        return desc+" at " + start + " - " + end;
    }

    /**
     * Gets calendar
     * @return - calendar
     */
    public GregorianCalendar getGeoCalendar() 
    {
        return gc;
    }

   /**
    * Gets events description
    * @return - description of event
    */
    public String getDescription() 
    {
        return desc;

    }

    /**
     * Gets date of event
     * @return - day of event
     */
    public String getDate() 
    {
        return date;

    }

    /**
     * Gets Year of event
     * @return - year of event
     */
    public int getYear() 
    {
        String year = date.substring(6, 10);
        return Integer.parseInt(year);
    }

    /**
     * Gets month of event
     * @return - month of event
     */
    public int getMonth() 
    {
        String month = date.substring(0, 2);
        if (month.substring(0, 1).equals("0")) 
        {
            month = date.substring(1, 2);
        }
        int x = Integer.parseInt(month);
        return x;
    }

    /**
     * Gets day of event
     * @return - day of event
     */
    public int getDay() 
    {

        String day = date.substring(3, 5);
        if (day.substring(0, 1).equals("0")) 
        {
            day = date.substring(4, 5);
        }
        int x = Integer.parseInt(day);
        return x;
    }

    /**
     * Gets day of week
     * @return
     */
    public int getDayOfWeek() 
    {
        return this.getGeoCalendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Gets start time of event
     * @return - start time
     */
    public int getStart() 
    {
        return start;
    }

    /**
     * Gets end time of event
     * @return - end time
     */
    public int getEnd() 
    {
        return end;
    }
    
    /**
     * Changes event description
     * @param s - new title
     */
    public void ChangeTitle(String s)
    {
    		this.desc = s;
    }
    
    /**
     * Changes event end time
     * @param i - new time
     */
    public void ChangeEnd(int i)
    {
    		this.end = i;
    }
    
    /**
     * Changes event start time
     * @param i - new time
     */
    public void ChangeStart(int i)
    {
    		this.start = i;
    }

    /**
     * Compares to events
     */
    public int compareTo(Event that) 
    {
        int num = 0;
        if (this.getYear() != that.getYear()) 
        {
            num = this.getYear() - that.getYear();
        } 
        else if (this.getMonth() != that.getMonth()) 
        {
            num = this.getMonth() - that.getMonth();
        } 
        else if (this.getDay() != that.getDay()) 
        {
            num = this.getDay() - that.getDay();
        } 
        else 
        {
            num = this.getStart() - that.getStart();
        }

        return num;
    }
    
    /**
     * Returns info about saved file
     */
    public String returnFileInfo()
    {
       String info = (this.getDescription() + "," + this.getDate() + "," + this.getStart() + "," + this.getEnd());
       return info;
    }
  
    
    
}
