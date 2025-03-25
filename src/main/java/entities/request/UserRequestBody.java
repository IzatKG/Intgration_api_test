package entities.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestBody {
    private String name;
    private String job;
}
