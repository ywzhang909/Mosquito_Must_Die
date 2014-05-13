package utility;

import java.io.Serializable;

/**
 * 
 * @author 山东大学android实验室刘昭呈
 *
 */
public class Target implements Serializable {
 //指令类别:0表示协调指令:1表示动作指令:2表示升级
 private int category;
 //协调指令时，value为1表示准备就绪，动作指令时，value为0
 private String value;
 //协调指令时，为0.动作指令时，
 private int Locx;
 private int Locy;
 public Target(int category,String value,int Locx,int Locy){
	 this.category=category;
	 this.value=value;
	 this.Locx=Locx;
	 this.Locy=Locy;
 }
public int getCategory() {
	return category;
}
public void setCategory(int category) {
	this.category = category;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public int getLocx() {
	return Locx;
}
public void setLocx(int locx) {
	Locx = locx;
}
public int getLocy() {
	return Locy;
}
public void setLocy(int locy) {
	Locy = locy;
}
 
}
