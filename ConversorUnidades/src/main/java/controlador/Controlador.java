package controlador;


import java.util.ArrayList;

import conexionAPI.Conexion;
import divisas.Moneda;
import longitudes.Longitud;
import pesos.Pesos;
import temperaturas.Temperatura;
import volumen.Volumen;

//EN EL MAIN GRÁFICO, CUANDO APRETAMOS EL BOTON CONVERTIR, CREAMOS EL OBJETO CONTRALODOR
// CON LOS DATOS MAGNITUD, ORIGEN, DESTINO, TIPO DE CONVERSIIÓN, Y LLAMAMOS AL CONTROLADOR.
//Object[] Datos = new Object[]{"TipoConversion", "Origen", "destino", 3.14};
//Converti(Datos);

public class Controlador {

	public double Convertir(Object[] Datos) {
		String tipoConversion = (String) Datos[0];
		
		switch (tipoConversion) {
		
		case "Distancias":
			Longitud longitud = new Longitud((double) Datos[3], (String) Datos[1]);
			Longitud longitudConvertido = longitud.convertir(longitud, (String) Datos[2]);
			System.out.println(Datos[3] + " " + Datos[1] + " equivalen a: " + longitudConvertido.getMagnitud() + " "
					+ longitudConvertido.getUnidad());
			return longitudConvertido.getMagnitud();

		case "Divisas":
			double cotizacion;
			try {
				Moneda moneda=new Moneda();
				moneda.setValor((Double)Datos[3]);
				cotizacion=((moneda.convertir((String)Datos[1],(String)Datos[2])).getValor());
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("aca esta el error");
				e.printStackTrace();
				cotizacion=0;
			}

			return cotizacion;
		case "Temperaturas":
			Temperatura temperatura = new Temperatura((double) Datos[3], (String) Datos[1]);
			Temperatura temperaturaConvertida = temperatura.convertir(temperatura, (String) Datos[2]);
			System.out.println(Datos[3] + " " + Datos[1] + " equivalen a: " + temperaturaConvertida.getMagnitud() + " "
					+ temperaturaConvertida.getUnidad());
			return temperaturaConvertida.getMagnitud();

		case "Volumen":
			Volumen volumen = new Volumen((double) Datos[3], (String) Datos[1]);
			Volumen volumenConvertido = volumen.convertir(volumen, (String) Datos[2]);
			System.out.println(Datos[3] + " " + Datos[1] + " equivalen a: " + volumenConvertido.getMagnitud() + " "
					+ volumenConvertido.getUnidad());
			return volumenConvertido.getMagnitud();

		case "Pesos":
			Pesos peso = new Pesos((double) Datos[3], (String) Datos[1]);
			Pesos pesoConvertido = peso.convertir(peso, (String) Datos[2]);
			System.out.println(Datos[3] + " " + Datos[1] + " equivalen a: " + pesoConvertido.getMagnitud() + " "
					+ pesoConvertido.getUnidad());
			return pesoConvertido.getMagnitud();
			
			
		default:
			System.out.println("Error fatal, cierre el programa y vuelva a iniciarlo");
			return 0;
		}
	}

}