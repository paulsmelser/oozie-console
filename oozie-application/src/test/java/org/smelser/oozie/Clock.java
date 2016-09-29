package org.smelser.oozie;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.util.StopWatch;

public class Clock {
    StopWatch watch;

    public static Clock newClock(){
        Clock clock = new Clock();
        clock.watch = new StopWatch();
        return clock;
    }

    public void start(){
        watch.start();
    }

    public void stop(){
        stop();
    }

    public <T, R> R start(Function<T,R> function, T arg){
        watch.start();
        R response = function.apply(arg);
        watch.stop();
        return response;
    }

    public <T, R> R start(String taskName, Function<T,R> function, T arg){
        watch.start(taskName);
        R response = function.apply(arg);
        watch.stop();
        return response;
    }

    public <T, U, R> R start(BiFunction<T, U, R> function, T arg, U arg2){
        watch.start();
        R response = function.apply(arg, arg2);
        watch.stop();
        return response;
    }

    public <T, U, R> R start(String taskName, BiFunction<T, U, R> function, T arg, U arg2){
        watch.start(taskName);
        R response = function.apply(arg, arg2);
        watch.stop();
        return response;
    }

    public <T> T start(Supplier<T> consumer) throws Exception {
        watch.start();
        T result = consumer.get();
        watch.stop();
        return result;
    }

    public <T> T start(String taskName, Supplier<T> consumer) throws Exception {
        watch.start(taskName);
        T result = consumer.get();
        watch.stop();
        return result;
    }

    public <T> void start(Consumer<T> consumer, T arg){
        watch.start();
        consumer.accept(arg);
        watch.stop();
    }

    public <T> void start(String taskName, Consumer<T> consumer, T arg){
        watch.start(taskName);
        consumer.accept(arg);
        watch.stop();
    }

    public void start(Runnable runnable){
        watch.start();
        runnable.run();
        watch.stop();
    }

    public void start(String taskName, Runnable runnable){
        watch.start(taskName);
        runnable.run();
        watch.stop();
    }

}
