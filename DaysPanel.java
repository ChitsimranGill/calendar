
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * Creates to-do list panel.
 * @author chitsimrangill
 *
 */
public class DaysPanel extends JPanel implements ChangeListener 
{
    private Model m;
    private JTextArea dayText = new JTextArea(20, 15);
    private JPanel daysPanel = new JPanel();
    public String cdate;
    private JList<Event> daysJList;
    private JScrollPane eventsPane = new JScrollPane();
    private DefaultListModel listsModel = new DefaultListModel<>();
    private GregorianCalendar gc = new GregorianCalendar();
    public ArrayList<Event> list = new ArrayList<Event>();
    private JTextArea dayInText2 = new JTextArea(20, 15);
    
    @Override
    public void stateChanged(ChangeEvent e) 
    {
        createDaysPanel();
        revalidate();
        repaint();
    }

    /**
     * Constructs to do list
     * @return - to-do list
     */
    public JScrollPane createToDoList() 
    {
        m.updateMaximumDays();
        if (m.getMonthInt() < 10 && m.getDayInt() < 10) 
        {
            cdate = "0" + m.getMonthInt() + "/" + "0" + m.getDayInt() + "/" + m.getYear();

        } else if (m.getMonthInt() < 10) 
        {
            cdate = "0" + m.getMonthInt() + "/" + m.getDayInt() + "/" + m.getYear();

        } else if (m.getDayInt() < 10) 
        {
            cdate = m.getMonthInt() + "/" + "0" + m.getDayInt() + "/" + m.getYear();

        } else 
        {
            cdate = m.getMonthInt() + "/" + m.getDayInt() + "/" + m.getYear();
        }

        listsModel.clear();
        listsModel.add(0,cdate);

        int i =1; 
        for (Event e : m.getList()) 
        {
            if (m.getYearInt() == e.getYear() && m.getMonthInt() == e.getMonth() && m.getDayInt() == e.getDay()) 
            {
                listsModel.addElement(e);
            }
            i++;
        }
        daysJList = new JList(listsModel);
        eventsPane = new JScrollPane(daysJList);
        eventsPane.setVisible(true);
        return eventsPane;
    }

    /**
     * Creates view of to do list
     * @return - list
     */
    public JTextArea createDayListView() 
    {
        if (m.getMonthInt() < 10 && m.getDayInt() < 10) 
        {
            cdate = "0" + m.getMonthInt() + "/" + "0" + m.getDayInt() + "/" + m.getYear();

        } else if (m.getMonthInt() < 10) 
        {
            cdate = "0" + m.getMonthInt() + "/" + m.getDayInt() + "/" + m.getYear();

        } else if (m.getDayInt() < 10) 
        {
            cdate = m.getMonthInt() + "/" + "0" + m.getDayInt() + "/" + m.getYear();

        } else 
        {
            cdate = m.getMonthInt() + "/" + m.getDayInt() + "/" + m.getYear();
        }
        dayText.setText("");
        dayText.setText(cdate);
        for (Event e : m.getList()) 
        {
            if (m.getYearInt() == e.getYear() && m.getMonthInt() == e.getMonth() && m.getDayInt() == e.getDay()) 
            {
                dayText.append("\n" + e.toString());
            }
        }
        return dayText;
    }

    /**
     * Creates list
     * @param cm - model
     */
    public DaysPanel(Model cm) 
    {
        this.m = cm;
        readEventsFromFile();
        setLayout(new FlowLayout());
        add(createDaysPanel());
    }

    /**
     * Reads events from txt file
     */
    public void readEventsFromFile() 
    {
        try {
            FileReader fr = new FileReader(m.getMonthName()+"_"+m.getYearInt()+".txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String desc, date;
            int start, end;
            while ((line = br.readLine()) != null) 
            {
                String[] array = line.split("\\,");
                desc = array[0];
                date = array[1];
                start = Integer.parseInt(array[2]);
                end = Integer.parseInt(array[3]);
                Event event = new Event(desc, date, start, end);
                m.getList().add(event);
            }

            br.close();
            fr.close();
            System.out.println("Events successfully loaded.");

        } catch (IOException x) {
            System.out.println("No file to load from. Create some events, then quit to save the file. Then try again.");

        }
    }

    /**
     * Saves events into file
     */
    public void saveEventsToFile() 
    {

        try {
            FileWriter fw = new FileWriter(m.getMonthName()+"_"+m.getYearInt()+".txt");
            BufferedWriter bw = new BufferedWriter(fw);

            for (Event e : m.getList()) 
            {
                bw.write(e.returnFileInfo());
                bw.newLine();

            }

            System.out.println("Events Saved into "+m.getMonthName()+"_"+m.getYearInt()+".txt");
            bw.close();
            fw.close();

        } catch (IOException poop) 
        {
            System.out.println("io exception");
        }

    }
    
    /**
     * Gets months to do list
     */
    public void getMonthView()
    {
    	try {
            FileReader fr = new FileReader(m.getMonthName()+"_"+m.getYearInt()+".txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String desc, date;
            int start, end;
            while ((line = br.readLine()) != null) 
            {
                String[] array = line.split("\\,");
                desc = array[0];
                date = array[1];
                start = Integer.parseInt(array[2]);
                end = Integer.parseInt(array[3]);
                Event event = new Event(desc, date, start, end);
                list.add(event);
 
            }

            br.close();
            fr.close();
            System.out.println("Events successfully loaded.");

        } catch (IOException x) 
    	{
            System.out.println("No file to load from. Create some events and try again.");

        }
    		JFrame f3 = new JFrame("Month View");
    		dayInText2.setText("");
    		dayInText2.setText(m.getMonthName());
        for (Event e : list) 
        {
           dayInText2.append("\n" + e.toString());
        }
    		f3.add(dayInText2);
    		f3.setDefaultCloseOperation(f3.DISPOSE_ON_CLOSE);
    		f3.pack();
    		f3.setVisible(true);
    }

    /**
     * Creates buttons of to do list.
     * @return - to do list panel
     */
    public JPanel createDaysPanel() 
    {
    		final JTextField desc = new JTextField("Event description");
        final JTextField startTime = new JTextField("Start time");
        final JTextField endTime = new JTextField("End time");
        
        JPanel p1 = new JPanel();
        p1.add(desc);
        p1.add(startTime);
        p1.add(endTime);
        
        daysPanel.removeAll();
        daysPanel.setLayout(new BorderLayout());

        JButton quit = new JButton("Export");
        quit.addActionListener(new ActionListener() 
        {

            public void actionPerformed(ActionEvent e) 
            {
                saveEventsToFile();
            }

        });

        JButton Add = new JButton("Add");
        Add.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            		int start = Integer.parseInt(startTime.getText());
                int end = Integer.parseInt(endTime.getText());
                Event event = new Event(desc.getText(), cdate, start, end);
                m.getList().add(event);
                m.update();
                saveEventsToFile();
                m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt());
                m.previousMonth();
                m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt() );
                m.getNextMonth();
            }

        });

        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int index = daysJList.getSelectedIndex();
               
                if (index>= 0 && listsModel.get(index).getClass() == Event.class) 
                {
                    for (int i = 0; i < m.getList().size(); i++) 
                    {
                        if (m.getList().get(i).equals(listsModel.get(index))) 
                        {
                            m.getList().remove(i);
                            m.update();
                            saveEventsToFile();
                            m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt());
                            m.previousMonth();
                            m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt() );
                            m.getNextMonth();
                        }
                    }

                }
            }

        });
        
        JButton edit = new JButton("Edit");
        edit.addActionListener(new ActionListener() 
        {
        	@Override
        	public void actionPerformed(ActionEvent e)
        	{
        		int index = daysJList.getSelectedIndex();
        		if (index>= 0 && listsModel.get(index).getClass() == Event.class) 
        		{
                    for (int i = 0; i < m.getList().size(); i++) 
                    {
                        if (m.getList().get(i).equals(listsModel.get(index))) 
                        {
                        		Boolean w = false;
                        		if (desc.getText().equals("Event description"))
                        		{
                        			w = true;
                        		}
                        		if (w == false)
                        		{
                        			m.getList().get(i).ChangeTitle(desc.getText());
                        			saveEventsToFile();
                        			m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt());
                                    m.previousMonth();
                                    m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt() );
                                    m.getNextMonth();
                        		}
                        		Boolean w1 = false;
                        		if (startTime.getText().equals("Start time"))
                        		{
                        			w1 = true;
                        		}
                        		if(w1 == false)
                        		{
                        			m.getList().get(i).ChangeStart(Integer.parseInt(startTime.getText()));
                        			saveEventsToFile();
                        			m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt());
                                    m.previousMonth();
                                    m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt() );
                                    m.getNextMonth();
                        		}
                        		Boolean w2 = false;
                        		if (endTime.getText().equals("End time"))
                        		{
                        			w2 = true;
                        		}
                        		if (w2 == false)
                        		{
                        			m.getList().get(i).ChangeEnd(Integer.parseInt(endTime.getText()));
                        			saveEventsToFile();
                        			m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt());
                                    m.previousMonth();
                                    m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt() );
                                    m.getNextMonth();
                        		}
                            m.update();
                        }
                    }

                }
        	}
        	
        });
        
        JPanel p2 = new JPanel();
        p2.add(edit,BorderLayout.NORTH);
        p2.add(Add, BorderLayout.CENTER);
        p2.add(delete, BorderLayout.SOUTH);
        p2.add(quit, BorderLayout.EAST);
        
        daysPanel.add(createToDoList(), BorderLayout.NORTH);
        daysPanel.add(p2,BorderLayout.CENTER);
        daysPanel.add(p1, BorderLayout.SOUTH);

        return daysPanel;
    }
}
