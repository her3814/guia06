package died.guia06;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlumnoTest {
	@BeforeEach
	public void init() {
	    System.out.println("@Before");
	}
	
	private Alumno alumnoA = new Alumno("Hernan",12345);
	private Curso cursoA = new Curso(1,"CursoA",2020,10,5,0);
	private Curso cursoB = new Curso(2,"CursoB",2020,5,5,5);
	private Curso cursoC = new Curso(3,"CursoC",2020,1,5,0);
	
	public AlumnoTest() {
		
	}
	@Test
	public void testCrearAlumnoSinNombre() {
		try {
			new Alumno(null,12345);			
		}catch(Exception e) {
			assertTrue(e.getClass() == IllegalArgumentException.class);
		}
	}
	
	@Test
	public void testCrearAlumnoSinLibreta() {
		try {
			new Alumno("Hernan Lopez",null);			
		}catch(Exception e) {
			assertTrue(e.getClass() == IllegalArgumentException.class);
		}
	}
	
	@Test
	public void testCrearAlumno() {
		var alumno = new Alumno("Hernan Lopez",12345);
		assertFalse(alumno==null);
	}
	
	
	@Test
	void testCreditosObtenidosSinCursosAprobados() {
		Alumno alumno = new Alumno("Hernan Lopez",21973);
		assertTrue(alumno.creditosObtenidos() == 0);
		assertFalse(alumno.creditosObtenidos() < 0);
		assertFalse(alumno.creditosObtenidos() > 0);
	}
	
	@Test
	void testCreditosObtenidos() {				
		assertTrue(alumnoA.creditosObtenidos() == 0);
	}
	
	@Test
	void testAprobarCursoSinInscribirse() {
		try {			
			alumnoA.aprobar(cursoC);
		}
		catch(Exception e) {
			assertTrue(e.getClass() == IllegalArgumentException.class);
		}
	}
	
	@Test
	void equalsAlumnoLibretaIgual() {
		Alumno alumno = new Alumno("Walter",12345);
		assertTrue(alumnoA.equals(alumno));
	}

	@Test
	void equalsAlumnoLibretaDistinta() {
		Alumno alumno = new Alumno("Hernan",12346);
		assertFalse(alumnoA.equals(alumno));
	}
	@Test
	void testInscripcionAceptadaCursandoTres() {
		
		alumnoA.inscripcionAceptada(cursoB);
		alumnoA.inscripcionAceptada(cursoC);
		
		var cursoD = new Curso(4,"Curso4",2020,5,1,0);
		
		try {			
			alumnoA.aprobar(cursoD);
		}
		catch(Exception e) {
			assertTrue(e.getClass() == IllegalArgumentException.class);
		}
		
	}

	@Test
	void testInscripcionAceptada() {
		
		alumnoA.inscripcionAceptada(cursoA);
		
		try {
			alumnoA.inscripcionAceptada(cursoB);			
		}
		catch(IllegalArgumentException e){
			assertFalse(e.getClass() == IllegalArgumentException.class);	
		}
		
		assertTrue(alumnoA.inscriptoEn(cursoB));
	}
	
	
	@Test
	void testAlumnoCantidadCursando() {
		
		assertTrue(alumnoA.getCantidadCursosInscripto()==0);
		assertFalse(alumnoA.getCantidadCursosInscripto()!=0);
		
		alumnoA.inscripcionAceptada(cursoB);
		
		assertTrue(alumnoA.getCantidadCursosInscripto()==1);
		assertFalse(alumnoA.getCantidadCursosInscripto()!=1);
	}

	@Test
	void testAprobarCurso() {
		alumnoA.inscripcionAceptada(cursoA);
		this.alumnoA.aprobar(cursoA);
		assertTrue(this.alumnoA.aproboCurso(cursoA));
		assertFalse(this.alumnoA.aproboCurso(cursoB));
	}
}
