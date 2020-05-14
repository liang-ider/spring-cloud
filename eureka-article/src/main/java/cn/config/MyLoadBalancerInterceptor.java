package cn.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URI;

//@Configuration
public class MyLoadBalancerInterceptor implements ClientHttpRequestInterceptor {
    private LoadBalancerClient loadBalancerClient;
    private LoadBalancerRequestFactory requestFactory;

    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancerClient, LoadBalancerRequestFactory requestFactory) {
        this.loadBalancerClient = loadBalancerClient;
        this.requestFactory = requestFactory;
    }
    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancerClient) {
        this(loadBalancerClient,new LoadBalancerRequestFactory(loadBalancerClient));
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        final URI uri =httpRequest.getURI();
        String serviceName = uri.getHost();
        System.out.println("进入自定义的请求拦截器中"+serviceName);
        Assert.state(serviceName!=null,"Request URI does not contain a valid hostname:"+uri);
        return this.loadBalancerClient.execute(serviceName,requestFactory.createRequest(httpRequest,bytes,clientHttpRequestExecution));
    }
}
