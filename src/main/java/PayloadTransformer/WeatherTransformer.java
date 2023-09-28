package PayloadTransformer;


@FunctionalInterface
public interface WeatherTransformer<T, U, R> {
	R transform(T input, U input2);
}
