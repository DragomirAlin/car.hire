package ro.agilehub.javacourse.car.hire.user.entity;

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
@Document(collection = User.COLLECTION_NAME)
public class User {
    public final static String COLLECTION_NAME = "user";

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
    private String countryId;
    private long driverLicense;
    private Status status;


}

