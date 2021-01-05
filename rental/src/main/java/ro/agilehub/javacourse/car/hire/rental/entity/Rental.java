package ro.agilehub.javacourse.car.hire.rental.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@EqualsAndHashCode(of = "_id")
@Document(collection = "rental")
public class Rental {

    @Id
    @Field("_id")
    private ObjectId _id;

    private String user_id;

    private String car_id;

    private String startDate;

    private String endDate;

    private String status;


}


