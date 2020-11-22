package ro.agilehub.javacourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "rental")
public class Rental {

    @Id
    private ObjectId _id;

    private String user_id;

    private String car_id;

    private String startDate;

    private String endDate;

    private Status status;


}


