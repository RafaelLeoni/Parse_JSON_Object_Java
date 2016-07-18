package br.com.parse;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.parse.core.exception.ParseException;
import br.com.parse.entity.Batman;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

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
	}
	
	@After
	public void afterClass(){
		
	}
}
