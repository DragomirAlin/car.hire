package ro.agilehub.javacourse.car.hire.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@EqualsAndHashCode(of = "_id")
@Document(collection = "user")
public class User {

    @Id
    @Field("_id")
    private ObjectId _id;

    @Indexed(unique=true)
    private String email;

    private String password;

    @Indexed(unique=true)
    private String username;

    private String firstname;
    private String lastname;
    private String title;
    private String country_id;
    private long driverlicensenumber;
    private String status;


}

