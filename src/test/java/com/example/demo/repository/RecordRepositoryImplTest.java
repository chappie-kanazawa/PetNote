//package com.example.demo.repository;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.data.domain.Page;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//
//import com.example.demo.entity.Record;
//
////import lombok.var;
//
//@SpringJUnitConfig
//@SpringBootTest
//@ActiveProfiles("unit")
//@Sql
//class RecordRepositoryImplTest {
//
//    @Autowired
//    private RecordRepositoryImpl recordRepository;
//    
//    @Test
//    @DisplayName("findAllのテスト")
//    void findAll() {
//    	List<Record> list = recordRepository.findAll();
//
//        // 件数のチェック
//        assertEquals(6, list.size());
//
//        // 2件目のレコードの取得(ORDER BYが正しく反映されているか)
//        Record record2 = list.get(2);
//        assertNotNull(record2);
//        
//
//        // 各カラムの値が正しくセットされているか
//        assertEquals("よく食べたてすと", record2.getComment());
//        assertEquals("ddddtest", record2.getRecPic());
//        assertEquals(LocalDateTime.of(2021, 03, 13, 15, 00, 00), record2.getRecDate());
//        assertEquals(3, record2.getPetId());
//        
//        Record record3 = list.get(3);
//        assertNotNull(record3);
//
//        assertEquals("歌っているてすと", record3.getComment());
//
//    }
//    
//    @Test
//    @DisplayName("findByRecIdのテスト(正常系)")
//    void findByRecId1() {
//        Record rec1 = recordRepository.findByRecId(1);
//
//        // レコードの存在チェック
//        assertNotNull(rec1);
//
//        // 各カラムの値が正しくセットされているか
//        assertEquals("今日も元気てすと", rec1.getComment());
//        assertEquals("cccctest", rec1.getRecPic());
//        assertEquals(LocalDateTime.of(2021, 03, 12, 15, 00, 00), rec1.getRecDate());
//        assertEquals(1, rec1.getPetId());
//    }
//
//    @Test
//    @DisplayName("findByRecIdのテスト(レコードが取得できない場合)")
//    void findByRecId2() {
//        // レコードが取得できず例外がスローされるか
//        assertThrows(EmptyResultDataAccessException.class, () -> recordRepository.findByRecId(10));
//    }
//    
//    @Test
//    @DisplayName("findByPetId(正常系)のテスト")
//    void findByPetId1() {
//    	List<Record> list = recordRepository.findByPetId(2);
//        // 件数のチェック
//        assertEquals(2, list.size());
//
//        // 1件目のレコードの取得(ORDER BYが正しく反映されているか)
//        Record rec2 = list.get(0);
//        assertNotNull(rec2);
//
//        // 各カラムの値が正しくセットされているか
//        assertEquals(3, rec2.getRecId());
//        assertEquals("歌っているてすと", rec2.getComment());
//        assertEquals("eeeetest", rec2.getRecPic());
//        assertEquals(LocalDateTime.of(2021, 03, 14, 15, 00, 00), rec2.getRecDate());
//        assertEquals(2, rec2.getPetId());
//
//    }
//    
//    @Test
//    @DisplayName("findByUserId正常系)のテスト")
//    void findByUserId1() {
//    	List<Record> list = recordRepository.findByUserId(1);
//        // 件数のチェック
//        assertEquals(2, list.size());
//
//        // 1件目のレコードの取得(ORDER BYが正しく反映されているか)
//        Record rec2 = list.get(0);
//        assertNotNull(rec2);
//
//        // 各カラムの値が正しくセットされているか
//        assertEquals(1, rec2.getRecId());
//        assertEquals("今日も元気てすと", rec2.getComment());
//        assertEquals("cccctest", rec2.getRecPic());
//        assertEquals(LocalDateTime.of(2021, 03, 12, 15, 00, 00), rec2.getRecDate());
//        assertEquals(1, rec2.getPetId());
//
//    }
//    
//    
//    
//    @Test
//    @DisplayName("insertのテスト(正常系)")
//    void insert() {    	
//        Record rec = new Record();
//        //rec.setRecId(6);					//IDは自動割り振り
//        rec.setComment("一緒に散歩したてすと4");
//        rec.setRecPic("ggggtest4");
//        rec.setRecDate(LocalDateTime.of(2007, 12, 03, 10, 15, 30));
//        rec.setPetId(1);
//
//        recordRepository.insert(rec);
//
//        List<Record> list = recordRepository.findAll();
//
//        // 件数のチェック
//        assertEquals(7, list.size());
//
//        // 登録されたレコードの取得
//        Record recx = list.get(6);
//
//        // 各カラムの値が正しくセットされているか
//        assertEquals(rec.getRecId(), recx.getRecId());
//        assertEquals(rec.getComment(), recx.getComment());
//        assertEquals(rec.getRecPic(), recx.getRecPic());
//        assertEquals(rec.getRecDate(), recx.getRecDate());
//        assertEquals(rec.getPetId(), recx.getPetId());
//        
//    }
//    
//
//    @Test
//    @DisplayName("updateのテスト(正常系)")
//    void update1() {
//    	
//        Record rec1 = recordRepository.findByRecId(5);
//        assertEquals("一緒に散歩したてすと", rec1.getComment());
//    	
//        Record rec = new Record();
//        rec.setRecId(5);
//        rec.setComment("一緒に散歩したてすとxxx");
//        rec.setRecPic("ggggtestzzzz");
//        rec.setRecDate(LocalDateTime.of(2007, 12, 03, 10, 15, 30));
//        rec.setPetId(2);
//
//        int updateCount = recordRepository.update(rec);
//
//        assertEquals(1, updateCount);
//
//        Record rec2 = recordRepository.findByRecId(5);
//
//        // レコードの存在チェック
//        assertNotNull(rec2);
//
//        // 各カラムの値が正しくセットされているか
//        assertEquals(5, rec2.getRecId());
//        assertEquals("一緒に散歩したてすとxxx", rec2.getComment());
//        assertEquals("ggggtestzzzz", rec2.getRecPic());
//        assertEquals(LocalDateTime.of(2007, 12, 03, 10, 15, 30), rec2.getRecDate());
//        assertEquals(2, rec2.getPetId());
//    }
//
//    @Test
//    @DisplayName("updateのテスト(更新対象がない場合)")
//    void update2() {
//        Record rec = new Record();
//        rec.setRecId(10);
//        int updateCount = recordRepository.update(rec);
//        assertEquals(0, updateCount);
//    }
//
//    @Test
//    @DisplayName("deleteByRecIdのテスト(正常系)")
//    void deleteByRecId1() {
//    	recordRepository.deleteByRecId(1);
//
//    	List<Record> list = recordRepository.findAll();
//
//        // 件数のチェック(対象外のレコードまで消えていないかチェック)
//        assertEquals(5, list.size());
//
//        // レコードが取得できないことを確認
//        assertThrows(EmptyResultDataAccessException.class, () -> recordRepository.findByRecId(1));
//    }
//
//    @Test
//    @DisplayName("deleteByRecIdのテスト(更新対象がない場合)")
//    void deleteByRecId2() {
//        int deleteCount = recordRepository.deleteByRecId(10);
//        assertEquals(0, deleteCount);
//
//        List<Record> list = recordRepository.findAll();
//
//        // 件数のチェック(全てのレコードが消えていない事を確認)
//        assertEquals(6, list.size());
//
//    }
//
//    @Test
//    @DisplayName("deleteByPetIdのテスト(正常系)")
//    void deleteByPetId1() {
//    	recordRepository.deleteByPetId(2);
//
//    	List<Record> list = recordRepository.findAll();
//
//        // 件数のチェック(対象外のレコードまで消えていないかチェック)
//        assertEquals(4, list.size());
//
//        // レコードが取得できないことを確認
//        assertThrows(EmptyResultDataAccessException.class, () -> recordRepository.findByRecId(3));
//    }
//
//    @Test
//    @DisplayName("deleteByPetIdのテスト(更新対象がない場合)")
//    void deleteByPetId2() {
//        int deleteCount = recordRepository.deleteByPetId(10);
//        assertEquals(0, deleteCount);
//
//        List<Record> list = recordRepository.findAll();
//
//        // 件数のチェック(全てのレコードが消えていない事を確認)
//        assertEquals(6, list.size());
//
//    }
//    
//    @Test
//    @DisplayName("deleteByUserIdのテスト(正常系)")
//    void deleteByUserId1() {
//    	recordRepository.deleteByUserId(0);
//
//    	List<Record> list = recordRepository.findAll();
//
//        // 件数のチェック(対象外のレコードまで消えていないかチェック)
//        assertEquals(2, list.size());
//
//        // レコードが取得できないことを確認
//        assertThrows(EmptyResultDataAccessException.class, () -> recordRepository.findByRecId(4));
//    }
//
//    @Test
//    @DisplayName("deleteByUserIdのテスト(更新対象がない場合)")
//    void deleteByUserId2() {
//        int deleteCount = recordRepository.deleteByUserId(10);
//        assertEquals(0, deleteCount);
//
//        List<Record> list = recordRepository.findAll();
//
//        // 件数のチェック(全てのレコードが消えていない事を確認)
//        assertEquals(6, list.size());
//
//    }
//    
////    @Test
////    @DisplayName("findByPetId(正常系)のテスト")
////    void findByPetId2() {
////        Page<Record> list = recordRepository.findByPetIdByOrderByRecDate(2, );
////        // 件数のチェック
////        assertEquals(2, list.getSize());
////        // 1件目のレコードの取得(ORDER BYが正しく反映されているか)
////        var rec2 = list.get(1);
////        assertNotNull(rec2);
////
////        // 各カラムの値が正しくセットされているか
////        assertEquals(3, rec2.getRecId());
////        assertEquals("歌っているてすと", rec2.getComment());
////        assertEquals("eeeetest", rec2.getRecPic());
////        assertEquals(LocalDateTime.of(2021, 03, 14, 15, 00, 00), rec2.getRecDate());
////        assertEquals(2, rec2.getPetId());
////    }
//    
//}
