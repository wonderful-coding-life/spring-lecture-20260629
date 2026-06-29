package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LombokApplication implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Product product = Product.builder().name("갤럭시와치").description("삼성이 많은 스마트 와치").build();
        product.setPrice(120000);

        log.error("app error log -  DB is broken");
        log.warn("app warn log - DB is slow");
        log.info("product is ordered by credit card");
        log.debug("problematic credit card number " + 12345);
        log.trace("logout every thing...");
    }
}
