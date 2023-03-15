package pesos;

import java.util.HashMap;
import java.util.Map;

import longitudes.Longitud;

public class Pesos {

	private double magnitud;
	private String unidad;
	private HashMap valoresConversion;

	public Pesos(double magnitud, String unidad) {
		this.magnitud = magnitud;
		this.unidad = unidad;

		// Inicializar y agregar elementos al diccionario de conversión
		this.valoresConversion = new HashMap<>();
		Map<String, Double> kilos = new HashMap<>();
		kilos.put("kg", 1.0);
		kilos.put("ton", 1000.0);
		kilos.put("oz", 0.0283495);
		kilos.put("lb",0.453592 );
		valoresConversion.put("kg", kilos);

		Map<String, Double> toneladas = new HashMap<>();
		toneladas.put("kg", 0.001);
		toneladas.put("ton", 1.0);
		toneladas.put("oz",0.0283495 );
		toneladas.put("lb", 0.0005);
		valoresConversion.put("ton", toneladas);

		Map<String, Double> onzas = new HashMap<>();
		onzas.put("kg", 35.274);
		onzas.put("ton",35274.0 );
		onzas.put("oz", 1.0);
		onzas.put("lb", 16.0);
		valoresConversion.put("oz", onzas);

		Map<String, Double> libras = new HashMap<>();
		libras.put("kg", 2.20462);
		libras.put("ton", 2204.62);
		libras.put("oz", 0.0625);
		libras.put("lb", 1.0);
		valoresConversion.put("lb", libras);

	}

	public Pesos convertir(Pesos origen, String destino) {
		double nuevaMagnitud = 0;
		Pesos nuevaLongitud = new Pesos(nuevaMagnitud, destino);
		nuevaLongitud.setUnidad(destino);
		double magnitud = origen.getMagnitud() * obtenerFactor(origen.getUnidad(), destino);
																							
																							
		nuevaLongitud.setMagnitud(magnitud);
		return nuevaLongitud;
	}

	public double getMagnitud() {
		return magnitud;
	}

	public void setMagnitud(double magnitud) {
		this.magnitud = magnitud;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public double obtenerFactor(String origen, String destino) {
			
			double factorConversion = ((Map<String, Double>) valoresConversion.get(destino)).get(origen);
			//double factorConversion = valoresConversion.get(origen).get(destino);
			return factorConversion;
}
}
