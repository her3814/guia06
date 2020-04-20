package died.guia06;

import java.util.ArrayList;
import java.util.List;

import died.guia06.util.InscripcionAlumnoYaInscriptoException;
import died.guia06.util.InscripcionCreditosInsuficientesException;
import died.guia06.util.InscripcionCupoCompletoException;
import died.guia06.util.InscripcionExcesoMismoCicloException;
import died.guia06.util.RegistroAuditoriaException;

public class App {
	private final String NL = System.lineSeparator() + "--------------------------------" + System.lineSeparator();
	
	private List<Alumno> alumnos;
	private List<Curso> cursos;
	
	public List<Curso>getCursos() {
		return this.cursos;
	}
	
	public Curso getCurso(int id) {
		return this.cursos.stream().filter(c -> c.getId()==id).findFirst().orElse(null);
	}

	public Alumno getAlumno(int nroLibreta) {
		return this.alumnos.stream().filter(a -> a.getNroLibreta()==nroLibreta).findFirst().orElse(null);
	}
	
	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}
	
	public static void main(String[] args) {
		var app = new App();
		
		//Registro Alumno a curso de ciclo lectivo 2020
		app.registrar(1, 1);
		
		//Registro Alumno ya inscripto
		app.registrar(1, 1);

		//Registro Alumno a curso de ciclo lectivo 2020
		app.registrar(7,1);
		//Registro Alumno a curso de ciclo lectivo 2020
		app.registrar(8,1);

		//Registro Alumno a curso teniendo 3 cursos del mismo ciclo lectivo
		app.registrar(3,1);
		
		//Registro Alumnos
		app.registrar(1, 2);
		app.registrar(1, 3);
		app.registrar(1, 4);
		app.registrar(8,4);
		
		//Inscribo alumno, superando el cupo del curso
		app.registrar(1, 5);

		//ImprimirInscriptos
		app.printNL();
		app.getCurso(1).imprimirInscriptos();
		
		app.printNL();
		app.getCurso(1).imprimirInscriptosNroLibreta();
		
		//Apruebo Alumno
		app.aprobar(1, 2);

		app.printNL();
		app.getCurso(1).imprimirInscriptosCreditos();


		//Registro Alumno en nuevo curso teniendo los puntos necesarios
		app.registrar(2, 2);
		
		//Registro Alumno en nuevo curso sin tener los puntos necesarios
		app.registrar(2, 3);

		//Apruebo Alumnos
		app.aprobar(1, 3);
		app.aprobar(1, 4);
		

		//Registro Alumnos
		app.registrar(2, 3);
		app.registrar(2, 4);

		//Apruebo Alumnos
		app.aprobar(2, 3);
		app.registrar(2, 4);

		app.printNL();
		app.getCurso(2).imprimirInscriptosCreditos();
		
		app.registrar(4,4);
		
		//Imprimo los detalles de cada alumno
		app.printNL();
		System.out.println("DETALLES FINALES DE ALUMNOS" + System.lineSeparator());
			System.out.println(app.getAlumnos());

			//Imprimo la cantidad de cursos por alumno por ciclo lectivo
			app.printNL();
			System.out.println("CANT DE CURSOS INSCRIPTOS POR CICLO LECTIVO DE ALUMNOS" + System.lineSeparator());

			for(Alumno a : app.getAlumnos()) {
				app.printNL();
				System.out.println(a.getNroLibreta() +" - " +a.getNombre()  + System.lineSeparator());
				System.out.println("2020: " + a.getCantidadCursosInscripto(2020));
				System.out.println("2021: " + a.getCantidadCursosInscripto(2021));
				System.out.println("2022: " + a.getCantidadCursosInscripto(2022));
			}
			
			
	}
	

	
	/*
	 * Inicializo cursos y alumno para usar en el programa
	 */
	public App() {
		super();
		this.alumnos=new ArrayList<Alumno>();
		this.cursos=new ArrayList<Curso>();
		
		this.cursos.add( new Curso(1, "Curso PHP - 1", 2020,4,10,0));
		this.cursos.add( new Curso(7, "Curso Ingreso", 2020,4,10,0));
		this.cursos.add( new Curso(8, "Curso Matematica", 2020,4,10,0));
		this.cursos.add( new Curso(2, "Curso PHP - 2", 2021,2,5,10));		
		this.cursos.add( new Curso(3, "Curso JAVA - 1",2020,5,5,0));
		this.cursos.add(new Curso(4, "Curso JAVA - 2",2021,5,5,5));		
		
		this.alumnos.add(new Alumno("Hernan Lopez",1));
		this.alumnos.add(new Alumno("Jose Lopez",2));
		this.alumnos.add(new Alumno("Juan Lopez",3));
		this.alumnos.add(new Alumno("Hernan Lopez",4));
		this.alumnos.add(new Alumno("Armando Lopez",5));
	}
	
	public void printNL() {
		System.out.println(this.NL);
		
	}
	public void aprobar(int idCurso, int libreta) {
		this.printNL();
		Curso curso = this.getCurso(idCurso);
		if(curso==null)
			System.out.println("No se encuentra registrado el curso id: " + idCurso);
		else {
			Alumno alumno = this.getAlumno(libreta);
			if(alumno==null)
				System.out.println("No se encuentra el alumno libreta n°: " + libreta);
			else {
				try {
					alumno.aprobar(curso);
					
					if(alumno.aproboCurso(curso)) 
					{
						System.out.println("REGISTRO DE APROBACIÓN DE ALUMNO EN CURSO");
						System.out.println("CURSO:" + curso.toString());
						System.out.println("SE APROBÓ AL ALUMNO: " + alumno.getNombre());
						System.out.println("CREDITOS OBTENIDOS: " + curso.getCreditos());
						System.out.println("TOTAL DE CREDITOS: " + alumno.creditosObtenidos());
					}
					else {
						System.out.println("NO SE PUDO APROBAR AL ALUMNO LIBRETA N°: " + alumno.getNroLibreta() + " EN EL CURSO " + curso.getNombre());
					}
					
				}
				catch(IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				
				
			}
			
		}
		
	}
	
	public void registrar(int idCurso, int libreta) {
		this.printNL();
		Curso curso = this.getCurso(idCurso);
		if(curso==null)
			System.out.println("No se encuentra registrado el curso id: " + idCurso);
		else {
			Alumno alumno = this.getAlumno(libreta);
			if(alumno==null)
				System.out.println("No se encuentra el alumno libreta n°: " + libreta);
			
			else {
				
				try {
					curso.inscribirAlumno(alumno);
					System.out.println("INSCRIPCION REALIZADA");
					System.out.println(curso.toString());
					System.out.println(alumno.toString());
				} catch (InscripcionCreditosInsuficientesException | InscripcionCupoCompletoException
						| InscripcionExcesoMismoCicloException | InscripcionAlumnoYaInscriptoException
						| RegistroAuditoriaException e) {
					// TODO Auto-generated catch block
					System.out.println("NO SE PUDO INSCRIBIR AL ALUMNO LIBRETA N°: " + alumno.getNroLibreta() + " EN EL CURSO " + curso.getNombre());
					System.out.println("RAZON: " + e.getMessage());
				
				}
				
			}
			
		}
		
		
	}
}
