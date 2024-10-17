package com.carballeira.aplicacion.model;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Usuario {

	private String nombre;
    private String pwd;

	
    
    //Constructor
    public Usuario(String nombre, String pwd) {		
        this.nombre = nombre;
		this.pwd= pwd;
	}   
	
	//Getters & Setters
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public boolean comprobarUsuario() {
		
	           	// AQUI HARÍA UNA SELECT A LA BBDD Y OBTIENE LOS DATOS
				if (nombre != null && !this.nombre.isEmpty()) {//COMPRUEBA SI ESTAN VACIOS
					if (this.pwd != null && !this.pwd.isEmpty()) {
						       NodeList nodosUsuario=OperacionesBBDDXML.getNodesXML();
		                       return  selectUser(nodosUsuario);
					}
				}
				return false;
	}

	public Boolean selectUser(NodeList nodosUsuario) {
		for (int i = 0; i < nodosUsuario.getLength(); i++) {
			Node persona = nodosUsuario.item(i);

			if (persona.getNodeType() == Node.ELEMENT_NODE) {
				Element unElemento = (Element) persona;

				Usuario objUser = new Usuario(obtenerNodoValor("user", unElemento), //porque en el xml es user
						obtenerNodoValor("pwd", unElemento));
				if (objUser.getNombre().equals(nombre)) {
					if (objUser.getPwd().equals(pwd)) {
						return true;
					}
				}
			}
		}
		return false;
	}


	private String obtenerNodoValor(String strTag, Element ePersona) {
		Node nValor = (Node) ePersona.getElementsByTagName(strTag).item(0).getFirstChild();
		return nValor.getNodeValue();
	}

	
}
