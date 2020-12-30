package ro.agilehub.javacourse.car.hire.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "_id")
@Document(collection = "country")
public class Country {

    @Id
    @Field("_id")
    private ObjectId _id;
    @Indexed(unique = true)
    private String name;
    @Indexed(unique = true)
    private String isoCode;
}
