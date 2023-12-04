package git.aditya.eclipselinkparameterbinding.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "JSON_MESSAGE")
public class JSONMessage {
    @Id
    @Column(name = "ID")
    private String id = UUID.randomUUID().toString();
    @Column(name = "MESSAGE")
    @Lob
    private String message;

    public JSONMessage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
