package br.gov.sp.fatec;

import java.awt.BorderLayout;

/**
 * @author Luiz Carlos Farias da Silva
 * email - fariaslcfs@gmail.com  luiz.silva116@fatec.sp.gov.br 
 * 
 */

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.mysql.fabric.xmlrpc.base.Array;

import javassist.bytecode.Descriptor.Iterator;

/**
 * @author usuario
 *
 */
@SuppressWarnings("serial")
public class Form extends Frame {

	Collection<Double> colrets = new ArrayList<Double>();
	Double temposRetornados[] = new Double[3];

	Frame frm = new Frame("Teste Parsers");
	int i_iter;
	String uoracle, poracle, thard, tfirm, tsoft;

	String umysql;
	String pmysql;
	String usqlserver;
	String psqlserver;

	final Label lbiter = new Label("      Número de iterações");
	final Label lbuo = new Label("            Usuário Oracle");
	final Label lbpo = new Label("            Senha Oracle");

	Label lbum = new Label("            Usuário Mysql");
	Label lbus = new Label("            Usuário SqlServer");
	Label lbpm = new Label("            Senha Mysql");

	Label lbps = new Label("            Senha SqlServer");

	/*final Label lbbranco = new Label("\n                  TEMPO(s)");*/
	final Label lbespaco = new Label(" \n");
	final TextField iter = new TextField(20);
	final TextField useroracle = new TextField(20);
	final TextField passoracle = new TextField(20);
/*	final TextField tempoh = new TextField(20);
	final TextField tempof = new TextField(20);
	final TextField tempos = new TextField(20);
	final TextField info = new TextField();*/

	final TextField usermysql = new TextField(20);
	final TextField passmysql = new TextField(20);
	final TextField usersqlserver = new TextField(20);
	final TextField passsqlserver = new TextField(20);

	/**
	 * 
	 */
	public void criaForm() {

		frm.setSize(250, 440);
		frm.setLocationRelativeTo(null);

		frm.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		Panel p = new Panel();
		Panel p1 = new Panel();

		p.setLayout(new GridLayout(0, 1));

		p.add(lbiter);
		p.add(iter);

		
		 p.add(lbum); p.add(usermysql);		 
		 p.add(lbpm); p.add(passmysql);
		 

		p.add(lbuo);
		p.add(useroracle);

		p.add(lbpo);
		p.add(passoracle);


		
		 p.add(lbus); p.add(usersqlserver);
		 p.add(lbps); p.add(passsqlserver);
		 

		p.add(lbespaco);
		p.add(lbespaco);


		Button Inicia = new Button("Inicia Teste");
		Inicia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Teste teste = new Teste();
				i_iter = Integer.parseInt(iter.getText());
				uoracle = useroracle.getText();
				poracle = passoracle.getText();

				
				 usqlserver = usersqlserver.getText();
				 psqlserver =	 passsqlserver.getText(); 
				 umysql = usermysql.getText(); 
				 pmysql  = passmysql.getText();
				 

				try {
					colrets = teste.Testar(i_iter, usqlserver, psqlserver, umysql, pmysql, uoracle, poracle);
					int i = 0;
					for (Double t : colrets) {
						temposRetornados[i] = t;
						i++;
					}
/*					tempoh.setText("HARD\t" + temposRetornados[0].toString());
					tempof.setText("FIRM\t" + temposRetornados[1].toString());
					tempos.setText("SOFT\t" + temposRetornados[2].toString());*/
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			
		});
		p.add(lbespaco);
		p.add(Inicia);

/*		p.add(lbbranco);
		p.add(tempoh);
		p.add(tempof);
		p.add(tempos);*/
		p1.add(p);
		frm.add(p1, BorderLayout.NORTH);
	}
}
