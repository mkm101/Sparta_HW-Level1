package Service;

import dto.Requestdto;
import dto.Responsedto;
import Entity.Name;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Component
public class MemberService {
    private final Repository repository;

    public MemberService(Repository repository) {
        this.repository = repository;
    }
    public Responsedto createMemo(Requestdto requestdto) {
        Name name = new Name(requestdto);
        Name saveName = repository.save(name); // db 연동해서 저장.
        return new Responsedto(saveName);
    }

    public List<Name> getNames() {
        return repository.findAllByOrderByModifiedAtDesc().stream().map(Name::new).toList();
    }

    public List<Responsedto> Lookup(Long id, @RequestBody Requestdto requestdto){
        return repository.findAll().stream().map(Requestdto::new).toList();
    }

    @Transactional
    public Responsedto updateName(Long id,Requestdto requestdto) {
        // 해당 메모가 DB에 존재하는지 확인
        Name name = findName(id);
        // memo 내용 수정
        name.update(requestdto);
        return new Responsedto(name);
    }

    private Name findName(Long id) {
    }
}
