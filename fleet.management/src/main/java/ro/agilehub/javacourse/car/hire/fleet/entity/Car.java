package ro.agilehub.javacourse.car.hire.fleet.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@EqualsAndHashCode(of = "_id")
@Document(collection = "car")
public class Car {

    @Id
    @Field("_id")
    private ObjectId _id;
    private String make_id;
    private String model;
    private int year;
    private int mileage;
    private String fuel;
    private String carClazz;
    private String status;


}

