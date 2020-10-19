package entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
}
