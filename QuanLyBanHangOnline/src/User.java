public class User {
    private String maUser;
    private String tenUser;     
    private String sdt;            
    
    public User(String maUser, String tenUser, String sdt) {
        this.maUser = maUser;
        this.tenUser = tenUser;
        this.sdt = sdt;
    }

    public String getMaUser() {
        return maUser;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser;
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        this.tenUser = tenUser;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

}