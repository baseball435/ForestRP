package com.client.frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.client.Forest;
import com.common.image.ImageLoader;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame implements ActionListener, KeyListener {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField username;
	public JLabel message;
	private JButton btnRegister;
	private JButton btnLogin;
	private JPasswordField password;

	private Color semiTransparent = new Color(0, 0, 0, 99);

	private BufferedImage backGround = ImageLoader.loadBufferedImage("/loginBG.jpg");

	public LoginFrame() {
		super();
		// INSTANCE = this;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel(new ImageIcon(backGround));
		setContentPane(label);
		initGUI();
		setIconImage(ImageLoader.loadImage("/tray.PNG"));

		if (Forest.server == null) {
			message.setText("Failed to connect");
		}
		setVisible(true);
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Forest RP - Login");
			GroupLayout thisLayout = new GroupLayout(getContentPane());
			getContentPane().setLayout(thisLayout);
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Username:");
				jLabel1.setForeground(Color.white);
				jLabel1.setBackground(semiTransparent);

			}
			{
				username = new JTextField();
				username.addKeyListener(this);
			}
			{
				btnLogin = new JButton(new ImageIcon(
						ImageLoader.loadImage("/login/login.png")));
				btnLogin.setRolloverIcon(new ImageIcon(ImageLoader
						.loadImage("/login/loginhover.png")));

				btnLogin.setContentAreaFilled(false);
				btnLogin.setBorder(null);
			}
			{
				btnRegister = new JButton(new ImageIcon(
						ImageLoader.loadImage("/login/register.png")));
				btnRegister.setRolloverIcon(new ImageIcon(ImageLoader
						.loadImage("/login/registerhover.png")));
				btnRegister.setContentAreaFilled(false);
				btnRegister.setBorder(null);
			}
			{
				message = new JLabel();
				message.setForeground(Color.YELLOW);
				message.setBackground(semiTransparent);
				message.setText("Forest RP");
				message.setFont(new java.awt.Font("Harrington", 1, 14));
				message.setHorizontalAlignment(SwingConstants.CENTER);
				message.setOpaque(true);
			}
			{
				jLabel2 = new JLabel();
				jLabel2.setText("Password:");
				jLabel2.setForeground(Color.white);
				jLabel2.setBackground(semiTransparent);

			}
			{
				password = new JPasswordField();
				password.setEchoChar('*');
				password.addKeyListener(this);
			}

			thisLayout.setVerticalGroup(thisLayout
					.createSequentialGroup()
					.addContainerGap(34, 34)
					.addComponent(message, GroupLayout.PREFERRED_SIZE, 38,
							GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addGroup(
							thisLayout
									.createParallelGroup(
											GroupLayout.Alignment.BASELINE)
									.addComponent(username,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(jLabel1,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE, 18,
											GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(
							thisLayout
									.createParallelGroup(
											GroupLayout.Alignment.BASELINE)
									.addComponent(password,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(jLabel2,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE, 18,
											GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(
							thisLayout
									.createParallelGroup(
											GroupLayout.Alignment.BASELINE)
									.addComponent(btnLogin,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE, 45,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(btnRegister,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE))
					.addContainerGap(76, 76));
			thisLayout
					.setHorizontalGroup(thisLayout
							.createSequentialGroup()
							.addContainerGap(101, 101)
							.addGroup(
									thisLayout
											.createParallelGroup()
											.addGroup(
													GroupLayout.Alignment.LEADING,
													thisLayout
															.createSequentialGroup()
															.addComponent(
																	btnLogin,
																	GroupLayout.PREFERRED_SIZE,
																	115,
																	GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(
																	LayoutStyle.ComponentPlacement.RELATED)
															.addComponent(
																	btnRegister,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.PREFERRED_SIZE)
															.addGap(0, 0,
																	Short.MAX_VALUE))
											.addGroup(
													thisLayout
															.createSequentialGroup()
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addComponent(
																					jLabel2,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					66,
																					GroupLayout.PREFERRED_SIZE)
																			.addComponent(
																					jLabel1,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					66,
																					GroupLayout.PREFERRED_SIZE))
															.addPreferredGap(
																	LayoutStyle.ComponentPlacement.UNRELATED)
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addGroup(
																					thisLayout
																							.createSequentialGroup()
																							.addComponent(
																									password,
																									GroupLayout.PREFERRED_SIZE,
																									111,
																									GroupLayout.PREFERRED_SIZE))
																			.addGroup(
																					thisLayout
																							.createSequentialGroup()
																							.addComponent(
																									username,
																									GroupLayout.PREFERRED_SIZE,
																									111,
																									GroupLayout.PREFERRED_SIZE)))
															.addGap(0, 43,
																	Short.MAX_VALUE))
											.addGroup(
													GroupLayout.Alignment.LEADING,
													thisLayout
															.createSequentialGroup()
															.addComponent(
																	message,
																	GroupLayout.PREFERRED_SIZE,
																	195,
																	GroupLayout.PREFERRED_SIZE)
															.addGap(0, 43,
																	Short.MAX_VALUE)))
							.addContainerGap(55, 55));

			this.setResizable(false);
			getContentPane().setBackground(new java.awt.Color(27, 194, 22));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
		username.requestFocus();
		btnLogin.setFocusable(false);
		btnRegister.addActionListener(this);
		btnLogin.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setMessage(String msg) {
		message.setText(msg);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(btnLogin)) {
			if (username.getText().length() < 4) {
				setMessage("Invalid username");
				return;
			}
			if (password.getPassword().length < 4) {
				setMessage("Invalid password");
				return;
			}
			if (Forest.server != null) {
				Forest.server.sendPacket("Login", username.getText() + "!@#"
						+ new String(password.getPassword()));
			} else {
				message.setText("No server connection.");
				Forest.connectToServer();
				if (Forest.server != null) {
					message.setText("Reconnected -Try again");
				}
				repaint();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (username.hasFocus()) {
				password.requestFocus();
			} else if (password.hasFocus()) {
				ActionEvent ae = new ActionEvent(btnLogin, 0, "Login");
				actionPerformed(ae);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
