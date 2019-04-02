package com.epam.productsreview.config;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.Span;

/**
 * Created by saurabh on 1/4/19.
 */
@Configuration
public class ZipkinConfiguration {

    @Bean
    public Sampler sampler() {
        return Sampler.ALWAYS_SAMPLE;
    }


}
