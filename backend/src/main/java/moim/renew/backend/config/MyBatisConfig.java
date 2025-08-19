package moim.renew.backend.config;

import javax.sql.DataSource;

import moim.renew.backend.Approval.Handler.StringArrayJsonTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan("moim.renew.backend") // 모든 Mapper 인터페이스 스캔
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);

        // sql-map-config.xml 설정 적용
        factory.setConfigLocation(
                new PathMatchingResourcePatternResolver().getResource("classpath:sql-map-config.xml")
        );

        // Mapper XML 파일 지정
        factory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:Mapper/*.xml")
        );
        factory.setTypeHandlersPackage("moim.renew.backend.Approval.Handler");

        return factory.getObject();
    }
}

