import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;

@Manifest(authors = { "ICyanide" }, description = "Crafts runes at the Air Alter", name = "CyanAirAlterAIO")
public class main extends ActiveScript implements PaintListener {
	
	public static Timer timer = new Timer(0);
	public boolean guiOpen = true;
	private Tree container = null;
	private List<Node> jobs = new ArrayList<Node>();
	

	public void onStart() {
		
		jobs.add(new Banking());
		jobs.add(new Traversing());
		jobs.add(new Crafting());
		container = new Tree(jobs.toArray((new Node[jobs.size()])));
		
		 mainGUI g = new mainGUI();       // starts the GUI
		 g.setVisible(true);
		 while(g.isActive()) sleep(500);  //Sleeps while GUI is Open
	}
	
	public void onStop() {
		System.out.println("");
	}
	
	@Override
	public int loop() {
		if(guiOpen == false) {
			if(container != null) {
				final Node job = container.state();
				if(job != null) {
					container.set(job);
					getContainer().submit(job);
					job.join();
				}
			}
		}
		return Random.nextInt(10, 50);
	}

	@Override
	public void onRepaint(Graphics g1) {
		final Graphics2D g = (Graphics2D) g1;
		final Point p = Mouse.getLocation();
		String currentStatus = Variables.status;
		String currentsleepstatus = Variables.dynamicSleepStatus;
		
		//my black box
		g.setColor(Color.black);
		g.fillRect(7, 395, 505, 128);
		
		//info graphics
		g.setColor(Color.green);
		g.drawString(String.format("Time Running: %s", timer.toElapsedString()), 15, 410);
		g.drawString("Status: "+ currentStatus, 15, 425);
		g.drawString("Crafting: "+ Variables.CRAFTEDRUNE_ID, 15, 440);
		g.drawString("Runes: "+ Variables.ELEMENTALRUNE_ID, 15, 455);
		g.drawString("Essence: "+ Variables.ESSENCE_ID, 15, 470);
		g.drawString("In Dynamic Sleep: "+ currentsleepstatus, 15, 485);
		g.drawString("Animation ID: "+ Players.getLocal().getAnimation(), 15, 500);
		
		// mouse graphics
				g.setColor(Mouse.isPressed() ? Color.BLUE : Color.CYAN);
				g.fillOval(p.x - 4, p.y - 4, 8, 8);
				g.setColor(Color.BLACK);
				g.drawOval(p.x - 4, p.y - 4, 8, 8);
				g.setColor(Color.RED);
				g.fillOval(p.x - 2, p.y - 2, 4, 4);
	}

	@SuppressWarnings("serial")
	class mainGUI extends JFrame {
		public mainGUI() {
			initComponents();
		}

		private void startButtonActionPerformed(ActionEvent e) {
			String runeType = comboRuneType.getSelectedItem().toString();
			
			if(runeType.equals("Air")) {
				Variables.TALISMAN_ID = 0; 
				Variables.ELEMENTALRUNE_ID = 556;
				Variables.CRAFTEDRUNE_ID = 556;
				Variables.ESSENCE_ID = 1436;
			} else if(runeType.equals("Dust")) {
				Variables.TALISMAN_ID = 1440;
				Variables.ELEMENTALRUNE_ID = 557;
				Variables.ESSENCE_ID = 7936;
				Variables.CRAFTEDRUNE_ID = 4696;
			} else if(runeType.equals("Mist")) {
				Variables.TALISMAN_ID = 1444;
				Variables.ELEMENTALRUNE_ID = 555;
				Variables.ESSENCE_ID = 7936;
				Variables.CRAFTEDRUNE_ID = 4695;
			} else if(runeType.equals("Smoke")) {
				Variables.TALISMAN_ID = 1442;
				Variables.ELEMENTALRUNE_ID = 554;
				Variables.ESSENCE_ID = 7936;
				Variables.CRAFTEDRUNE_ID = 4697;
			}
			
			
			this.setVisible(false);
			guiOpen = false;
		}

		private void initComponents() {
			// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
			// Generated using JFormDesigner Evaluation license - Aaron Boutilier
			tabbedPane1 = new JTabbedPane();
			panel1 = new JPanel();
			label2 = new JLabel();
			comboRuneType = new JComboBox<>();
			label3 = new JLabel();
			label13 = new JLabel();
			label14 = new JLabel();
			label15 = new JLabel();
			label16 = new JLabel();
			startButton = new JButton();
			label17 = new JLabel();
			panel2 = new JPanel();
			label4 = new JLabel();
			label5 = new JLabel();
			label6 = new JLabel();
			label7 = new JLabel();
			label8 = new JLabel();
			label9 = new JLabel();
			label10 = new JLabel();
			label11 = new JLabel();
			label12 = new JLabel();

			//======== this ========
			setTitle("CyanAirAlter AIO");
			setResizable(false);
			Container contentPane = getContentPane();

			//======== tabbedPane1 ========
			{

				//======== panel1 ========
				{
					panel1.setBackground(Color.black);
					panel1.setForeground(Color.cyan);

					//---- label2 ----
					label2.setText("Rune Type:");
					label2.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label2.setForeground(Color.cyan);

					//---- comboRuneType ----
					comboRuneType.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
					comboRuneType.setModel(new DefaultComboBoxModel<>(new String[] {
						"Air",
						"Dust",
						"Mist",
						"Smoke"
					}));

					//---- label3 ----
					label3.setText("Starting Information:");
					label3.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label3.setForeground(Color.cyan);

					//---- label13 ----
					label13.setText("-Start in Varrock west bank");
					label13.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label13.setForeground(Color.cyan);

					//---- label14 ----
					label14.setText("-Start with an empty Inventory");
					label14.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label14.setForeground(Color.cyan);

					//---- label15 ----
					label15.setText("-Start wearing an Air Tiara");
					label15.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label15.setForeground(Color.cyan);

					//---- label16 ----
					label16.setText("-Have Essences(Pure for mixtures)");
					label16.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label16.setForeground(Color.cyan);

					//---- startButton ----
					startButton.setText("Start");
					startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
					startButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							startButtonActionPerformed(e);
						}
					});

					//---- label17 ----
					label17.setText("-Have Binding Necklaces(for mixtures)");
					label17.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label17.setForeground(Color.cyan);

					GroupLayout panel1Layout = new GroupLayout(panel1);
					panel1.setLayout(panel1Layout);
					panel1Layout.setHorizontalGroup(
						panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(panel1Layout.createParallelGroup()
									.addGroup(panel1Layout.createSequentialGroup()
										.addComponent(label2)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(comboRuneType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(label3)
									.addGroup(panel1Layout.createSequentialGroup()
										.addGap(10, 10, 10)
										.addGroup(panel1Layout.createParallelGroup()
											.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
											.addComponent(label14)
											.addComponent(label15)
											.addComponent(label16)
											.addComponent(label13)
											.addComponent(label17))))
								.addContainerGap(20, Short.MAX_VALUE))
					);
					panel1Layout.setVerticalGroup(
						panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(18, 18, 18)
								.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(label2)
									.addComponent(comboRuneType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(label3)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label13)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label14)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label15)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label16)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label17)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(startButton)
								.addGap(20, 20, 20))
					);
				}
				tabbedPane1.addTab("Settings ", panel1);


				//======== panel2 ========
				{
					panel2.setBackground(Color.black);
					panel2.setForeground(Color.cyan);

					//---- label4 ----
					label4.setText("Mist Rune Requirements:");
					label4.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label4.setForeground(Color.blue);

					//---- label5 ----
					label5.setText("-Water Talismans");
					label5.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label5.setForeground(Color.blue);

					//---- label6 ----
					label6.setText("-Water Runes");
					label6.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label6.setForeground(Color.blue);

					//---- label7 ----
					label7.setText("Dust Rune Requirements:");
					label7.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label7.setForeground(new Color(102, 51, 0));

					//---- label8 ----
					label8.setText("-Earth Talismans");
					label8.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label8.setForeground(new Color(102, 51, 0));

					//---- label9 ----
					label9.setText("-Earth Runes");
					label9.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label9.setForeground(new Color(102, 51, 0));

					//---- label10 ----
					label10.setText("Smoke Rune Requirements:");
					label10.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label10.setForeground(Color.red);

					//---- label11 ----
					label11.setText("-Fire Talismans");
					label11.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label11.setForeground(Color.red);

					//---- label12 ----
					label12.setText("-Fire Runes");
					label12.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
					label12.setForeground(Color.red);

					GroupLayout panel2Layout = new GroupLayout(panel2);
					panel2.setLayout(panel2Layout);
					panel2Layout.setHorizontalGroup(
						panel2Layout.createParallelGroup()
							.addGroup(panel2Layout.createSequentialGroup()
								.addGroup(panel2Layout.createParallelGroup()
									.addGroup(panel2Layout.createSequentialGroup()
										.addContainerGap()
										.addComponent(label10))
									.addGroup(panel2Layout.createSequentialGroup()
										.addGap(185, 185, 185)
										.addGroup(panel2Layout.createParallelGroup()
											.addComponent(label12)
											.addComponent(label11)))
									.addGroup(panel2Layout.createSequentialGroup()
										.addContainerGap()
										.addGroup(panel2Layout.createParallelGroup()
											.addComponent(label4)
											.addGroup(panel2Layout.createSequentialGroup()
												.addGap(180, 180, 180)
												.addGroup(panel2Layout.createParallelGroup()
													.addComponent(label5)
													.addComponent(label6)))))
									.addGroup(panel2Layout.createSequentialGroup()
										.addContainerGap()
										.addGroup(panel2Layout.createParallelGroup()
											.addComponent(label7)
											.addGroup(panel2Layout.createSequentialGroup()
												.addGap(176, 176, 176)
												.addGroup(panel2Layout.createParallelGroup()
													.addComponent(label9)
													.addComponent(label8))))))
								.addContainerGap(13, Short.MAX_VALUE))
					);
					panel2Layout.setVerticalGroup(
						panel2Layout.createParallelGroup()
							.addGroup(panel2Layout.createSequentialGroup()
								.addGap(6, 6, 6)
								.addComponent(label7)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label8)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label9)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label4)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label5)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label6)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label10)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label11)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label12)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					);
				}
				tabbedPane1.addTab("Rune Info ", panel2);

			}

			GroupLayout contentPaneLayout = new GroupLayout(contentPane);
			contentPane.setLayout(contentPaneLayout);
			contentPaneLayout.setHorizontalGroup(
				contentPaneLayout.createParallelGroup()
					.addComponent(tabbedPane1)
			);
			contentPaneLayout.setVerticalGroup(
				contentPaneLayout.createParallelGroup()
					.addComponent(tabbedPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
			);
			pack();
			setLocationRelativeTo(getOwner());
			// JFormDesigner - End of component initialization  //GEN-END:initComponents
		}

		// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
		// Generated using JFormDesigner Evaluation license - Aaron Boutilier
		private JTabbedPane tabbedPane1;
		private JPanel panel1;
		private JLabel label2;
		private JComboBox<String> comboRuneType;
		private JLabel label3;
		private JLabel label13;
		private JLabel label14;
		private JLabel label15;
		private JLabel label16;
		private JButton startButton;
		private JLabel label17;
		private JPanel panel2;
		private JLabel label4;
		private JLabel label5;
		private JLabel label6;
		private JLabel label7;
		private JLabel label8;
		private JLabel label9;
		private JLabel label10;
		private JLabel label11;
		private JLabel label12;
		// JFormDesigner - End of variables declaration  //GEN-END:variables
	}
	
}

