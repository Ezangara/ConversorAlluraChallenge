package divisas;

import conexionAPI.Conexion;

public class Moneda {
private String trigrama;
private double valor;

public Moneda convertir ( String monedaOrigen, String MonedaDestino) {
	Moneda nuevaMoneda= new Moneda();
	double cotizacion;
	try {
		nuevaMoneda.valor = Conexion.obtenerCotizacion(monedaOrigen, MonedaDestino, this.valor);
		//nuevaMoneda.valor= this.valor * cotizacion;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Error al conectar con Open Exchange Rates");
		e.printStackTrace();
	}
	
	return nuevaMoneda;
}



public String getTrigrama() {
	return trigrama;
}

public void setTrigrama(String trigrama) {
	this.trigrama = trigrama;
}

public double getValor() {
	return valor;
}

public void setValor(double valor) {
	this.valor = valor;
}


}