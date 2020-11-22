package ro.agilehub.javacourse.car.hire.fleet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "car")
public class Car {

    @Id
    private ObjectId _id;
    private String make;
    private String model;
    private int year;
    private int mileage;
    private String fuel;
    private Status status;


}

