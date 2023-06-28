package Entity;

import dto.Requestdto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Name extends com.sparta.memo.entity.Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name ="contents", nullable = false,length = 500)
    private String contents;

    @Column(name ="name", nullable = false,length = 500)
    private String name;

    @Column(name ="password", nullable = false,length = 500)
    private String password;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    public void setModifiedAt(LocalDateTime modifiedAt){
        this.modifiedAt = modifiedAt;
    }
    public Name(Requestdto requestDto){
        this.username = requestDto.getName();
        this.contents = requestDto.getContents();
        this.id = requestDto.getid();
    }

    public void update(Requestdto requestdto) {
        this.username = requestdto.getName();
        this.contents = requestdto.getContents();
        this.name = requestdto.getName();   // 제목
    }

    public String getPassword() {
        return this.password;
    }



    public String getName() {
        return this.getName();
    }

    public String getWriter() {
        return this.getWriter();
    }

    public Name save(Name name) {
        this.name = name.name;
        this.contents = name.contents;
    }
}
