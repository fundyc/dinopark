package com.dinopark.model.dinosaurs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Dinosaurs {
    List<String> dinosaurs;
}
