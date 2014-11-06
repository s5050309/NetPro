package webBrowserL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFactory;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.events.StartLoadingEvent;

public class webBrowserLiang {
	public static void main(String[] args)
	{
		webBrowserLiang start = new webBrowserLiang();
		start.jFrame.setVisible(true);
	}

	private JFrame jFrame;
	private JTextField addressUrl;
	private JTextField addressUrl1;
	private JButton bTEnter;
	private JButton bTnBack;
	private JButton bTnBack1;
	private JLabel labelUrl;
	private JLabel labelUrl1;
	private Browser bSer;
	private Browser bSer1;
	private JTextArea area;
	private JScrollPane scroll;
	
	private Thread t1;
	private Thread t2;

	public webBrowserLiang() {
		browser();
		initLookAndFeel();
		loadInterface();
	}

	private void loadInterface(){
		jFrame = new JFrame();
		jFrame.setSize(1200, 800);
		jFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		jFrame.setVisible(true);
		jFrame.setTitle("นาย ทศพล อนันต์สินชัย s5050309@kmitl.ac.th - Java Web Browser (Network Programming Class Assignment)");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		labelUrl = new JLabel(" URL : ");
		labelUrl.setFont(new Font("Cordia New", Font.ROMAN_BASELINE, 27));
		labelUrl1 = new JLabel(" URL : ");
		labelUrl1.setFont(new Font("Cordia New", Font.ROMAN_BASELINE, 27));
		addressUrl = new JTextField(35);
		addressUrl.setFont(new Font("Cordia New", Font.ROMAN_BASELINE, 30));
		addressUrl1 = new JTextField(35);
		addressUrl1.setFont(new Font("Cordia New", Font.ROMAN_BASELINE, 30));
		Icon Bb = new ImageIcon(getClass().getResource("nre.jpg"));
		Icon Bb2 = new ImageIcon(getClass().getResource("re.jpg"));
		bTEnter = new JButton(Bb);
		bTEnter.setRolloverIcon(Bb2);
		bTEnter.setFont(new Font("Cordia New", Font.ROMAN_BASELINE, 25));
		bTEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runWeb1();
				runWeb2();
			}
		});
		Icon BEnter = new ImageIcon(getClass().getResource("nclick.jpg"));
		Icon BEnter2 = new ImageIcon(getClass().getResource("click.jpg"));
		bTnBack = new JButton(BEnter);
		bTnBack.setRolloverIcon(BEnter2);
		bTnBack.setFont(new Font("Cordia New", Font.ROMAN_BASELINE, 25));
		bTnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1 = new Thread(new Runnable() {
					public void run() {
						onClickbTBack();
						print("Thread 1", "Load = " + bSer.getURL());
						while(bSer.isLoading()) {

						}
						print("Thread 1", "Finish = " + bSer.getURL());
					}
				});
				t1.start();
			}
		});
		bTnBack1 = new JButton(BEnter);
		bTnBack1.setRolloverIcon(BEnter2);
		bTnBack1.setFont(new Font("Cordia New", Font.ROMAN_BASELINE, 25));
		bTnBack1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t2 = new Thread(new Runnable() {
					public void run() {
						onClickbTBack1();
						print("Thread 2", "Load = " + bSer1.getURL());
						while(bSer1.isLoading()) {
							
						}
						print("Thread 2", "Finish = " + bSer1.getURL());
					}
				});
				t2.start();
			}
		});
		area = new JTextArea();
		area.setFont(new Font("Cordia New", Font.ROMAN_BASELINE, 25));
		area.setForeground(Color.WHITE);
		area.setBackground(Color.BLACK);
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setSize(area.getWidth(), jFrame.getHeight() / 4);
		scroll = new JScrollPane(area);
		scroll.setPreferredSize(new Dimension(area.getWidth(), area.getHeight()));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		JPanel Panel1 = new JPanel();
		Panel1.add(bTnBack);
		Panel1.add(labelUrl);
		Panel1.add(addressUrl);
		JPanel Panel2 = new JPanel();
		Panel2.add(labelUrl1);
		Panel2.add(addressUrl1);
		Panel2.add(bTnBack1);
		JPanel Panel3 = new JPanel();
		Panel3.add(Panel1);
		Panel3.add(bTEnter);
		Panel3.add(Panel2);
		Panel3.setBackground(Color.getHSBColor(135 , 120, 131));
		JPanel Panel4 = new JPanel(new BorderLayout());
		Panel4.add(bSer.getView().getComponent(), BorderLayout.WEST);
		Panel4.add(bSer1.getView().getComponent(), BorderLayout.CENTER);
		resizeSolution();
		jFrame.add(Panel3, BorderLayout.NORTH);
		jFrame.add(Panel4, BorderLayout.CENTER);
		jFrame.add(scroll, BorderLayout.SOUTH);
		jFrame.setLocationRelativeTo(null);
		jFrame.addComponentListener(new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				
			}

			@Override
			public void componentResized(ComponentEvent e) {
				resizeSolution();
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				
			}
		});
		jFrame.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent arg0) {
				resizeSolution();
			}
		});
		addressUrl.setText("www.google.co.th");
		addressUrl1.setText("www.facebook.com");
		runWeb1();
		runWeb2();
	}
	
	private void resizeSolution() {
		bSer.getView().getComponent().setSize(jFrame.getWidth() / 2, jFrame.getHeight());
		bSer1.getView().getComponent().setSize(jFrame.getWidth() / 2, jFrame.getHeight());
		bSer.getView().getComponent().setPreferredSize(new Dimension(bSer.getView().getComponent().getWidth(), bSer.getView().getComponent().getHeight()));
		bSer1.getView().getComponent().setPreferredSize(new Dimension(bSer1.getView().getComponent().getWidth(), bSer1.getView().getComponent().getHeight()));
		area.setSize(area.getWidth(), jFrame.getHeight() / 4);
		scroll.setPreferredSize(new Dimension(area.getWidth(), area.getHeight()));
	}

	private void onClickbTBack(){
		int post = bSer.getCurrentNavigationEntryIndex() - 1;
		bSer.goToIndex(post);
	}

	private void onClickbTBack1(){
		int post = bSer1.getCurrentNavigationEntryIndex() - 1;
		bSer1.goToIndex(post);
	}


	private void browser() {
		bSer = BrowserFactory.create();
		bSer.addLoadListener(new LoadAdapter() {
			public void onStartLoadingFrame(StartLoadingEvent event) {
				if(event.isMainFrame()) {
					reload(event.getValidatedURL(), false);
				}
			}
		});
		bSer1 = BrowserFactory.create();
		bSer1.addLoadListener(new LoadAdapter() {
			public void onStartLoadingFrame(StartLoadingEvent event) {
				if(event.isMainFrame()) {
					reload1(event.getValidatedURL(), false);
				}
			}
		});
	}

	private void initLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e){
			e.printStackTrace();
		}		
	}

	private void reload(String url, boolean reload) {
		url = url.toLowerCase();
		if(!(url.startsWith("http://") || url.startsWith("https://")))
			url = "http://" + url;
		if(reload)
			bSer.loadURL(url);
		addressUrl.setText(url);
	}
	
	private void reload1(String url, boolean reload) {
		url = url.toLowerCase();
		if(!(url.startsWith("http://") || url.startsWith("https://")))
			url = "http://" + url;
		if(reload)
			bSer1.loadURL(url);
		addressUrl1.setText(url);
	}
	
	private void print(String tabName, String msg) {
		area.append("{" + tabName + "} : " + msg + "\r\n");
		area.setCaretPosition(area.getText().length());
	}
	
	private void runWeb1() {
		t1 = new Thread(new Runnable() {
			public void run() {
				reload(addressUrl.getText(), true);
				print("Thread 1", "Load = " + bSer.getURL());
				while(bSer.isLoading()) {
					
				}
				print("Thread 1", "Finish = " + bSer.getURL());
			}
		});
		t1.start();
	}
	
	private void runWeb2() {
		t2 = new Thread(new Runnable() {
			public void run() {
				reload1(addressUrl1.getText(), true);
				print("Thread 2", "Load = " + bSer1.getURL());
				while(bSer1.isLoading()) {

				}
				print("Thread 2", "Finish = " + bSer1.getURL());
			}
		});
		t2.start();
	}
	
}
