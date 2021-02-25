package com.dinopark.dinosaurs;

import brave.baggage.BaggageField;
import com.dinopark.model.dinosaurs.Dinosaurs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DinosaursController {
    private static Logger log = LoggerFactory.getLogger(DinosaursController.class);

    @Autowired
    BaggageField versionField;

    @GetMapping("/dinosaurs")
    public Dinosaurs dinosaurs() {
        log.info("Retrieve dinosaurs info");
        versionField.updateValue(getClass().getPackage().getImplementationVersion());
        return new Dinosaurs(List.of("Triceratop", "Tyranosaur", "Diplodocus"));
    }
}
