package br.com.parsejson.unit;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.parsejson.core.JSONParse;
import br.com.parsejson.entityTest.Batman;
import br.com.parsejson.exception.ParseException;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import junit.framework.Assert;

public class ParseToJsonTest {
	private Batman batman;

	@Before
	public void beforeClass(){
		Fixture.of(Batman.class).addTemplate("valido", new Rule(){{
		    add("nome", random("Daniel","Larissa", "Rafael"));
		    add("cidade", "Rio de janeiro");
		    add("valorInvestimento", random(Long.class, range(1L, 10000000000L)));
		    add("dataNascimento", Calendar.getInstance());
		}});
		
		batman = Fixture.from(Batman.class).gimme("valido");
		
		
	}
	
	
	@Test
	public void dadoUmaEntidadeBatmanDeveMeRetornarUmJsonDeBatman() throws ParseException{
	
		JSONParse parse = new JSONParse(batman);
		String json = parse.toJson();
		Assert.assertEquals("String deve conter 'batman':{ ", json.contains("\"batman\":{"));
	}
	
	@After
	public void afterClass(){
		
	}
}
