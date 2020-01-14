

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Creates calendar frame and to-do list frame
 * @author chitsimrangill
 *
 */
public class Frame extends JFrame implements ChangeListener 
{
    private CalendarPanel cp;
    private DaysPanel dp;
    
    /**
     * Constructor
     * @param cp - calendar panel
     * @param dp - to-do list panel
     */
    public Frame(CalendarPanel cp, DaysPanel dp)
    {
    	    setLayout(new BorderLayout());
        this.cp = cp;
        add(cp, BorderLayout.CENTER);
        JLabel b3  = new JLabel("Today: " + dp.cdate );
        JButton monthView = new JButton("Month View");
        monthView.addActionListener(new ActionListener()
        		{
        			@Override
        			public void actionPerformed(ActionEvent e)
        			{
        				dp.getMonthView();
        				dp.list.clear();
        			}
        		});
        JPanel p6 = new JPanel();
        p6.setLayout(new BorderLayout());
        p6.add(b3, BorderLayout.WEST);
        p6.add(monthView, BorderLayout.EAST);
        add(p6, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
        JFrame lFrame = new JFrame();
        lFrame.setLayout(new BorderLayout());
        this.dp = dp;
		lFrame.add(dp, BorderLayout.CENTER);
		lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lFrame.pack();
		lFrame.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {          
    		cp.createDaysView();
    		repaint();
    }
        
        
}
