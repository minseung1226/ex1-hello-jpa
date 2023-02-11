package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member extends BaseEntity{
    @Id@GeneratedValue
    private Long  id;
    @Column(nullable = false)
    private String name;


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member() {
    }
}
