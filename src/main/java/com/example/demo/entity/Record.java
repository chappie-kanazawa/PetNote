package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity // JPA エンティティとして扱う
@Table(name = "players")
@Data
public class Record implements Comparable<Record>{
	
    @Id // JPA にこの変数をオブジェクトの ID だと認識させる
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rec_id")
	private int recId;
    
    @Column(name="comment")
	private String comment;
    
    @Column(name="rec_pic")
	private String recPic;
    
    @Column(name="rec_date")
    private LocalDateTime recDate;
    
    @Column(name="pet_id")
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