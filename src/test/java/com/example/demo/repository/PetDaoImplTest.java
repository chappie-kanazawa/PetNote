package com.example.demo.repository;

import com.example.demo.entity.Pet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest
@ActiveProfiles("unit")
@Sql
class PetDaoImplTest {

    @Autowired
    private PetDaoImpl petDao;
    
    @Test
    @DisplayName("findAllのテスト")
    void findAll() {
        var list = petDao.findAll();

        // 件数のチェック
        assertEquals(4, list.size());

        // 2件目のレコードの取得(ORDER BYが正しく反映されているか)
        var pet2 = list.get(1);
        assertNotNull(pet2);

        // 各カラムの値が正しくセットされているか
        assertEquals("チチtest", pet2.getPetName());
        assertEquals("インコtest", pet2.getKind());
        assertEquals(2, pet2.getGender());
        assertEquals("aaaatest", pet2.getPetIcon());
        assertEquals(2, pet2.getUserId());

        var account3 = list.get(2);
        assertNotNull(account3);

        assertEquals("ミーtest", account3.getPetName());

    }
    
    @Test
    @DisplayName("findByPetIdのテスト(正常系)")
    void findByPetId1() {
        var pet1 = petDao.findByPetId(1);

        // レコードの存在チェック
        assertNotNull(pet1);

        // 各カラムの値が正しくセットされているか
        assertEquals("ペロtest", pet1.getPetName());
        assertEquals("犬test", pet1.getKind());
        assertEquals(1, pet1.getGender());
        assertEquals("zzzztest", pet1.getPetIcon());
        assertEquals(1, pet1.getUserId());
    }

    @Test
    @DisplayName("findByPetIdのテスト(レコードが取得できない場合)")
    void findByUserId2() {
        // レコードが取得できず例外がスローされるか
        assertThrows(EmptyResultDataAccessException.class, () -> petDao.findByPetId(10));
    }
    
    @Test
    @DisplayName("findByUserId(正常系)のテスト")
    void findByUserId1() {
        var list = petDao.findByUserId(2);
        // 件数のチェック
        assertEquals(1, list.size());

        // 1件目のレコードの取得(ORDER BYが正しく反映されているか)
        var pet2 = list.get(0);
        assertNotNull(pet2);

        // 各カラムの値が正しくセットされているか
        assertEquals("チチtest", pet2.getPetName());
        assertEquals("インコtest", pet2.getKind());
        assertEquals(2, pet2.getGender());
        assertEquals("aaaatest", pet2.getPetIcon());
        assertEquals(2, pet2.getUserId());

    }
    
    @Test
    @DisplayName("findByUserIdAndKind(正常系)のテスト")
    void findByUserIdAndKind1() {
        var list = petDao.findByUserIdAndKind(1, "犬test");
        // 件数のチェック
        assertEquals(2, list.size());

        // 1件目のレコードの取得(ORDER BYが正しく反映されているか)
        var pet3 = list.get(0);
        assertNotNull(pet3);

        // 各カラムの値が正しくセットされているか
        assertEquals("ペロtest", pet3.getPetName());
        assertEquals("犬test", pet3.getKind());
        assertEquals(1, pet3.getGender());
        assertEquals("zzzztest", pet3.getPetIcon());
        assertEquals(1, pet3.getUserId());

    }
    
    @Test
    @DisplayName("insertのテスト(正常系)")
    void insert() {
        var pet = new Pet();
        pet.setPetId(5);
        pet.setPetName("ペロtest4");
        pet.setKind("犬test4");
        pet.setGender(1);
        pet.setPetIcon("zzzztest4");
        pet.setUserId(1);

        petDao.insert(pet);

        //assertEquals(1, insertCount);

        var list = petDao.findAll();

        // 件数のチェック
        assertEquals(5, list.size());

        // 登録されたレコードの取得
        var petx = list.get(4);

        // 各カラムの値が正しくセットされているか
        assertEquals(pet.getPetId(), petx.getPetId());
        assertEquals(pet.getPetName(), petx.getPetName());
        assertEquals(pet.getKind(), petx.getKind());
        assertEquals(pet.getGender(), petx.getGender());
        assertEquals(pet.getPetIcon(), petx.getPetIcon());
        assertEquals(pet.getUserId(), petx.getUserId());
        
    }

    @Test
    @DisplayName("updateのテスト(正常系)")
    void update1() {
        var pet = new Pet();
        pet.setPetId(2);
        pet.setPetName("チチtestx");
        pet.setKind("インコtestx");
        pet.setGender(2);
        pet.setPetIcon("aaaatestx");
        pet.setUserId(1);				//本来であればユーザーID（飼い主）は変わらない
        

        var updateCount = petDao.update(pet);

        assertEquals(1, updateCount);

        var pet2 = petDao.findByPetId(2);

        // レコードの存在チェック
        assertNotNull(pet2);

        // 各カラムの値が正しくセットされているか
        assertEquals(pet.getPetId(), pet2.getPetId());
        assertEquals(pet.getPetName(), pet2.getPetName());
        assertEquals(pet.getKind(), pet2.getKind());
        assertEquals(pet.getGender(), pet2.getGender());
        assertEquals(pet.getPetIcon(), pet2.getPetIcon());
        assertEquals(pet.getUserId(), pet2.getUserId());
    }

    @Test
    @DisplayName("updateのテスト(更新対象がない場合)")
    void update2() {
        var pet = new Pet();
        pet.setPetId(10);
        var updateCount = petDao.update(pet);
        assertEquals(0, updateCount);
    }

    @Test
    @DisplayName("deleteByPetIdのテスト(正常系)")
    void deleteByPetId1() {
        petDao.deleteByPetId(1);

        var list = petDao.findAll();

        // 件数のチェック(対象外のレコードまで消えていないかチェック)
        assertEquals(3, list.size());

        // レコードが取得できないことを確認
        assertThrows(EmptyResultDataAccessException.class, () -> petDao.findByPetId(1));
    }

    @Test
    @DisplayName("deleteByPetIdのテスト(更新対象がない場合)")
    void deleteByPetId2() {
        var deleteCount = petDao.deleteByPetId(10);
        assertEquals(0, deleteCount);

        var list = petDao.findAll();

        // 件数のチェック(全てのレコードが消えていない事を確認)
        assertEquals(4, list.size());

    }

    @Test
    @DisplayName("deleteByUserIdのテスト(正常系)")
    void deleteByUserId1() {
        petDao.deleteByUserId(2);

        var list = petDao.findAll();

        // 件数のチェック(対象外のレコードまで消えていないかチェック)
        assertEquals(3, list.size());

        // レコードが取得できないことを確認
        assertThrows(EmptyResultDataAccessException.class, () -> petDao.findByPetId(2));
    }

    @Test
    @DisplayName("deleteByUserIdのテスト(更新対象がない場合)")
    void deleteByUserId2() {
        var deleteCount = petDao.deleteByUserId(10);
        assertEquals(0, deleteCount);

        var list = petDao.findAll();

        // 件数のチェック(全てのレコードが消えていない事を確認)
        assertEquals(4, list.size());

    }
    
}
