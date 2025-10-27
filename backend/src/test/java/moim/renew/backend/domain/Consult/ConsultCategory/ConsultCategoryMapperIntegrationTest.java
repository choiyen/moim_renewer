package moim.renew.backend.domain.Consult.ConsultCategory;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@MapperScan("moim.renew.backend.domain.Consult.ConsultCategory.Mapper")
@Transactional
public class ConsultCategoryMapperIntegrationTest
{
}
