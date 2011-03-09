package ar.com.nny.base.configuration;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.collections.map.HashedMap;

import java.lang.reflect.Method;
import java.util.Map;

public class CachedMethodInterceptor implements MethodInterceptor {

    private Map<Method, Object> cache = new HashedMap();


    public Object intercept(Object service, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object cached = cache.get(method);
        if ( cached != null ) {
            return cached;
        }

        Object result = methodProxy.invokeSuper(service, args);
        cache.put(method, result);
        return result;
    }
}