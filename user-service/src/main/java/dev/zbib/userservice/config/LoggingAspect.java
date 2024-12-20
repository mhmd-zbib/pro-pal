package dev.zbib.userservice.config;


import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    // Log method execution before the actual method is called (for all layers)
    @Before("execution(* dev.zbib.userservice..*(..))")
    public void logMethodExecution(JoinPoint joinPoint) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        Object[] args = joinPoint.getArgs();
        log.info("[{}] Executing method: {}({})", layer, methodName, formatArguments(args));
    }

    // Log method return value after the method is executed (for all layers)
    @AfterReturning(value = "execution(* dev.zbib.userservice..*(..))", returning = "result")
    public void logMethodReturn(
            JoinPoint joinPoint,
            Object result) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        log.info("[{}] Method executed: {} returned: {}", layer, methodName, result);
    }

    // Log method execution time and status after method execution (for all layers)
    @After("execution(* dev.zbib.userservice..*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        log.info("[{}] Method executed: {} completed", layer, methodName);
    }

    // Log exceptions thrown by any method (for all layers)
    @AfterThrowing(value = "execution(* dev.zbib.userservice..*(..))", throwing = "exception")
    public void logMethodException(
            JoinPoint joinPoint,
            Throwable exception) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        log.error("[{}] Method execution failed: {} with exception: {}", layer, methodName, exception.getMessage());
    }

    // Helper method to determine the layer based on the class name or method signature
    private String getLayer(JoinPoint joinPoint) {
        String className = getClassName(joinPoint);
        if (className.contains("controller")) {
            return "Controller";
        } else if (className.contains("service")) {
            return "Service";
        } else if (className.contains("repository")) {
            return "Repository";
        } else {
            return "Unknown Layer";
        }
    }

    // Helper method to remove the package prefix from the class name
    private String getClassName(JoinPoint joinPoint) {
        String className = joinPoint.getSignature()
                .getDeclaringTypeName();
        // Remove the package prefix `dev.zbib.userservice`
        return className.replace("dev.zbib.userservice", "");
    }

    // Helper method to get the method name without package prefix
    private String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature()
                .getName();
    }

    // Helper method to format arguments for logging
    private String formatArguments(Object[] args) {
        if (args == null || args.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
