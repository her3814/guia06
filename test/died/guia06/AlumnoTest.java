package died.guia06;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class AlumnoTest {
	private Alumno alumnoA = new Alumno("Hernan",12345);
	private Curso cursoA = new Curso();
	private Curso cursoB = new Curso();
	private Curso cursoC = new Curso();
	
	public AlumnoTest() {
		
	}
@Before
public void init() {
    System.out.println("@Before");
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
		
		var cursoD = new Curso();
		
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
