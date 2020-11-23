package ro.agilehub.javacourse.car.hire.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@EqualsAndHashCode(of = "_id")
@Document(collection = "user")
public class User {

    @Id
    @Field("_id")
    private ObjectId _id;
    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String countryOfResidence;
    private long driverLicenseNumber;
    private Status status;


}

