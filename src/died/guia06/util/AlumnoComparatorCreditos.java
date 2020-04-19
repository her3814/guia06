package died.guia06.util;

import java.util.Comparator;

import died.guia06.Alumno;

/**
 * @author her38
 * Comparador de alumnos por Cantidad de Creditos obtenidos
 */
public class AlumnoComparatorCreditos implements Comparator<Alumno> {
	@Override
	public int compare(Alumno o1, Alumno o2) {
		return (o2.creditosObtenidos() - o1.creditosObtenidos());
	}

}
