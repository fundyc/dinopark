package com.dinopark.triceratops;

import brave.baggage.BaggageField;
import com.dinopark.triceratops.model.Triceratop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriceratopController {
    private static Logger log = LoggerFactory.getLogger(TriceratopController.class);

    @Autowired
    BaggageField versionField;

    @GetMapping("/triceratop")
    public Triceratop triceratop() {
        versionField.updateValue(getClass().getPackage().getImplementationVersion());
        log.info("Retrieve triceratop info");
        return new Triceratop("Trice", "blue", 12);
    }
}
