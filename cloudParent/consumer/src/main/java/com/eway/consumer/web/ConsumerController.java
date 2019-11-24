package com.eway.consumer.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eway.consumer.pojo.User;


/**
* @author 程龙
* @version 创建时间：2019年11月22日 下午8:21:08
* @ClassName 类名称：
* @Description 类描述：
*/
@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    
//    @Autowired
//    private DiscoveryClient discoveryClient;
    
//    @Autowired
//    private RibbonLoadBalancerClient client;
    
    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        //集群环境下获取服务实例
        //Map<String,Map<String,ServiceInstance>>
        //根据服务ID获取实例
//        List<ServiceInstance> instances =  discoveryClient.getInstances("userService");
//        //从实例中取出ip和端口
//        ServiceInstance instance = instances.get(0);
//        String url = "http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;
//        System.out.println("url:"+instance.getHost()+"  "+instance.getPort());
//        User user = restTemplate.getForObject(url, User.class);
        
        //-----------以下是通过负载均衡器ribbon的做法-----------   算法：随机，轮询，hash,默认是轮询
//        ServiceInstance site = client.choose("userService");
//        String url = "http://"+site.getHost()+":"+site.getPort()+"/user/"+id;
        String url = "http://userService/user/"+id;  //此为最终方案，但需要在启动器类上的restTemplate加个注解@LoadBalanced
        System.out.println("=======================>"+url);
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
}
