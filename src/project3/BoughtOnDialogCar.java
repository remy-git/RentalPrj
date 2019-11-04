package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class BoughtOnDialogCar extends JDialog implements ActionListener {

    private JTextField txtCarName;
    private JTextField txtDate;
    private JTextField txtTurbo;
    private JTextField txtTrimPackage;
    private JTextField txtCost;
    private JButton okButton;
    private JButton cancelButton;
    private int closeStatus;
    private Auto auto;
    static final int OK = 0;
    static final int CANCEL = 1;
    JPanel carPanel = new JPanel();

    /*********************************************************
     Instantiate a Custom Dialog as 'modal' and wait for the
     user to provide data and click on a button.

     @param parent reference to the JFrame application
     @param auto an instantiated object to be filled with data
     *********************************************************/

    public BoughtOnDialogCar(JFrame parent, Auto auto) {
        // call parent and create a 'modal' dialog
        super(parent, true);

        this.auto = auto;
        setTitle("Bought Car");
        closeStatus = CANCEL;
        setSize(400,200);

        // prevent user from closing window
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // instantiate and display two text fields
        txtCarName = new JTextField("Honda Civic",30);
        txtDate = new JTextField(15);
        txtTrimPackage = new JTextField("LT",15);
        txtCost = new JTextField("10100.00", 15);
        txtTurbo = new JTextField("Turbo", 15);

        txtDate.setText("10/17/2018");

        carPanel.setLayout(new GridLayout(7,2));

        getContentPane().add(carPanel, BorderLayout.CENTER);

        // Instantiate and display two buttons
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        carPanel.add(new JLabel(""));
        carPanel.add(new JLabel(""));
        carPanel.add(new JLabel(""));

        carPanel.add(new JLabel("Name of Car: "));
        carPanel.add(txtCarName);
        carPanel.add(new JLabel("bought on Date: "));
        carPanel.add(txtDate);
        carPanel.add(new JLabel("Trim Package"));
        carPanel.add(txtTrimPackage);
        carPanel.add(new JLabel("Turbo"));
        carPanel.add(txtTurbo);
        carPanel.add(new JLabel("Amount Paid for"));
        carPanel.add(txtCost);

        setVisible (true);
    }

    /**************************************************************
     Respond to either button clicks
     @param e the action event that was just fired
     **************************************************************/
    public void actionPerformed(ActionEvent e) {

        //JButton button = (JButton) e.getSource();
        Object source = e.getSource();

       // System.out.println(combobox.getSelectedIndex());

        // if OK clicked the fill the object
        if (source == okButton) {
            // save the information in the object
            closeStatus = OK;
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            GregorianCalendar temp = new GregorianCalendar();


                Date d = null;
                try {
                    d = df.parse(txtDate.getText());
                    temp.setTime(d);

                } catch (ParseException e1) {
//                  Do some thing good, what I am not sure.
                }
                auto.setBoughtOn(temp);
                auto.setAutoName(txtCarName.getText());
                ((Car) auto).setTrim(txtTrimPackage.getText());
                auto.setBoughtCost(Double.parseDouble(txtCost.getText()));



         }

         // make the dialog disappear
        dispose();
    }

    /**************************************************************
     Return a String to let the caller know which button
     was clicked

     @return an int representing the option OK or CANCEL
     **************************************************************/
    public int getCloseStatus(){
        return closeStatus;
    }
}