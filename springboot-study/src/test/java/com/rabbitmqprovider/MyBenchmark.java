package com.rabbitmqprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.HotspotThreadProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(10)
public class MyBenchmark {

    String string = "";
    StringBuilder stringBuilder = new StringBuilder();

    String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        ObjectMapper GLOBAL_MAP = new ObjectMapper();
        ThreadLocal<ObjectMapper> GLOBAL_MAP_THREAD = new ThreadLocal<>();
    }

    @Benchmark
    public Map globalTest(BenchmarkState state) throws Exception{
        return state.GLOBAL_MAP.readValue(json, Map.class);
    }

    @Benchmark
    public Map globalTestThreadLocal(BenchmarkState state) throws Exception{
        if(null == state.GLOBAL_MAP_THREAD.get()){
            state.GLOBAL_MAP_THREAD.set(new ObjectMapper());
        }
        return state.GLOBAL_MAP_THREAD.get().readValue(json, Map.class);
    }

    @Benchmark
    public Map localTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Map.class);
    }
    
    
    
//    @Benchmark
//    public String stringAdd() {
//        for (int i = 0; i < 1000; i++) {
//            string = string + i;
//        }
//        return string;
//    }
//
//    @Benchmark
//    public String stringBuilderAppend() {
//        for (int i = 0; i < 1000; i++) {
//            stringBuilder.append(i);
//        }
//        return stringBuilder.toString();
//    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .addProfiler(HotspotThreadProfiler.class)
                .build();
        new Runner(opt).run();
    }
    
}
