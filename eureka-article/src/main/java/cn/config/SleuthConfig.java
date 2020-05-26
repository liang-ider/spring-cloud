package cn.config;

import brave.http.HttpAdapter;
import brave.http.HttpSampler;
import org.springframework.cloud.sleuth.instrument.web.ServerSampler;
import org.springframework.cloud.sleuth.instrument.web.SkipPatternProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

//@Configuration
public class SleuthConfig {
    @Bean(name = ServerSampler.NAME)
    public HttpSampler myHttpSampler(SkipPatternProvider patternProvider) {
        Pattern pattern = patternProvider.skipPattern();
        return new HttpSampler(){
            @Override
            public <Req> Boolean trySample(HttpAdapter<Req, ?> httpAdapter, Req req) {
                String url = httpAdapter.url(req);
                boolean skip = pattern.matcher(url).matches();
                if(skip) {
                    return false;
                }
                return null;
            }
        };
    }
}
