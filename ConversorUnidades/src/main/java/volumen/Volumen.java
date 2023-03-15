package volumen;

import java.util.HashMap;
import java.util.Map;


//"m3","lt","ft3", "ImpGal","USGal"

public class Volumen {

	private double magnitud;
	private String unidad;
	private HashMap valoresConversion;

	public Volumen(double magnitud, String unidad) {
        this.magnitud = magnitud;
        this.unidad = unidad;

        // Inicializar y agregar elementos al diccionario de conversión
        this.valoresConversion = new HashMap<>();
        Map<String, Double> metroCubico = new HashMap<>();
        metroCubico.put("m3", 1.0);
        metroCubico.put("lt", 1000.0);
        metroCubico.put("ft3", 35.3147);
        metroCubico.put("ImpGal", 219.969);
        metroCubico.put("USGal", 264.172);     
        valoresConversion.put("m3", metroCubico);

        Map<String, Double> litros = new HashMap<>();
        litros.put("m3", 0.001);
        litros.put("lt", 1.0);
        litros.put("ft3", 0.0353147);
        litros.put("ImpGal", 0.219969);
        litros.put("USGal", 0.264172);
        valoresConversion.put("lt", litros);
        
        Map<String, Double> pieCubico = new HashMap<>();
        pieCubico.put("m3", 0.0283168);
        pieCubico.put("lt", 28.3168);
        pieCubico.put("ft3", 1.0);
        pieCubico.put("ImpGal", 6.22884);
        pieCubico.put("USImp", 7.48052);
        valoresConversion.put("ft3", pieCubico);
        
        Map<String, Double> galonImperial = new HashMap<>();
        galonImperial.put("m3", 0.00454609);
        galonImperial.put("lt", 4.54609);
        galonImperial.put("ft3", 0.160544);
        galonImperial.put("ImpGal", 1.0);
        galonImperial.put("USGal", 1.200945);
        valoresConversion.put("ImpGal", galonImperial);
        
        Map<String, Double> galonesUSA = new HashMap<>();
        galonesUSA.put("m3", 0.0037854);
        galonesUSA.put("lt", 3.785);
        galonesUSA.put("ft3", 0.133681);
        galonesUSA.put("ImpGal", 0.832674);
        galonesUSA.put("USGal", 1.0);
        valoresConversion.put("USGal",galonesUSA);
        
	}
	
	public Volumen convertir (Volumen origen, String destino) {		
		double nuevaMagnitud = 0;
		Volumen nuevoVolumen= new Volumen(nuevaMagnitud, destino);
		nuevoVolumen.setUnidad(destino);
		double magnitud=origen.getMagnitud()*obtenerFactor(origen.getUnidad(), destino);//ACA HAY QUE LLAMAR AL MÉTODO PARA OBTENER EL FACTOR DE CONVERSION
		nuevoVolumen.setMagnitud(magnitud);
		return nuevoVolumen;
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