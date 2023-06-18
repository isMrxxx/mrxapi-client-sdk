package com.mrx.mrxapiclientsdk;

import com.mrx.mrxapiclientsdk.client.MrxApiClient;
import com.mrx.mrxapiclientsdk.model.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: Mrx
 * @DateTime: 2023/6/5 13:54
 * @Description:
 */
@Configuration
@ConfigurationProperties("mrxapi.client")
@Data
@ComponentScan
public class MrxapiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public MrxApiClient mrxApiClient(){
        return new MrxApiClient(accessKey,secretKey);
    }
}
