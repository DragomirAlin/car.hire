package ro.agilehub.javacourse.car.hire.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {

    @Id
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

