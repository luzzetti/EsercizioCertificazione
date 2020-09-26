package it.luzzetti.interceptors;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@Loggable
@Priority(Interceptor.Priority.PLATFORM_AFTER+10)
public class LoggingInterceptor {

    //    @Inject
//    private Logger logger;
    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @PostConstruct
    public void loggaMetodoInvocato(InvocationContext ic) throws Exception {
        logger.info("[PostConstruct] - " + ic.getTarget().getClass().getName());
        try {
            ic.proceed();
        } finally {
            // Non fare niente...
        }
    }

    @AroundConstruct
    private void intercettaChiamateAlCostruttore(InvocationContext ic) throws Exception {
        logger.info("[Costruttore] - " + ic.getConstructor().getName());
        try {
            ic.proceed();
        } finally {
            // Non fare niente...
        }
    }

    @AroundInvoke
    private Object intercettaChiamateAiMetodi(InvocationContext ic) throws Exception {
        logger.info("[Start] - " + ic.getTarget().getClass().getName() + " --> " + ic.getMethod().getName());
        try {
            return ic.proceed();
        } finally {
            logger.info("[ End ] - " + ic.getTarget().getClass().getName() + " --> " + ic.getMethod().getName());
        }
    }

}
