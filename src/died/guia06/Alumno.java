package died.guia06;

import java.util.ArrayList;
import java.util.List;


public class Alumno implements Comparable<Alumno> {
	private String nombre;
	private Integer nroLibreta;
	private List<Curso> cursando;
	private List<Curso> aprobados;

	public String getNombre() {
		return this.nombre;
	}
		
	public Integer getNroLibreta() {
		return nroLibreta;
	}

	//Se implementa el constructor con nombre y numero de libreta. A fin de que una vez
	//dados estos datos no puedan editarse.
	public Alumno(String nombre, Integer nroLibreta) throws IllegalArgumentException {
		super();
		
		if(nombre==null)
			throw new IllegalArgumentException("El nombre no puede estar vacio");

		if(nroLibreta==null)
			throw new IllegalArgumentException("El numero de libreta no puede estar vacío");
		
		this.nombre = nombre;
		this.nroLibreta = nroLibreta;
		this.aprobados = new ArrayList<Curso>();
		this.cursando = new ArrayList<Curso>();
		
	}


	public int creditosObtenidos() {
		int creditos = 0;
		if(aprobados != null)
			for(Curso curso : aprobados) {
				creditos += curso.getCreditos();
			}
		
		return creditos;
	}

	public void aprobar(Curso c) throws IllegalArgumentException{
		if(!this.cursando.contains(c)) {
			throw new IllegalArgumentException("No puede aprobar un curso sin estar cursandolo");
		}
		
		if(this.cursando.contains(c)) {
			this.cursando.remove(c);
			this.aprobados.add(c);
		}
	}

	public void inscripcionAceptada(Curso c) throws IllegalArgumentException{
		if(this.cursando.size()>=3) {
			throw new IllegalArgumentException("No puede inscribirse a mas de 3 cursos a la vez.");
		}		
			this.cursando.add(c);			
	}

	public boolean inscriptoEn(Curso curso) {
		return cursando.contains(curso);
	}

	public boolean aproboCurso(Curso curso) {
		return aprobados.contains(curso);
	}
	public int getCantidadCursosInscripto() {		
		return this.cursando.size();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (this.getNroLibreta() == null) {
			if (other.getNroLibreta() != null)
				return false;
		} else if (!this.getNroLibreta().equals(other.getNroLibreta()))
			return false;
		return true;
	}

	@Override
	public int compareTo(Alumno o) {
		return this.getNombre().compareTo(o.getNombre());
	}

}
