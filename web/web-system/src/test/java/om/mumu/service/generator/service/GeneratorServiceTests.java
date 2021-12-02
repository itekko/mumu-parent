package om.mumu.service.generator.service;

import cn.hutool.core.io.FileUtil;
import com.mumu.service.generator.config.GeneratorProperies;
import com.mumu.service.generator.service.GeneratorService;
import com.mumu.web.system.SystemApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 自动生成代码测试类
 * @author ekko
 */
@Slf4j
@SpringBootTest(classes = SystemApplication.class)
public class GeneratorServiceTests {

    @Autowired
    private GeneratorService generatorService;

    @Autowired
    private GeneratorProperies generatorProperies;


    @Test
    public void testGeneratorCode(){
        log.info("代码生成开始");
        FileUtil.writeBytes(generatorService.generatorCode(generatorProperies.getTables()),generatorProperies.getOutPutPath());
    }

}


