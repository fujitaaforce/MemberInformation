package jp.co.aforce.beans;

import java.io.Serializable;

//JavaBeans の雛形
@SuppressWarnings("serial")
public class MemberNoBean implements Serializable {

private String memberno;
private String name;
private int age;
private int birthyear;
private int birthmonth;
private int birthday;
private String emsg;

 // セッター
 public String getMemberno() {
     return memberno;
 }
 public String getName() {
     return name;
 }
 public int getAge() {
     return age;
 }
 public int getBirthyear() {
     return birthyear;
 }
 public int getBirthmonth() {
     return birthmonth;
 }
 public int getBirthday() {
     return birthday;
 }
 public String getEmsg() {
	 return emsg;
 }


 // ゲッター
 public void setMemberno(String memberno) {
     this.memberno = memberno;
 }
 public void setName(String name) {
     this.name = name;
 }
 public void setAge(int age) {
     this.age = age;
 }
 public void setBirthyear(int birthyear) {
     this.birthyear = birthyear;
 }
 public void setBirthmonth(int birthmonth) {
     this.birthmonth = birthmonth;
 }
 public void setBirthday(int birthday) {
     this.birthday = birthday;
 }
 public void setEmsg(String emsg) {
	 this.emsg = emsg;
 }
}