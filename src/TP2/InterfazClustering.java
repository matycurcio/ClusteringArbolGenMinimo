package TP2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class InterfazClustering extends javax.swing.JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private ArrayList<Point> puntos;
	private JTextField cajaCantClusters;
	int cantClusters;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazClustering window = new InterfazClustering();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public InterfazClustering() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 128));
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CLUSTERING DE DATOS");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 21));
		lblNewLabel.setBounds(320, 24, 243, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel msgError = new JLabel("ERROR! Ingrese un n\u00FAmero");
		msgError.setForeground(new Color(255, 0, 0));
		msgError.setHorizontalAlignment(SwingConstants.CENTER);
		msgError.setBounds(644, 802, 133, 30);
		frame.getContentPane().add(msgError);
		msgError.setVisible(false);
	
		puntos=new ArrayList<Point>();
		
		generarPuntos();		// agrega los puntos del plano a la lista puntos
	
		Clustering c=new Clustering(puntos);		
		
		ArrayList<JPanel> dibujosClusters= new ArrayList<JPanel>();		//genera una lista con todos los dibujos de clusters posibles y los guarda en una lista de JPanel
		for (int i=0; i<puntos.size(); ++i) {
			Grafo g= c.obtenerClusters(i);
			JPanel panelClusters = dibujarGrafo(g);
			panelClusters.setVisible(false);
			dibujosClusters.add(panelClusters);
		}
				
		JPanel panelPuntos=dibujarPuntos(puntos);
		panelPuntos.setVisible(true);
		JPanel panelGrafoCompleto = dibujarGrafo(c.getGrafo());
		panelGrafoCompleto.setVisible(false);

		
		JButton dibujarGrafoCompleto = new JButton("DIBUJAR GRAFO COMPLETO");
		dibujarGrafoCompleto.addActionListener(new ActionListener() {		//cuando se presiona el boton dibujar grafo completo, muestra el JPanel panelGrafoCompleto
			public void actionPerformed(ActionEvent e) {					//y esconde todos los demás paneles
				for(JPanel panel: dibujosClusters) {
					panel.setVisible(false);
				}
				panelPuntos.setVisible(false);
				panelGrafoCompleto.setVisible(true);			
			}
		});
		dibujarGrafoCompleto.setBounds(129, 762, 180, 30);
		frame.getContentPane().add(dibujarGrafoCompleto);
		
		JButton generarClusters = new JButton("GENERAR CLUSTERS");			//cuando se presiona el boton generar clusters, se muestra solo el panel i de la lista dibujosClusters
		generarClusters.addActionListener(new ActionListener() {			//siendo i el número ingresado por usuario.
			public void actionPerformed(ActionEvent e) {
				try{

				panelGrafoCompleto.setVisible(false);
				mostrarSolo(Integer.parseInt(cajaCantClusters.getText()), dibujosClusters);
				msgError.setVisible(false);
				panelPuntos.setVisible(true);
				}
				catch (Exception e3) {
					msgError.setVisible(true);
					panelPuntos.setVisible(true);
				}
			}
		});
		generarClusters.setBounds(488, 802, 142, 30);
		frame.getContentPane().add(generarClusters);
		
		cajaCantClusters = new JTextField();
		cajaCantClusters.setColumns(10);
		cajaCantClusters.setBounds(541, 746, 34, 30);
		frame.getContentPane().add(cajaCantClusters);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese n\u00FAmero de clusters deseados");
		lblNewLabel_1.setBounds(475, 702, 180, 30);
		frame.getContentPane().add(lblNewLabel_1);			
	}

	private JPanel dibujarPuntos(ArrayList<Point> puntos) {
		JPanel panel=new javax.swing.JPanel();
		panel.setBackground(new Color(255, 255, 255));
		
		for (Point p: puntos) {
			JPanel newPanel = new JPanel();
			newPanel.setBackground(Color.BLACK);
			newPanel.setBounds(p.x, p.y, 10, 10);
			panel.add(newPanel);
		}
		panel.setBounds(10, 90, 865, 601);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		return panel;
	}
	
	private JPanel dibujarGrafo(Grafo h) {
		
		JPanel panel = new javax.swing.JPanel() {
			  /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			public void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    for (int i=0; i<h.getMatrizAdy().length; ++i) {
			    	for (int j=0; j<h.getMatrizAdy().length; ++j) {
			    		if(h.getMatrizAdy()[i][j]) {
			    			g.drawLine(puntos.get(i).x, puntos.get(i).y, puntos.get(j).x, puntos.get(j).y);
			    		}
			    	}
			    }
			}
		};
		for(Point p:puntos) {
			JPanel newPanel = new JPanel();
			newPanel.setBackground(Color.BLACK);
			newPanel.setBounds(p.x, p.y, 10, 10);
			panel.add(newPanel);
		}
		panel.setBounds(10, 90, 865, 601);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		return panel;
	}
	
	private void mostrarSolo(int indiceAMostrar, ArrayList<JPanel> dibujosClusters) {
		for (int i=0 ; i<dibujosClusters.size(); ++i) {
			if(indiceAMostrar!=i) {
				dibujosClusters.get(i).setVisible(false);
			}
			else
				dibujosClusters.get(i).setVisible(true);
		}
		
	}
		
	private void generarPuntos() {
		Point p1=new Point(100,100);
		Point p2= new Point(50,50);
		Point p3= new Point (50,200);
		Point p4=new Point (60,150);
		Point p5=new Point (100,150);
		Point p6=new Point (200,150);
		Point p7=new Point (300,200);
		Point p8=new Point (400,300);
		Point p9=new Point (300,50);
		Point p10=new Point (790,550);
		Point p11=new Point (790,10);
		Point p12=new Point (700,50);
		Point p13=new Point (790,500);
		Point p14=new Point (700,550);
		Point p15=new Point (50,500);
		Point p16=new Point (75,550);
		Point p17=new Point (150,400);
		Point p18=new Point (200,500);
		Point p19=new Point (180,350);
		Point p20=new Point (200,450);

		puntos.add(p4);
		puntos.add(p1);
		puntos.add(p2);
		puntos.add(p3);
		puntos.add(p5);
		puntos.add(p6);
		puntos.add(p7);
		puntos.add(p8);
		puntos.add(p9);
		puntos.add(p10);
		puntos.add(p11);
		puntos.add(p12);
		puntos.add(p13);
		puntos.add(p14);
		puntos.add(p15);
		puntos.add(p16);
		puntos.add(p17);
		puntos.add(p18);
		puntos.add(p19);
		puntos.add(p20);
	}
}
