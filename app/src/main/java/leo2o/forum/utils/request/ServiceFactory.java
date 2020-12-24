package leo2o.forum.utils.request;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final Map<Class<?>, Object> cache = new HashMap<>();

    public static <S> S getService(Class<S> sClass) {
        if (cache.containsKey(sClass)) {
            return sClass.cast(cache.get(sClass));
        }
        S service = retrofit.create(sClass);
        cache.put(sClass, service);
        return service;
    }
}
