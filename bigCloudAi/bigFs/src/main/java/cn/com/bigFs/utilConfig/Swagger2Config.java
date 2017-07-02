package cn.com.bigFs.utilConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

    @EnableSwagger2
	@Configuration
	public class Swagger2Config {

		@Bean
		public Docket createRestApi() {
			return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.select()
					.apis(RequestHandlerSelectors.basePackage("cn.com.bigFs.ctrl"))
					.paths(PathSelectors.any())
					.build();
		}
		private ApiInfo apiInfo() {
			return new ApiInfoBuilder()
					.title("bigFs RESTful API")
					.version("2.0")
					.description("API 接口")
					.build();
		}
}
