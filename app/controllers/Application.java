package controllers;

import models.Hashtag;
import models.JogadaInvalidaException;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	private static Hashtag jogo = new Hashtag();

	public static Result index() {
		return ok(index.render("que tal jogar?", jogo));
	}

	public static Result joga() {
		DynamicForm requestData = Form.form().bindFromRequest();
	    int x = Integer.parseInt(requestData.get("x"));
	    int y = Integer.parseInt(requestData.get("y"));
	    try {
			jogo.jogar(x, y);
		} catch (JogadaInvalidaException e) {
			return badRequest(index.render("jogada inválida: " + e.getMessage(), jogo));
		}
		return ok(index.render("jogo em andamento!", jogo));
	}
	
	public static Result novoJogo(){
		jogo = new Hashtag();
		return ok(index.render("jogo novinho em folha", jogo));
	}
}
