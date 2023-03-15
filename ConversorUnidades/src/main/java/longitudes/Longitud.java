package longitudes;

import java.util.HashMap;
import java.util.Map;




public class Longitud {

	private double magnitud;
	private String unidad;
	private HashMap valoresConversion;

	public Longitud(double magnitud, String unidad) {
        this.magnitud = magnitud;
        this.unidad = unidad;

        // Inicializar y agregar elementos al diccionario de conversión
        this.valoresConversion = new HashMap<>();
        Map<String, Double> metros = new HashMap<>();
        metros.put("m", 1.0);
        metros.put("km", 1000.0);
        metros.put("in", 0.025399);
        metros.put("ft", 0.304794);
        metros.put("mi", 1609.34);
        metros.put("nm", 1852.0);
        valoresConversion.put("m", metros);

        Map<String, Double> kilometros = new HashMap<>();
        kilometros.put("m", 0.001);
        kilometros.put("km", 1.0);
        kilometros.put("in", 0.000025);
        kilometros.put("ft", 0.000304);
        kilometros.put("mi", 1.60934);
        kilometros.put("nm", 1.8520);
        valoresConversion.put("km", kilometros);
        
        Map<String, Double> pulgadas = new HashMap<>();
        pulgadas.put("m", 39.3701);
        pulgadas.put("km", 39370.1);
        pulgadas.put("in", 1.0);
        pulgadas.put("ft", 12.0);
        pulgadas.put("mi", 63360.0);
        pulgadas.put("nm", 72913.4);
        valoresConversion.put("in", pulgadas);
        
        Map<String, Double> pies = new HashMap<>();
        pies.put("m", 3.28084);
        pies.put("km", 3280.84);
        pies.put("in", 0.08333);
        pies.put("ft", 1.0);
        pies.put("mi", 5280.0);
        pies.put("nm", 6076.12);
        valoresConversion.put("ft", pies);
        
        Map<String, Double> millasTerrestres = new HashMap<>();
        millasTerrestres.put("m", 0.006621);
        millasTerrestres.put("km", 0.62137);
        millasTerrestres.put("in", 0.000015);
        millasTerrestres.put("ft", 0.000189);
        millasTerrestres.put("mi", 1.0);
        millasTerrestres.put("nm", 1.1508);
        valoresConversion.put("mi", millasTerrestres);
        
        Map<String, Double> millasNauticas = new HashMap<>();
        millasNauticas.put("m", 0.0005399);
        millasNauticas.put("km", 0.5399568);
        millasNauticas.put("in", 0.00001371);
        millasNauticas.put("ft", 0.00016457);
        millasNauticas.put("mi", 0.8689607);
        millasNauticas.put("nm", 1.0);
        valoresConversion.put("mn", millasNauticas);
    }

	
	public Longitud convertir (Longitud origen, String destino) {		
		double nuevaMagnitud = 0;
		Longitud nuevaLongitud= new Longitud(nuevaMagnitud, destino);
		nuevaLongitud.setUnidad(destino);
		double magnitud=origen.getMagnitud()*obtenerFactor(origen.getUnidad(), destino);//ACA HAY QUE LLAMAR AL MÉTODO PARA OBTENER EL FACTOR DE CONVERSION
		nuevaLongitud.setMagnitud(magnitud);
		return nuevaLongitud;
	}
	
	
public double obtenerFactor(String origen, String destino) {
	
	double factorConversion = ((Map<String, Double>) valoresConversion.get(destino)).get(origen);
	//double factorConversion = valoresConversion.get(origen).get(destino);
	return factorConversion;
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

	
		
	
}
