package ar.com.syr.trasportes.initialData;

public class GeneralGenerator {
	
	public static void main(String[] args) {
		new DataGeneratorRemito().generateRemitos();
		new DataGeneratorEmpleado().generateEmpleados();

	}

}
