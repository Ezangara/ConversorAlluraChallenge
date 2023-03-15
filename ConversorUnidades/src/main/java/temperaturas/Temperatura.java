package temperaturas;

public class Temperatura {
	private double magnitud;
	private String unidad;
	public Temperatura(double magnitud, String unidad) {
        this.magnitud = magnitud;
        this.unidad = unidad;
	}

	public Temperatura convertir(Temperatura origen, String destino) {
		Temperatura nuevaTemperatura = new Temperatura(magnitud, destino);
		nuevaTemperatura.unidad = destino;
		if (origen.unidad.equals("Celsius")) {
			if (destino.equals("Farenheit")) {
				nuevaTemperatura.magnitud = (((origen.magnitud) * 9 / 5) + 32); // Celsius a Farenheit
			} else {
				nuevaTemperatura.magnitud = ((origen.magnitud) + 273.15); // Celsius a Kelvin
			}
		} else if (origen.unidad.equals("Farenheit")) {
			if (destino.equals("Celsius")) {
				nuevaTemperatura.magnitud = (((origen.magnitud) - 32) * 5 / 9); // Farenheit a Celsius
			} else {
				nuevaTemperatura.magnitud = (((origen.magnitud) - 32) * 5 / 9) + 273.15; // Farenheit a Kelvin
			}
		} else if (origen.unidad.equals("Kelvin")) {
			if (destino.equals("Celsius")) {
				nuevaTemperatura.magnitud = ((origen.magnitud) - 273.15); // Kelvin a Celsius
			} else {
				nuevaTemperatura.magnitud = (((origen.magnitud) * 9 / 5) + 32) - 273.15; // Kelvin a Farenheit
			}

		} else {
			System.out.println("Error en la selección de la unidad. Reinicie el programa");
		}

		return nuevaTemperatura;
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