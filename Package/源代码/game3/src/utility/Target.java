package utility;

import java.io.Serializable;

/**
 * 
 * @author ɽ����ѧandroidʵ�������ѳ�
 *
 */
public class Target implements Serializable {
 //ָ�����:0��ʾЭ��ָ��:1��ʾ����ָ��:2��ʾ����
 private int category;
 //Э��ָ��ʱ��valueΪ1��ʾ׼������������ָ��ʱ��valueΪ0
 private String value;
 //Э��ָ��ʱ��Ϊ0.����ָ��ʱ��
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
