package bombonato.genmap.batch.config;

import bombonato.genmap.business.service.CdsService;
import bombonato.genmap.business.service.MapJoinService;
import bombonato.genmap.business.service.impl.CdsServiceImpl;
import bombonato.genmap.business.service.impl.MapJoinServiceImpl;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing(modular=true)
public class AppConfig {

    @Bean
    public MapJoinService mapJoinService() {
        return new MapJoinServiceImpl();
    }

    @Bean
    public CdsService cdsService() {
        return new CdsServiceImpl();
    }

}
