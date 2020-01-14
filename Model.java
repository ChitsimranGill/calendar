

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.event.*;


/**
 * Model class of the project.
 * Contains getter and setter methods of calendar
 * @author chitsimrangill
 *
 */
public class Model 
{
    public static String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static String[] wDays = {"S", "M", "T", "W", "T", "F", "S"};
    public static String[] wDaysFull = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private GregorianCalendar gc = new GregorianCalendar();
    private ArrayList<ChangeListener> listeners = new ArrayList<>();
    private int maxDays;
    private int currentDay;
    private ArrayList<Event> eventList;
    private GregorianCalendar temporary = new GregorianCalendar();

    /**
     * Constructor
     */
    public Model() 
    {
        maxDays = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
        currentDay = gc.get(Calendar.DATE);
        eventList = new ArrayList<Event>();
    }

    /**
     * Gets event list
     * @return - list of events
     */
    public ArrayList<Event> getList() 
    {
        return eventList;
    }

    public void attach(ChangeListener l) 
    {
        listeners.add(l);
    }

    /**
     * Updates listener
     */
    public void update() 
    {
        for (ChangeListener l : listeners) 
        {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Gets next month
     */
    public void getNextMonth() 
    {
        gc.add(Calendar.MONTH, 1);
        maxDays = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
        update();
    }

    /**
     * Updates max days of a month
     */
    public void updateMaximumDays()
    {
    		maxDays = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * Gets previous month
     */
    public void previousMonth() 
    {
        gc.add(Calendar.MONTH, -1);
        maxDays = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
        update();
    }

    /**
     * Gets calendar
     * @return - calendar
     */
    public GregorianCalendar getGC() 
    {
        return gc;
    }

    /**
     * Gets max days of a month
     */
    public int getMaximumDays() 
    {
        return maxDays;
    }

    /**
     * Gets Month's name
     * @return - name of month
     */
    public String getMonthName() 
    {
        return months[gc.get(Calendar.MONTH)];
    }

    /**
     * Gets year
     * @return - year
     */
    public String getYear() 
    {
        return Integer.toString(gc.get(Calendar.YEAR));
    }

   /**
    * Gets month in Integer
    * @return - number of month
    */
    public int getMonthInt() 
    {
        return gc.get(Calendar.MONTH)+1;
    }

    /**
     * Gets Year in Int
     * @return - number of year
     */
    public int getYearInt() 
    {
        return gc.get(Calendar.YEAR);
    }

    /**
     * Gets Days in Integer
     * @return - number of day
     */
    public int getDayInt() 
    {
        return gc.get(Calendar.DATE);
    }

    /**
     * Gets day of Week of month
     * @return - day of week
     */
    public int getDayOfWeek() 
    {
        temporary = (GregorianCalendar) gc.clone();
        temporary.set(temporary.get(Calendar.YEAR), temporary.get(Calendar.MONTH), 1);
        return temporary.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * Gets last day of month
     * @return - last day of month
     */
    public int getDayLast()
    {
    		temporary = (GregorianCalendar) gc.clone();
    		temporary.set(temporary.get(Calendar.YEAR), temporary.get(Calendar.MONTH), getMaximumDays());
    		return temporary.get(Calendar.DAY_OF_WEEK);
    		
    }
    
    /**
     * Checks if list has event
     * @param i - index of event
     * @return - true if events exists
     */
    public boolean hasEvent(int i)
    {
        for (Event e : eventList)
        {
            if (e.getYear() == getYearInt() && e.getMonth() == getMonthInt() && e.getDay() == i)
            {
                return true;
            }
        }
        return false;
    }
}
