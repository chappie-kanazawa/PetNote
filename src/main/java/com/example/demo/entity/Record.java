package com.example.demo.entity;

import java.time.LocalDateTime;
import java.lang.Comparable;

public class Record implements Comparable<Record>{
	private int recId;
	private String comment;
	private String recPic;
    private LocalDateTime recDate;
    private int petId;
    private String petName;
    
    //Listにする際の自然順序付けを日付降順に設定
    public int compareTo(Record record) {
    	if( this.recDate.isAfter(record.recDate) ) {
    		return -1;
    	}
    	if( this.recDate.isBefore(record.recDate) ) {
    		return 1;
    	} 
    	return 0;
    }
    
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRecPic() {
		return recPic;
	}
	public void setRecPic(String recPic) {
		this.recPic = recPic;
	}
	public LocalDateTime getRecDate() {
		return recDate;
	}
	public void setRecDate(LocalDateTime recDate) {
		this.recDate = recDate;
	}
	public int getPetId() {
		return petId;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}

    
	
}