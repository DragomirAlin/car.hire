package ro.agilehub.javacourse.car.hire.fleet.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@EqualsAndHashCode(of = "_id")
@Document(collection = "make")
public class Make {

    @Id
    @Field("_id")
    private ObjectId _id;
    private String makeName;
}
