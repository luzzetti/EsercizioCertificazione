package it.luzzetti.interceptors;

import jdk.nashorn.internal.objects.annotations.Property;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@Profilable
@Priority(Interceptor.Priority.PLATFORM_AFTER+5)
public class ProfileInterceptor {

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @AroundInvoke
    public Object misuraDurataMetodo(InvocationContext ic) throws Exception {
        long tempoDiInizio = System.currentTimeMillis();
        try {
            return ic.proceed();
        } finally {
            long delta = System.currentTimeMillis() - tempoDiInizio;
            logger.info("[DURATA] + " + ic.getMethod().getName() + ": " + delta + "ms");
        }
    }


}
