package im.baas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationServer {

    private static final Logger log = LoggerFactory.getLogger(ApplicationServer.class);
    public static void main(String[] args) {
        log.info("main | Starting Application Server...");
        SpringApplication.run(ApplicationServer.class, args);
    }

}
