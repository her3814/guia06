package died.guia06.util;

import java.io.IOException;

public class RegistroAuditoriaException extends Exception{
	
	public RegistroAuditoriaException(String message, IOException e) {
		super(message,e);
	}

}
