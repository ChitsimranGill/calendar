

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Creates Calendar Panel of calendar
 * @author chitsimrangill
 *
 */
public class CalendarPanel extends JPanel implements ChangeListener 
{
    private Border normal = new LineBorder(Color.BLACK, 0);
    private Border highlighted = new LineBorder(Color.ORANGE, 1);
    private Model m;
    private ArrayList<JButton> daysButtons = new ArrayList<>();
    private JPanel daysGridPanel = new JPanel();
    private JPanel monthsPanel = new JPanel();
    private JTextPane daysPane = new JTextPane();
    private int maximumDays;
    public JLabel monthsLabel = new JLabel();
    private JLabel weekLabel = new JLabel();
    private JPanel topPanel = new JPanel();
    private JButton previous = new JButton();
    private JButton next = new JButton();
    private JButton nY = new JButton();
    private JButton pY = new JButton();
    private JPanel p3 = new JPanel();

    /**
     * Constructor
     * @param model
     */
    public CalendarPanel(Model model) 
    {
        super();
        this.m = model;
        maximumDays = m.getMaximumDays();
        createPreviousNextButtons();
        createPreviousNextYearButtons();    
        createDaysView();
        add(createMonthPanel());  
        setLayout(new FlowLayout());
    }

    /**
     * Creates buttons to change month
     */
    public void createPreviousNextButtons()
    {
        previous.setText("<<");
        next.setText(">>");
        previous.addActionListener(new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
              m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt());
              m.previousMonth();
          }
      });

      next.addActionListener(new ActionListener() 
      {
          public void actionPerformed(ActionEvent e) 
          {
              m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, m.getDayInt() );
              m.getNextMonth();
          }
      });
  }
    
    /**
     * Creates months to change year
     */
    public void createPreviousNextYearButtons()
    {
        pY.setText("Previous Year");
        nY.setText("Next Year");

      pY.addActionListener(new ActionListener() 
      {
          public void actionPerformed(ActionEvent e) 
          {
              m.getGC().set(m.getYearInt()-1, m.getMonthInt(), m.getDayInt());
              m.previousMonth();
          }
      });

      nY.addActionListener(new ActionListener() 
      {
          public void actionPerformed(ActionEvent e) 
          {
              m.getGC().set(m.getYearInt()+1, m.getMonthInt()-2, m.getDayInt());
              m.getNextMonth();
          }
      });
  }
    
    /**
     * Creates days view
     * @return - to-do list
     */
    public JPanel createDayListView() 
    {
        JPanel daysView = new JPanel();
        daysView.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JScrollPane scroll = new JScrollPane(daysView);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return daysView;
    }

   /**
    * Creates month panel of calendar
    * @return - month panel
    */
    public JPanel createMonthPanel() 
    {
        daysButtons.clear();
        maximumDays = m.getMaximumDays();
        String title = "                                        "+ m.getMonthName() + " " + m.getYear();
        monthsLabel.setText(title);
        weekLabel.setText("         S                M                T                 W                T                  F                S");

        p3.setLayout(new BorderLayout());
        p3.add(nY,BorderLayout.NORTH);
        p3.add(pY,BorderLayout.SOUTH);
        p3.add(monthsLabel, BorderLayout.WEST);
        
        topPanel.removeAll();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(p3,BorderLayout.CENTER);
        topPanel.add(previous, BorderLayout.WEST);
        topPanel.add(next, BorderLayout.EAST);

        monthsPanel.setLayout(new BorderLayout());
        monthsPanel.add(topPanel, BorderLayout.NORTH);
        monthsPanel.add(weekLabel, BorderLayout.CENTER);
        monthsPanel.add(createDaysView(), BorderLayout.SOUTH);
        
        return monthsPanel;
    }

    /**
     * Creates days view
     * @return - day panel
     */
    public JPanel createDaysView() 
    {
        daysButtons.clear();
        daysGridPanel.removeAll();
        daysGridPanel.setLayout(new GridLayout(0, 7));

        for (int i = 1; i <= maximumDays; i++) 
        {
            JButton day = new JButton(Integer.toString(i));
            final int date = i;
            day.setBackground(Color.WHITE);
            daysButtons.add(day);
            day.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    highlightedButton(date);
                    m.getGC().set(m.getYearInt(), m.getMonthInt() - 1, date);
                    m.update();
                }
            });
            
        }
        
        for (int j = 1; j < m.getDayOfWeek(); j++) 
        {
        		if (m.getMaximumDays()==31)
        		{
            JButton filler = new JButton(Integer.toString(31-m.getDayOfWeek()+j));
            filler.setEnabled(false);
            daysGridPanel.add(filler);
        		}
        		else if (m.getMaximumDays()==30)
        		{
        			JButton filler = new JButton(Integer.toString(32-m.getDayOfWeek()+j));
                    filler.setEnabled(false);
                    daysGridPanel.add(filler);
        		}
        		else
        		{
        			JButton filler = new JButton(Integer.toString(32-m.getDayOfWeek()+j));
                    filler.setEnabled(false);
                    daysGridPanel.add(filler);
        		}
        }
        
        for (JButton b : daysButtons) 
        {
            int buttonNumber = Integer.parseInt(b.getText());                   
            if (buttonNumber == (m.getDayInt())) 
            {
                b.setBorder(highlighted);
            }
            else if(m.hasEvent(buttonNumber))
            {
            b.setBorder(new LineBorder(Color.BLUE, 1 ));
            }
            else {b.setBorder(normal);
            }
            daysGridPanel.add(b);
        }
        
        for (int j = 1; j < 8-m.getDayLast(); j++)
        {
        		if (m.getMaximumDays()==31)
        		{
            JButton filler = new JButton(Integer.toString(j));
            filler.setEnabled(false);
            daysGridPanel.add(filler);
        		}
        		else if (m.getMaximumDays()==30)
        		{
        			JButton filler = new JButton(Integer.toString(j));
                    filler.setEnabled(false);
                    daysGridPanel.add(filler);
        		}
        		else
        		{
        			JButton filler = new JButton(Integer.toString(j));
                    filler.setEnabled(false);
                    daysGridPanel.add(filler);
        		}
        }
        
        return daysGridPanel;
    }

    /**
     * Highlights buttons
     * @param i - button that needs to be highlighted
     */
    public void highlightedButton(int i) 
    {
        for (JButton b : daysButtons) 
        {
            b.setBorder(normal);
        }
        daysButtons.get(i-1).setBorder(highlighted);

    }

    @Override
    public void stateChanged(ChangeEvent e) 
    {
        createMonthPanel();
        repaint();
    }

}