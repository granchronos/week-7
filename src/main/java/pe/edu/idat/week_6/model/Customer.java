package pe.edu.idat.week_6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, message = "Name must be large")
    @NotEmpty(message = "Name is required")
    private String name;

    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 4, message = "Password must be at least 4 characters")
    private String password;

    private LocalDate birthday;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

}
