package project5;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class builder extends JFrame{

		private static final long serialVersionUID = 1L;
		private JPanel panel0;
		
		private static JPanel panel1;
		private JTextField HammingDist;
		private JTextField distanceField0;
		private JLabel distance0;
		private JTextField distanceField1;
		private JLabel distance1;
		private JTextField distanceField2;
		private JLabel distance2;
		private JTextField distanceField3;
		private JLabel distance3;
		private JTextField distanceField4;
		private JLabel distance4;
		private JSlider slider1;
		private JButton showStation;
		private JTextArea stationList;
		private JLabel compare;
		private JComboBox<String> stationDropDown;
		private JButton calculate;
		private JLabel hammingDistanceLabel;
		
		private JButton addStation;
		private JTextField station;
		private JScrollPane scrollPane1;
		private static ArrayList<String> STID;
		private BufferedWriter output;
		
		ImageIcon image = new ImageIcon("1.png");
		ImageIcon image1 = new ImageIcon("2.jpg");
		ImageIcon image2 = new ImageIcon("3.jpg");
		ImageIcon image3 = new ImageIcon("4.png");

		
		private static int[] hammingDistance;
		private JLabel label2;

		public static void readFile(String filename) throws FileNotFoundException {
			Scanner input = new Scanner(new File(filename));
			STID = new ArrayList<>();
			
			while (input.hasNext()) {
				String next = input.nextLine();
				STID.add(next);

			}
			input.close();
		}


		private void setFields() {
			
			hammingDistanceLabel = new JLabel("Enter the Hamming Distance: ");

			// text field to update slider values from initialized value of 1. 
			HammingDist = new JTextField(13);
			HammingDist.setText("1");
			HammingDist.setEditable(false);
			
			// Uses button to show the stations within requirements
			showStation = new JButton("Show Station");
			scrollPane1 = new JScrollPane();
			
			// Calc button to show how many stations at each hamming dist
			calculate = new JButton("Calculate HD");
			distance0 = new JLabel("Distance 0:");
			distanceField0 = new JTextField(11);
			distanceField0.setEditable(false);
			distanceField1 = new JTextField(11);
			distanceField1.setEditable(false);
			distanceField2 = new JTextField(11);
			distanceField2.setEditable(false);
			distance4 = new JLabel("Distance 4:");
			distanceField4 = new JTextField(11);
			distanceField4.setEditable(false);

			// creating slider with minor tick spacing every 1 point for each hamming dist
			slider1 = new JSlider(SwingConstants.HORIZONTAL, 1, 4, 1);
			
			slider1.setMinorTickSpacing(1);
			slider1.setPaintTicks(true);

			// labeling ticks with a hashtable containing values for each tick
			Hashtable<Integer, JLabel> tickValues = new Hashtable<>();
			
			tickValues.put(1, new JLabel("1"));
			tickValues.put(2, new JLabel("2"));
			tickValues.put(3, new JLabel("3"));
			tickValues.put(4, new JLabel("4"));
			
			slider1.setLabelTable(tickValues);
			slider1.setPaintLabels(true);

			// Action listener to use the showStation button to calc the hamming distance and set the text in the text area
			showStation.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					stationList.setText("");
					ArrayList<String> aList = new ArrayList<>();
					aList = calculateHammingDistance(stationDropDown.getSelectedItem().toString(), slider1.getValue(), STID);
					for (String c : aList) {
						stationList.append(c + "\n");
					}

				}

			});

			// ACtion listener to use calculateHD butotn to return the Hamming Distance array in the textboxes
			calculate.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					calculateHammingDistance(stationDropDown.getSelectedItem().toString(), slider1.getValue(), STID);
					
					for (int i : hammingDistance) {
						System.out.println(i);
					}
					
					distanceField0.setText(String.valueOf(hammingDistance[0]));
					distanceField1.setText(String.valueOf(hammingDistance[1]));
					distanceField2.setText(String.valueOf(hammingDistance[2]));
					distanceField3.setText(String.valueOf(hammingDistance[3]));
					distanceField4.setText(String.valueOf(hammingDistance[4]));
					
					//shows random image of school logos from 4 different schools for my creative portion
					double random = Math.random();
					random = random * 10;
					if(random * 10 >= 0 && random <= 2) {
					label2.setIcon(image);
					} else if(random >= 3 && random <= 6) {
						label2.setIcon(image1);
					} else if (random >= 7 && random <= 8) {
						label2.setIcon(image2);
					} else if(random >= 9 && random <= 10) {
						label2.setIcon(image3);
					}
				}
			});
			
			slider1.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent arg0) {
					HammingDist.setText(String.valueOf(slider1.getValue()));
				}
			});
		}
		
		@SuppressWarnings("unchecked")
		public builder() {
			super("Hamming Distance");
			setSize(900, 800);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setFields();
			stationDropDown = new JComboBox((STID.toArray()));
			panel0 = new JPanel(new BorderLayout());
			panel1 = new JPanel();
			JPanel panel3 = new JPanel();
			distance1 = new JLabel("Distance 1:");
			distance2 = new JLabel("Distance 2:");
			distance3 = new JLabel("Distance 3:");
			distanceField3 = new JTextField(11);
			distanceField3.setEditable(false);
			
			panel0.add(panel1, BorderLayout.CENTER);
			compare = new JLabel("Compare with:");
			label2 = new JLabel("");
			
			GroupLayout gl_panel1 = new GroupLayout(panel1);
			gl_panel1.setHorizontalGroup(
				gl_panel1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel1.createSequentialGroup()
						.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel1.createSequentialGroup()
								.addGap(22)
								.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel1.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_panel1.createSequentialGroup()
										.addComponent(compare)
										.addGap(53)
										.addComponent(stationDropDown, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
									.addComponent(calculate)
									.addGroup(gl_panel1.createSequentialGroup()
										.addComponent(hammingDistanceLabel)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(HammingDist, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
									.addComponent(panel3, 0, 0, Short.MAX_VALUE)
									.addComponent(showStation)
									.addGroup(gl_panel1.createSequentialGroup()
										.addGap(28)
										.addComponent(slider1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(label2, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)));
			
			gl_panel1.setVerticalGroup(
				gl_panel1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel1.createSequentialGroup()
						.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel1.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel1.createParallelGroup(Alignment.BASELINE)
									.addComponent(hammingDistanceLabel)
									.addComponent(HammingDist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(7)
								.addComponent(slider1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(showStation)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel1.createParallelGroup(Alignment.BASELINE)
									.addComponent(compare)
									.addComponent(stationDropDown, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addComponent(calculate)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(panel3, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
							.addComponent(label2, GroupLayout.PREFERRED_SIZE, 749, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

			// The text area to show results of show stations button click
			stationList = new JTextArea(20, 24);
			scrollPane1.setViewportView(stationList);

			addStation = new JButton("Add Station");
			station = new JTextField(4);

			// Addstation action listener checks if station is already in array, if not makes uppercase then adds to array and sorts
			addStation.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						output = new BufferedWriter(new FileWriter("Mesonet.txt"));
						if (!STID.contains(station.getText())) {
							STID.add(station.getText());
							Collections.sort(STID, new Comparator<String>() {
								@Override
								public int compare(String s1, String s2) {
									return s1.compareToIgnoreCase(s2);
								}
							});
						}
						for (String str : STID) {
							output.write(str.toUpperCase() + "\n");
						}
						output.close();
						readFile("Mesonet.txt");
						stationDropDown.setModel(new DefaultComboBoxModel<String>((String[]) STID.toArray()));
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			});
			JPanel panel4 = new JPanel();

			panel4.add(addStation);
			panel4.add(station);
			GroupLayout gl_panel3 = new GroupLayout(panel3);
			gl_panel3.setHorizontalGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel3.createSequentialGroup().addGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel3.createSequentialGroup().addGap(20)
									.addGroup(gl_panel3.createParallelGroup(Alignment.LEADING).addComponent(distance0)
											.addComponent(distance1)
											.addGroup(gl_panel3.createParallelGroup(Alignment.TRAILING).addComponent(distance4)
													.addComponent(distance3).addComponent(distance2)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
											.addComponent(distanceField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(distanceField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(distanceField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(distanceField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(distanceField0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
													GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel3.createSequentialGroup().addContainerGap().addComponent(panel4,
									GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(32, Short.MAX_VALUE)));
			gl_panel3.setVerticalGroup(gl_panel3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel3
					.createSequentialGroup().addGap(5)
					.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE).addComponent(distance0).addComponent(distanceField0,
							GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE).addComponent(distance1).addComponent(distanceField1,
							GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE).addComponent(distance2).addComponent(distanceField2,
							GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE).addComponent(distance3).addComponent(distanceField3,
							GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE).addComponent(distance4).addComponent(distanceField4,
							GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(46, Short.MAX_VALUE)));
			panel3.setLayout(gl_panel3);
			panel1.setLayout(gl_panel1);

			getContentPane().add(panel0);

			setVisible(true);
		}
		
		// Now basic HammDist calculation from previous projects / labs
		public ArrayList<String> calculateHammingDistance(String name, int HD, ArrayList<String> STID) {
			ArrayList<String> equalStations = new ArrayList<String>();
			hammingDistance = new int[] { 0, 0, 0, 0, 0 };
			char[] string1 = name.toCharArray();
			for (int city2 = 0; city2 < STID.size(); city2++) {
				int hammDist2 = 0;
				char[] string2 = STID.get(city2).toCharArray();
				for (int index = 0; index < string1.length; index++) {
					if (string1[index] != (string2[index])) {
						hammDist2++;
					}
				}
				switch (hammDist2) {
				case 0:
					hammingDistance[0]++;
					break;
				case 1:
					hammingDistance[1]++;
					break;
				case 2:
					hammingDistance[2]++;
					break;
				case 3:
					hammingDistance[3]++;
					break;
				case 4:
					hammingDistance[4]++;
					break;
				}
				if (hammDist2 == HD) {
					equalStations.add(STID.get(city2));
				}
			}
			return equalStations;
		}
		
		public static void main(String[] args) throws FileNotFoundException {
			readFile("Mesonet.txt");
			new builder();
		}
	}



