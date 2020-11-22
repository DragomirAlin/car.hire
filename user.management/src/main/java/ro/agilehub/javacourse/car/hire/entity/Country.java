package ro.agilehub.javacourse.car.hire.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "country")
public class Country {

    private ObjectId _id;

    private String name;

    private String isoCode;
}
