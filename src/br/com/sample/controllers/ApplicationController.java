package br.com.sample.controllers;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

public class ApplicationController {

	private final Result result;
	
	public ApplicationController(Result result) {
		this.result = result;
	}
	
	public void respondWithJson(Object o) {
		result.use( Results.json() ).withoutRoot().from(o).recursive().serialize();
	}
	
	public void respondWithJson(Object o, String baseName) {
		result.use( Results.json() ).from(o, baseName).recursive().serialize();
	}
	
	public void respondWithError() {
		result.use( Results.status() ).badRequest("Um erro aconteceu no servidor.");
	}
	
	public void respondWithNotFound() {
		result.use(Results.http()).setStatusCode(404);
		result.use(Results.http()).addHeader("message", "A busca nao retornou nenhum resultado."); 
	}
	
}
