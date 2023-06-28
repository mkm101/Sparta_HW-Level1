package Controller;

import Entity.Name;
import Repository.Repository;
import Service.MemberService;
import dto.Requestdto;
import dto.Responsedto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    private final MemberService memberService;
    private final Repository repository;

    @Autowired
    public Controller(Repository repository) {
        this.memberService = new MemberService((org.springframework.stereotype.Repository) repository);
        this.repository = repository;
    }
    @PostMapping("/post") //1. 작성API구현
    public Responsedto createMemo(@RequestBody Requestdto requestdto) {
        Name name = new Name(requestdto);
        return memberService.createMemo(requestdto);
    }

    @GetMapping("/posts") // 목록조회, 내림차순
    public List<Name> getNames() {
        return memberService.getNames();
    }

    @GetMapping("/posts/{id}") // 게시글 조회
    public List<Responsedto> getId(@PathVariable Long id, @RequestBody Requestdto requestdto) {
        return memberService.Lookup(id,requestdto);
    }

     //5. 게시글 수정 API
    @PutMapping("/memos/{id}")
    public Responsedto modify(@PathVariable Long id,@RequestBody Requestdto requestdto, String password){
         // 비밀번호 일치
        try {
            // 비밀번호 일치
            if (password.equals(requestdto.getPassword())) {
                return memberService.updateName(id, requestdto); // 수정
            } else {
                throw new IllegalArgumentException("비밀번호를 다시 입력해주세요.");
            }
        } catch (IllegalArgumentException e) {
            return new Responsedto(e.getMessage()); // 예외 메시지를 클라이언트에게 전달
        }
    }
    //6. 게시글삭제
    @PostMapping("post/{id}")
    public ResponseEntity<String> deleteMemo(@PathVariable Long id, @RequestParam String password){
        Name name = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 메모가 존재하지 않습니다."));

        if(!name.getPassword().equals(password))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
        }
        repository.delete(name); // 게시글 삭제
        return ResponseEntity.ok().body("게시글이 성공적으로 삭제되었습니다.");
    }
}
