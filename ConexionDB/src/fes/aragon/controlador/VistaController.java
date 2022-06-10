package fes.aragon.controlador;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;

public class VistaController {
	
	@FXML
	private BorderPane idPrincipal;

	// Event Listener on Button.onAction
	@FXML
	public void accionCliente(ActionEvent event) {
		Contenido contenido = new Contenido("/fes/aragon/vista/Cliente.fxml");
		idPrincipal.setCenter(contenido);
	}
}
