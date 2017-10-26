package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.DrawingBoard;

public class PaentWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel leftPanel = new JPanel(new BorderLayout());
	private JPanel brushPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel saveLoadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel colorPanel = new JPanel(new GridLayout());
	
	private JButton saveButton = new JButton("Save");
	private JButton loadButton = new JButton("Load");
	private JButton exportButton = new JButton("Export");
	
	public PaentWindow(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
