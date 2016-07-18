package br.com.parse.core.build;

public interface Build {

	<T> T in(Class<T> clazz);
}
