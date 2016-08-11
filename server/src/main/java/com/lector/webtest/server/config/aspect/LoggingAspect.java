package com.lector.webtest.server.config.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by Reza Mousavi reza.mousavi@lector.dk on 8/11/2016
 */
@Aspect
@Component
class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class);
    private static final String POINT_CUT = "execution(* com.lector.webtest.server.service.*.*(..))";

    @Pointcut(POINT_CUT)
    public void allServicesWithinServicePackage() {
    }

    @Around(POINT_CUT)
    public void logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        pjp.proceed();
        stopWatch.stop();
        logger.debug("Execution of method : " + pjp.getSignature() + " took : "
                + stopWatch.getLastTaskTimeMillis() + " in (ms).");
    }

}
