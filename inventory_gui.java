import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class inventory_gui extends JFrame {

    // Different sections of the inventory system
    private JPanel top = new JPanel();
    private JPanel center = new JPanel();
    private JPanel bottom = new JPanel();

    // Labels that will be used in the inventory system
    private JLabel addIDLabel = new JLabel();
    private JLabel colorLabel = new JLabel();
    private JLabel rLabel = new JLabel();
    private JLabel gLabel = new JLabel();
    private JLabel bLabel = new JLabel();
    private JLabel removeIDLabel = new JLabel();

    // Text fields that will be used in the inventory system
    private JTextField addIDTxt = new JTextField();
    private JTextField colourRTxt = new JTextField();
    private JTextField colourGTxt = new JTextField();
    private JTextField colourBTxt = new JTextField();
    private JTextField removeIDTxt = new JTextField();

    //buttons that will be used in the inventory system
    private JButton addBtn = new JButton();
    private JButton listBtn = new JButton();
    private JButton removeBtn = new JButton();
    private JButton sortBtn = new JButton();
    private JButton clearBtn = new JButton();

    private ArrayList<InventoryID> list = new ArrayList<>();

    inventory_gui(){
        //Setting up main window
        setTitle("Inventory IDs");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1200,600));
        setLayout(new BorderLayout());
        setResizable(true);
        setLocationRelativeTo(null);

        // Each JPanel Top, Center, and Bottom has their own methods so as to not congest this method with too be code and is easier to edit each panel
        setTop();
        setBottom();

        center.setPreferredSize(new Dimension(1200, 400));
        setCenter();

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        //
        pack();

        // Adding buttons. Likewise buttons will call upon methods so as to not congest this method and is easier to edit each button's logic
        addBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addBtnLogic();
                    }
                }
        );

        listBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        listBtnLogic();
                    }
                }
        );

        removeBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removeBtnLogic();
                    }
                }
        );

        sortBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sortBtnLogic();
                    }
                }
        );

        clearBtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clearBtnLogic();
                    }
                }
        );

    } //Sets up the frame, connections frame to logic and button of GUI

    public void setTop(){
        // Removing everything currently in the JPanel, resetting and redrawing
        top.removeAll();
        top.revalidate();
        top.repaint();
        top.setPreferredSize(new Dimension(1200, 100));
        top.setBackground(new Color(0, 102, 204));
        top.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));

        //Adding components to the Top JPanel
        addIDLabel.setText("Enter Six Digit ID Number ");
        addIDLabel.setForeground(Color.white);
        top.add(addIDLabel);

        addIDTxt.setText("");
        addIDTxt.setPreferredSize(new Dimension(100, 30));
        top.add(addIDTxt);

        //RGB Labels and TextFields Below
        colorLabel.setText("   Colour ");
        colorLabel.setForeground(Color.white);
        top.add(colorLabel);

        //RED
        rLabel.setText("  R");
        rLabel.setForeground(Color.white);
        top.add(rLabel);

        colourRTxt.setText("");
        colourRTxt.setPreferredSize(new Dimension(40, 30));
        top.add(colourRTxt);

        //GREEN
        gLabel.setText("G");
        gLabel.setForeground(Color.white);
        top.add(gLabel);

        colourGTxt.setText("");
        colourGTxt.setPreferredSize(new Dimension(40, 30));
        top.add(colourGTxt);

        //BLUE
        bLabel.setText("  B");
        bLabel.setForeground(Color.white);
        top.add(bLabel);

        colourBTxt.setText("");
        colourBTxt.setPreferredSize(new Dimension(40, 30));
        top.add(colourBTxt);

        //Remove ID
        removeIDLabel.setText("   Remove ID Number");
        removeIDLabel.setForeground(Color.white);
        top.add(removeIDLabel);

        removeIDTxt.setPreferredSize(new Dimension(100, 30));
        top.add(removeIDTxt);

    } // The top section of the GUI where it contains the textfield and labels

    public void setCenter(){
        center.removeAll();
        center.revalidate();
        center.repaint();

        center.setLayout(new GridLayout());

        // Create scroll pane
        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.WHITE);
        JScrollPane pane = new JScrollPane(listPanel);
        center.add(pane);

        // For each loop to run through list
        for  (InventoryID id : list){
            JPanel panel = new JPanel(); // Create new JPanel for each ID
            panel.setLayout(new GridLayout(1, 2, 10, 10));
            panel.setPreferredSize(new Dimension(1150, 100));
            panel.setBorder(new LineBorder(Color.lightGray)); // Use border to show separation of each ID entry
            panel.setBackground(Color.white);

            JLabel idLabel = new JLabel("   Inventory ID: " + id.getId()); //Get ID
            idLabel.setForeground(new Color(id.getR(), id.getG(), id.getB())); //Change color or ID
            panel.add(idLabel, 0); // Add label to panel and assign to first column

            JLabel rgbLabel = new JLabel("RGB: " + id.getRGB());
            rgbLabel.setForeground(new Color(id.getR(), id.getG(), id.getB()));
            panel.add(rgbLabel, 1);

            listPanel.add(panel);
        }

        listPanel.setLayout(new GridLayout(list.size(),1,10,10));

    } // The center of the GUI where inputs will be displayed

    public void setBottom(){
        bottom.removeAll();
        bottom.revalidate();

        // Adding relevant buttons to bottom JPanel

        bottom.setPreferredSize(new Dimension(1200, 100));
        bottom.setBackground(new Color(0, 102, 204));
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 30));

        addBtn.setText("Add ID");
        addBtn.setPreferredSize(new Dimension(120, 35));
        addBtn.setBackground(Color.LIGHT_GRAY);
        bottom.add(addBtn);

        listBtn.setText("Show IDs");
        listBtn.setPreferredSize(new Dimension(120, 35));
        listBtn.setBackground(Color.LIGHT_GRAY);
        bottom.add(listBtn);

        sortBtn.setText("Sort");
        sortBtn.setPreferredSize(new Dimension(120, 35));
        sortBtn.setBackground(Color.LIGHT_GRAY);
        bottom.add(sortBtn);

        removeBtn.setText("Remove ID");
        removeBtn.setPreferredSize(new Dimension(120, 35));
        removeBtn.setBackground(Color.LIGHT_GRAY);
        bottom.add(removeBtn);

        clearBtn.setText("Clear All");
        clearBtn.setPreferredSize(new Dimension(120, 35));
        clearBtn.setBackground(Color.LIGHT_GRAY);
        bottom.add(clearBtn);

    } // The bottom of the GUI where buttons will be at

    public void addBtnLogic(){
        String id = addIDTxt.getText();
        int negativeId,r,g,b;
        try{
            Integer.parseInt(id);
        } catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(null,"ID must be an integer value", "Error", JOptionPane.ERROR_MESSAGE);
            return; // So other errors will not show
        }

        negativeId = Integer.parseInt(addIDTxt.getText());
        if (negativeId < 0) {
            JOptionPane.showMessageDialog(null, "ID cannot contain negative numbers", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!(id.length() == 6)) {
            JOptionPane.showMessageDialog(null,"ID must be an integer of 6 digits", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        addIDTxt.setText("");




        try {
            r = Integer.parseInt(colourRTxt.getText());
            g = Integer.parseInt(colourGTxt.getText());
            b = Integer.parseInt(colourBTxt.getText());

            if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                throw new NumberFormatException();
            } else {
                list.add(new InventoryID(id, r, g, b));
                JOptionPane.showMessageDialog(null, "ID & RGB values saved");
                colourRTxt.setText("");
                colourGTxt.setText("");
                colourBTxt.setText("");
                return;
            }
        } catch (NumberFormatException exp) {
            list.add(new InventoryID(id));
            JOptionPane.showMessageDialog(null, "Invalid RGB or no RGB value.\n" + "ID: " + id + " has been saved and color is set to black");
        }

        colourRTxt.setText("");
        colourGTxt.setText("");
        colourBTxt.setText("");

        } //Error catching when input is detected

    public void listBtnLogic(){
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null, "List is empty.");
        }
        setTop(); // Calling top method so fills will be cleared
        setCenter();
    }

    public void removeBtnLogic(){
        String id = removeIDTxt.getText();
        if (id.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter an ID to remove", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // For loop to find ID to be removed
        int count = 0;
        for (int i = 0; i < list.size(); i++ ) {
            if (id.equals(list.get(i).getId())){
                count++;
                list.remove(i);
                i--;
            }
        }

        removeIDTxt.setText("");

        if (count == 0) {
            JOptionPane.showMessageDialog(null, "No matching record for ID: " + id);
        } else {
            JOptionPane.showMessageDialog(null, id + " Removed");
        }

        removeIDTxt.setText("");
    }

    public void sortBtnLogic(){
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null, "List is empty.");
        }
        Collections.sort(list);
        setCenter();
    }

    public void clearBtnLogic(){
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null, "List is empty.");
            return;
        }
        list.clear();
        JOptionPane.showMessageDialog(null, "List cleared");
        setCenter();
    }

    public static void main(String[] args) {
        new inventory_gui();
    }
}

class InventoryID implements Comparable<InventoryID>{

    private String id;
    private int r, g, b;

    InventoryID(String id){
        this.id = id;
        r = 0;
        g = 0;
        b = 0;
    }

    InventoryID(String id, int r, int g, int b){
        this.id = id;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public String getId(){
        return id;
    }

    public int getR(){
        return r;
    }

    public int getG(){
        return g;
    }

    public int getB(){
        return b;
    }

    public String getRGB(){
        return r + "," + g + "," + b;
    }

    @Override
    public int compareTo(InventoryID o) {
        int thisID = Integer.parseInt(this.id);
        int otherID = Integer.parseInt(o.id);
        if (thisID < otherID) {
            return -1;
        } else if (thisID == otherID) {
            return 0;
        }
        return 1;
    }
}
