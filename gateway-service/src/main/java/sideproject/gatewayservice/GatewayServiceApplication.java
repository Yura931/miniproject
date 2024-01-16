package sideproject.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	// spring boot 3버전 부터 HttpExchangeRepository 변경
	// 이전 버전 HttpTraceRepository 사용
	public HttpExchangeRepository exchangeRepository() {
		return new InMemoryHttpExchangeRepository();
	}
}
