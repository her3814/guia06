package died.guia06;


import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CursoTest {
	private Curso cursoA;
	private Curso cursoB;
	private Curso cursoC;
	
	private Alumno alumno;
	private Alumno alumno2;
	
	private ByteArrayOutputStream outContent;
	
	@BeforeEach public void initialize() {
		this.cursoA = new Curso(1,"Curso Java",2020,3,5,0);
		this.cursoB = new Curso(2,"Curso Java 2",2020,5,10,5);
		this.cursoC = new Curso(3,"Curso PHP",2021,5,1,15);
		
		this.alumno = new Alumno("Hernan Lopez",12345);
		this.alumno2 = new Alumno("Armando Lopez",12346);
	}

	
	@Test
	public void testGetNombre(){
		assertEquals(this.cursoA.getNombre(), "Curso Java");
	}

	@Test
	public void testGetCicloLectivo(){
		assertEquals(this.cursoA.getCicloLectivo(), 2020);
	}

	@Test
	public void testGetId(){
		assertEquals(this.cursoA.getId(), 1);
	}
	
	
	@Test
	public void testInscribirAlumno() {
		assertTrue(this.cursoA.inscribir(this.alumno));			
	}

	/**
	 * Si se intenta inscribir dos veces a un alumno debe devolver falso.
	 */
	@Test
	public void testInscribirAlumnoPorDos() {
		assertTrue(this.cursoA.inscribir(this.alumno));	
		assertFalse(this.cursoA.inscribir(this.alumno));		
	}
	
	@Test
	public void testIncribirSinCreditos() {
		assertFalse(this.cursoB.inscribir(this.alumno));
	}
	
	@Test
	public void testInscribirAlumnoConCreditos() {
		this.cursoA.inscribir(this.alumno);		
		this.alumno.aprobar(cursoA);
		assertTrue(this.cursoB.inscribir(this.alumno));	
		assertFalse(this.cursoC.inscribir(this.alumno));
	}
	
	@Test
	public void testSetCupoMenorAInscriptos() {
		this.cursoA.inscribir(this.alumno);
		this.cursoA.inscribir(this.alumno2);
		try {
			this.cursoA.setCupo(1);
		}
		catch(IllegalArgumentException e) {
			assertEquals("Ya tiene mas inscriptos que el cupo que desea setear al curso.",e.getMessage());
		}
	}
	
	@Test
	public void testSetCupo() {
		this.cursoA.inscribir(this.alumno);
		this.cursoA.inscribir(this.alumno2);
		try {
			this.cursoA.setCupo(8);
		}
		catch(IllegalArgumentException e) {
			assertEquals("Ya tiene mas inscriptos que el cupo que desea setear al curso.",e.getMessage());
		}
	}
	
	@Test
	public void testIncribirMasDe3Cursos() {
		Curso curso1 = new Curso(2,"Curso Ingreso",2020,1,5,0);
		Curso curso2 = new Curso(3,"Curso Matematica Basica",2020,1,5,0);
		Curso curso3 = new Curso(4,"Curso Fisica",2020,1,5,0);
		
		assertTrue(this.cursoA.inscribir(this.alumno));
		assertTrue(curso1.inscribir(this.alumno));
		assertTrue(curso2.inscribir(this.alumno));
		assertFalse(curso3.inscribir(this.alumno));
	}
	
	@Test
	public void testInscribirMasDelCupo() {
		var alumno3 = new Alumno("Alumno Generico",12347);
		var alumno4 = new Alumno("Alumno Generico",12348);
		
		assertTrue(this.cursoA.inscribir(this.alumno));	
		assertTrue(this.cursoA.inscribir(this.alumno2));	
		assertTrue(this.cursoA.inscribir(alumno3));	
		assertFalse(this.cursoA.inscribir(alumno4));			
	}
	

	@Test
	public void testInscribirAlumnosIgualLibreta() {
		var alumno3 = new Alumno("Alumno Generico",12345);
		
		assertTrue(this.cursoA.inscribir(this.alumno));	
		assertFalse(this.cursoA.inscribir(alumno3));			
	}
	
	@Test
	public void testImprimirInscriptosNombre() {

		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		this.cursoA.inscribir(alumno);
		this.cursoA.inscribir(alumno2);
	
		this.cursoA.imprimirInscriptos();
		
		
		var string = "IMPRIMIR ALUMNOS - Orden Alfabético" + System.lineSeparator() +
				"CURSO - Curso Java" + System.lineSeparator() +
				"--------------------------------" + System.lineSeparator() +
				"Total alumnos inscriptos 2." + System.lineSeparator() +
				"--------------------------------" + System.lineSeparator() +
				"[Libreta Universitaria: 12346" + System.lineSeparator() +
				"Nombre: Armando Lopez" + System.lineSeparator() +
				"--------------------------------" +System.lineSeparator() + 
				", Libreta Universitaria: 12345" + System.lineSeparator() +
				"Nombre: Hernan Lopez" + System.lineSeparator() +
				"--------------------------------" +System.lineSeparator() + 
				"]" + System.lineSeparator();

		assertEquals(string,outContent.toString());
	}
	
	@Test
	public void testImprimirInscriptosLibreta() {

		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		this.cursoA.inscribir(alumno);
		this.cursoA.inscribir(alumno2);
	
		this.cursoA.imprimirInscriptosNroLibreta();		
		
		var string = "IMPRIMIR ALUMNOS - Orden por Número de Libreta" + System.lineSeparator() +
				"CURSO - Curso Java" + System.lineSeparator() +
				"--------------------------------" + System.lineSeparator() +
				"Total alumnos inscriptos 2." + System.lineSeparator() +
				"--------------------------------" + System.lineSeparator() +
				"[Libreta Universitaria: 12345" + System.lineSeparator() +
				"Nombre: Hernan Lopez" + System.lineSeparator() +
				"--------------------------------" +System.lineSeparator() + 
				", Libreta Universitaria: 12346" + System.lineSeparator() +
				"Nombre: Armando Lopez" + System.lineSeparator() +
				"--------------------------------" +System.lineSeparator() + 
				"]" + System.lineSeparator();

		assertEquals(string,outContent.toString());
	}
	
	@Test
	public void testImprimirInscriptosCreditos() {

		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		this.cursoA.inscribir(alumno);
		this.cursoA.inscribir(alumno2);
	
		this.alumno2.aprobar(cursoA);

		this.cursoA.imprimirInscriptosCreditos();		
		
		var string = "IMPRIMIR ALUMNOS - Orden por Créditos Obtenidos" + System.lineSeparator() +
				"CURSO - Curso Java" + System.lineSeparator() +
				"--------------------------------" + System.lineSeparator() +
				"Total alumnos inscriptos 2." + System.lineSeparator() +
				"--------------------------------" + System.lineSeparator() +
				"[Libreta Universitaria: 12346" + System.lineSeparator() +
				"Nombre: Armando Lopez" + System.lineSeparator() +
				"--------------------------------" +System.lineSeparator() + 
				", Libreta Universitaria: 12345" + System.lineSeparator() +
				"Nombre: Hernan Lopez" + System.lineSeparator() +
				"--------------------------------" +System.lineSeparator() + 
				"]" + System.lineSeparator();
		
		assertEquals(string,outContent.toString());
	}
}
