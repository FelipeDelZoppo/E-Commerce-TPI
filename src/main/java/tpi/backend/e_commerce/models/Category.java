package tpi.backend.e_commerce.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "No puede estar vacio")
    private String name;

    private boolean deleted; 

    private LocalDateTime creationDatetime;
    private LocalDateTime updateDatetime;
    private LocalDateTime deleteDatetime;

    @PrePersist //Este metodo se ejecutara antes de crear persistir al objeto en la BD
    public void preCreate() {
        creationDatetime = LocalDateTime.now();
    }
    @PreUpdate //Este metodo se ejecutara antes de actualizarse el objeto en la BD
    public void preUpdate() {
        updateDatetime = LocalDateTime.now();
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

}
