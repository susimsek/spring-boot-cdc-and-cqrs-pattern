package com.spring.denormalizer.aop;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class TimedAspect {

  private final MeterRegistry registry;

  @Around("execution (@io.micrometer.core.annotation.Timed * *.*(..))")
  public Object timedMethod(ProceedingJoinPoint joinPoint) throws Throwable {

    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();
    String metricName = String.format("%s.%s", className, methodName);

    Timer.Sample timer = Timer.start(registry);

    return Try.of(joinPoint::proceed)
              .andFinally(() -> stopTimer(className, methodName, metricName, timer));
  }

  private void stopTimer(String className, String methodName, String metricName, Timer.Sample timer) {
    long elapsedTime = timer.stop(getTimer(className, methodName, metricName));
    long elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);

    log.debug("Execution of method : {} took : {} milliseconds", metricName, elapsedTimeInSeconds);
  }

  private Timer getTimer(String className, String methodName, String metricName) {
    return Timer.builder(metricName)
                .tags(Tags.of("class", className,
                              "method", methodName))
                .publishPercentiles(0.75, 0.95, 0.99)
                .register(registry);
  }

}