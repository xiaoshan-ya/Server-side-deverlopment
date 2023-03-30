package com.thoughtmechanix.licenses.clients;


import com.thoughtmechanix.licenses.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OrganizationDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient; //在spring中自动生成了该对象，注入到spring中，该对象是注册中心（nacos）

    public Organization getOrganization(String organizationId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("organizationservice"); //向注册中心（nacos）获取远程服务的详细信息

        if (instances.size() == 0) return null;
        String serviceUri = String.format("%s/v1/organizations/%s", instances.get(0).getUri().toString(), organizationId);
        //取远程服务中的其中一个，在选中的服务中拼接出一个端口号（organization实例的URL）
        //instances.get(0):挑选当前方法的第一种，调用服务方法见：12-PPT/11页


        System.out.println("!!!! SERVICE URI:  " + serviceUri); //打印在K8S/Pods/licensingservice中的日志中
//        http://10.1.3.224:8080/v1/organizations/e254f8c-c442-4ebe-a82a-e2fc1d1ff78a


        ResponseEntity<Organization> restExchange = //向organization发出请求，对远程服务的调用
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, Organization.class, organizationId);

        return restExchange.getBody();
    }
}
