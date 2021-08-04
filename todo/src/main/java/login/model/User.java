package login.model;

public class User {
	private String id;
	/** パスワード 暗号化して使用する */
	private byte[] pass;
	private String mail;
	
	public void setId(String id) {this.id = id; }
	public void setPass(byte[] pass) {this.pass = pass; }
	public void setMail(String mail) {this.mail = mail;}
	
	public String getId() {return id; }
	public byte[] getPass() {return pass; }
	public String getMail() {return mail; }

}
