package died.guia06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import died.guia06.util.AlumnoComparatorCreditos;
import died.guia06.util.AlumnoComparatorNroLibreta;
import died.guia06.util.Registro;

/**
 * Clase que representa un curso. Un curso se identifica por su ID y por su nombre y ciclo lectivo.
 * Un curso guarda una lista de los inscriptos actuales que tienen.
 * Un curso, al aprobarlo, otorga una cantidad de creditos definidas en el curso.
 * Un curso requiere que para inscribirnos tengamos al menos la cantidad de creditos requeridas, y que haya cupo disponible
 * @author marti
 *
 */
public class Curso {

	private Integer id;
	private String nombre;
	private Integer cicloLectivo;
	private Integer creditos;
	private Integer creditosRequeridos;	
	
	private Integer cupo; 
	private List<Alumno> inscriptos;
	
	private Registro log;
	
	public Integer getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}	

	public Integer getCicloLectivo() {
		return this.cicloLectivo;
	}

	public Integer getCreditos() {
		return this.creditos;
	}

	public Integer getCreditosRequeridos() {
		return this.creditosRequeridos;
	}
	
	public Integer getCupo() {
		return this.cupo;
	}
	
	/**
	 * Permite cambiar el cupo del curso, solo si la cantidad de curso no es menor a la cantidad de alumnos ya inscriptos en el curso, en tal caso arroja un error
	 * 
	 * @param cupo Nueva cantidad de cupos para el curso.
	 **/
	public void setCupo(Integer cupo) throws IllegalArgumentException{
		try {		
			log.registrar(this, "modificar cupo", " de " + this.cupo + " a " + cupo);
			if(cupo < this.inscriptos.size()) {
				throw new IllegalArgumentException("Ya tiene mas inscriptos que el cupo que desea setear al curso.");
			}
			else this.cupo = cupo;
		}catch (IOException e) {
			System.out.println("Sucedió un error al inscribir un alumno. Error:" + e.getMessage());
			e.printStackTrace();			
		}		
	}	
		
	public Curso(Integer id, String nombre, Integer cicloLectivo, Integer cupo, Integer creditos, Integer creditosRequeridos) throws IllegalArgumentException{

		super();
		if(id==null)
			throw new IllegalArgumentException("El id no puede estar vacio");

		if(nombre==null)
			throw new IllegalArgumentException("El nombre no puede estar vacio");

		if(cicloLectivo==null)
			throw new IllegalArgumentException("El cicloLectivo no puede estar vacio");

		if(cupo==null)
			throw new IllegalArgumentException("El cupo no puede estar vacio");

		if(creditos==null)
			throw new IllegalArgumentException("El creditos no puede estar vacio");

		if(creditosRequeridos==null)
			throw new IllegalArgumentException("El creditosRequeridos no puede estar vacio");
		
		this.id=id;
		this.nombre=nombre;
		this.cicloLectivo=cicloLectivo;
		this.cupo=cupo;
		this.creditos=creditos;
		this.creditosRequeridos=creditosRequeridos;
		

		this.inscriptos = new ArrayList<Alumno>();
		this.log = new Registro();
	}
	

	/**
	 * Este mÃ©todo, verifica si el alumno se puede inscribir y si es asÃ­ lo agrega al curso,
	 * agrega el curso a la lista de cursos en los que estÃ¡ inscripto el alumno y retorna verdadero.
	 * Caso contrario retorna falso y no agrega el alumno a la lista de inscriptos ni el curso a la lista
	 * de cursos en los que el alumno estÃ¡ inscripto.
	 * 
	 * Para poder inscribirse un alumno debe
	 * 		a) tener como minimo los creditos necesarios
	 *      b) tener cupo disponibles
	 *      c) puede estar inscripto en simultÃ¡neo a no mÃ¡s de 3 cursos del mismo ciclo lectivo.
	 * @param a Alumno a inscribir
	 * @return Devuelve true si el alumno pudo inscribirse correctamente. false si no se cumplen los requisitos de inscripción necesarios
	 */
	public Boolean inscribir(Alumno a) {
		
		try {
			log.registrar(this, "inscribir ",a.toString());
			
			if(a.creditosObtenidos() < this.creditosRequeridos)
				return false;

			if(this.cupo <= this.inscriptos.size())
				return false;

			if(a.getCantidadCursosInscripto() >= 3)
				return false;
			
			if(this.inscriptos.contains(a))
				return false;
			
			this.inscriptos.add(a);
			a.inscripcionAceptada(this);
			return true;			
		} 
		catch (IOException e) {
			System.out.println("Sucedió un error al inscribir un alumno. Error:" + e.getMessage());
			e.printStackTrace();	
			return false;
		}		
	}
	
	
	/**
	 * Imprime los inscriptos en orden alfabetico
	 */
	public void imprimirInscriptos() {		
		try {
			log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
			this.imprimirInscriptosPor(null, "Orden Alfabético");
		} 
		catch (IOException e) {
			System.out.println("Sucedió un error al imprimir alumnos inscriptos. Error:" + e.getMessage());
			e.printStackTrace();
		}		
	}
	
	/**
	 * Imprime los inscriptos por numero de libreta
	 */
	public void imprimirInscriptosNroLibreta() {
		try {
			log.registrar(this, "imprimir listado por nro de libreta",this.inscriptos.size()+ " registros ");
			Comparator<Alumno> comparador = new AlumnoComparatorNroLibreta();
			this.imprimirInscriptosPor(comparador, "Orden por Número de Libreta");
		} 
		catch (IOException e) {
			System.out.println("Sucedió un error al imprimir alumnos inscriptos. Error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Imprime los inscriptos por creditos Obtenidos
	 */
	public void imprimirInscriptosCreditos() {
		try {
			log.registrar(this, "imprimir listado por cant creditos obtenidos",this.inscriptos.size()+ " registros ");
			Comparator<Alumno> comparador = new AlumnoComparatorCreditos();
			this.imprimirInscriptosPor(comparador, "Orden por Créditos Obtenidos");
		} 
		catch (IOException e) {
			System.out.println("Sucedió un error al imprimir alumnos inscriptos. Error:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * imprime los alumnos inscriptos en base al comparador enviado
	 * @param c Si es nulo, ordena con el criterio implementado por la clase Alumno, caso contrario ordena en base al comparador enviado.
	 * @param comparatorDescriptor Descripción a utilizar en la primer linea del reporte, para indicar el criterio de ordenamiento.
	 */
	private void imprimirInscriptosPor(Comparator<?super Alumno> c, String comparatorDescriptor) {
		System.out.println("IMPRIMIR ALUMNOS - " + comparatorDescriptor);
		System.out.println("CURSO - " + this.nombre);
		System.out.println("--------------------------------");
		if(this.inscriptos.size()==0)
			System.out.println("AVISO: SIN ALUMNOS INSCRIPTOS EN EL CURSO.");
		else
			System.out.println("Total alumnos inscriptos "+this.inscriptos.size()+".");
			
		System.out.println("--------------------------------");
		Collections.sort(this.inscriptos,c);
		
		System.out.println(this.inscriptos);
		
		}
	


}
