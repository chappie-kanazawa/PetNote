package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity // JPA エンティティとして扱う
@Data // Lombok で getter setter など便利なメソッドを自動生成
@Getter
@Setter
@Table(name = "players")
public class Player {

    @Id // JPA にこの変数をオブジェクトの ID だと認識させる
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="number")
    private Integer number;
    
    @Column(name="position")
    private String position;
    
}