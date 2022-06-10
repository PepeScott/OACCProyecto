package fes.aragon.controlador;

import fes.aragon.modelo.Clientes;
import fes.aragon.mysql.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class NuevoClienteController {

    @FXML
    private TextField txtApellidoPaterno;

    @FXML
    private TextField txtNombre;

    @FXML
    void accionLimpiar(ActionEvent event) {
    	this.limpiar();
    }
    private Clientes cliente = null;

    @FXML
    void guardarAccion(ActionEvent event) {
		if(cliente == null) {
			cliente = new Clientes();
		}
		
		if(validar()) {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			if(cliente.getId() == null) {
				almacenar();
				alerta.setContentText("Se ha almacenado el cliente!");
				limpiar();
			} else {
				modificar();
				alerta.setContentText("Se ha modificado el cliente!");
			}
			alerta.setHeaderText(null);
			alerta.showAndWait();
		}
    }
    
    private void almacenar() {
    	try {
			Conexion cnn = new Conexion();
			cliente.setNombre(txtNombre.getText());
			cliente.setApellidoPaterno(txtApellidoPaterno.getText());
			cnn.almacenarClientes(cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setHeaderText(null);
			alerta.setContentText("Lo sentimos, se ha presentado un problema");
			alerta.showAndWait();
			e.printStackTrace();
		}
    }
    
    private void modificar() {
    	try {
			Conexion cnn = new Conexion();
			cliente.setNombre(txtNombre.getText());
			cliente.setApellidoPaterno(txtApellidoPaterno.getText());
			cnn.modificarClientes(cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setHeaderText(null);
			alerta.setContentText("Lo sentimos, se ha presentado un problema");
			alerta.showAndWait();
			e.printStackTrace();
		}
    }
    
    private void limpiar() {
    	this.txtNombre.setText("");
    	this.txtApellidoPaterno.setText("");
    }
    private boolean validar() {
    	boolean validos = true;
    	if(this.txtNombre.getText().isEmpty() || this.txtNombre.getText().regionMatches(0, " ", 0, 1)) {
    		validos = false;
    	}
    	if(this.txtApellidoPaterno.getText().isEmpty() || this.txtApellidoPaterno.getText().regionMatches(0, " ", 0, 1)) {
    		validos = false;
    	}
    	return validos;
    }
    public void modificarCliente(Clientes cliente) {
    	this.cliente = cliente;
    	this.txtNombre.setText(cliente.getNombre());
    	this.txtApellidoPaterno.setText(cliente.getApellidoPaterno());
    }

}
