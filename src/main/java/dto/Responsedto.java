package dto;

public class Responsedto {
    private String name; // 제목
    private String Writer;// 작성자명
    private String password; // 패스워드


    public Responsedto(String name) {
        this.name = name.getName();
        this.password =  name.getPassword();
        this.Writer = name.getWriter();
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
