package com.carballeira.aplicacion.view;
import com.carballeira.aplicacion.utils.ImagenUtils;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class BotonesView {

	public final String PATH_IMG_ENTRAR="/application/images/btn_entrar.png";
	
	public void addImageBotonEntrar(Button boton) {
		boton.setGraphic(new ImageView(new ImagenUtils().addImageAlComponente(PATH_IMG_ENTRAR)));
	}
}
