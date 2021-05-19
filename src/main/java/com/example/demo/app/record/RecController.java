//package com.example.demo.app.record;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import javax.validation.Valid;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.demo.app.account.AccountForm;
//import com.example.demo.app.login.LoginForm;
//import com.example.demo.app.pet.PetForm;
//import com.example.demo.entity.Record;
//import com.example.demo.service.RecordService;
//
///**
// * ペット成長記録:ペット情報
// */
//@Controller
//@RequestMapping("/")
//@SessionAttributes("accountForm")
//public class RecController {
//
//	private final RecordService recordService;
//
//	public RecController(
//			RecordService recordService
//			) {
//		this.recordService = recordService;
//	}
//
//    /*
//     * オブジェクトをHTTPセッションに追加する
//     */
//    @ModelAttribute("loginForm")
//    public LoginForm setUpLoginForm() {
//    	LoginForm loginForm = new LoginForm();
//        return loginForm;
//    }
//    
//    @ModelAttribute("accountForm")
//    public AccountForm setUpAccountForm() {
//    	AccountForm accountForm = new AccountForm();
//        return accountForm;
//    }
//    
//    @ModelAttribute("petForm")
//    public PetForm setUpPetForm() {
//    	PetForm petForm = new PetForm();
//        return petForm;
//    }
//    
//    @ModelAttribute("recForm")
//    public RecForm setUpRecForm() {
//    	RecForm recForm = new RecForm();
//        return recForm;
//    }
//
//	/////////////////////
//	// 画像アップロード
//	/////////////////////
//
//	private String getExtension(String filename) {
//		int dot = filename.lastIndexOf(".");
//		if (dot > 0) {
//			return filename.substring(dot).toLowerCase();
//		}
//		return "";
//	}
//
//	private String getUploadFileName(String fileName) {
//
//		return fileName + "_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now())
//				+ getExtension(fileName);
//	}
//
//	// 画像の分類用にcatNameを取得しフォルダ作成、既にフォルダがある場合は何もしない
//	private String createAndReturnPath(String catName) {
//		Path path = Paths.get("src/main/resources/static/images/" + catName + "/");
//		if (!Files.exists(path)) {
//			try {
//				Files.createDirectory(path);
//			} catch (Exception e) {
//				// エラー処理は省略
//			}
//		}
//		return path.toString();
//	}
//	
//	//画像fileをimage/catNameに保存し、DBに保存するための画像のパスfilePathを返す
//	private String saveAndGetFilePath(MultipartFile file, String catName) {
//		String dirPath = createAndReturnPath(catName);
//		
//		String fileName = getUploadFileName(file.getOriginalFilename());
//		Path filePath = Paths.get(dirPath + "/" + fileName);
//
//		try (OutputStream os = Files.newOutputStream(filePath, StandardOpenOption.CREATE)) {
//			byte[] bytes = file.getBytes();
//			os.write(bytes);
//			
//		} catch (IOException e) {
//			// エラー処理は省略
//		}
//		
//		String relPath = "/images/" + catName + "/" + fileName;
//		return relPath;
//	}
//	
//
//
//	/////////////////////
//	// 成長記録投稿
//	/////////////////////
//
////	@RequestMapping(value = "/record_post")
////	public String goRecPost(
////			AccountForm accountForm,
////			Model model
////			) {
////		return "record_post";
////	}
//
//	@RequestMapping(value = "/record_complete", method = RequestMethod.POST)
//	public String postRecord(
//			AccountForm accountForm,
//			@Valid @ModelAttribute("recForm") RecForm recForm, 
//			BindingResult resultRec, 
//			Model model
//			) {
//
//		// エラーがある場合、自画面遷移する
//		if (resultRec.hasErrors()) {
//			return "mypage";
//		}
//
//		//画像を保存 ファイル名fileName で画像を保存
//		String recPicPath = saveAndGetFilePath(recForm.getRecPic(), "rec");
//		recForm.setRecPicPath(recPicPath);
//		model.addAttribute("recForm", recForm);
//		
//        //入力されたRecForm を recordに詰めなおす
//    	Record record = new Record();
//    	record.setComment(recForm.getComment());
//    	record.setRecPic(recPicPath);
//    	record.setRecDate(recForm.getRecDate());
//    	record.setPetId(recForm.getPetId());
//    	
//    	//DBにrecordを登録
//		recordService.postRecord(record);
//
//		return "record_complete";
//
//	}
//	
//
//	
//    /////////////////////
//	//　成長記録編集
//	/////////////////////
//    
//    @RequestMapping(value = "/record_edit", method = RequestMethod.POST)
//    public String editRecordForm(
//    		AccountForm accountForm,
//    		RecForm recForm,
//    		Model model
//    		) {
//    	return "record_edit";
//    }
//    
//    @RequestMapping(value = "/record_edit_complete", method = RequestMethod.POST)
//    public String completeEditPetForm(
//    		AccountForm accountForm,
//    		@ModelAttribute("recForm") @Validated RecForm recForm, 
//    		BindingResult result, 
//    		Model model
//    		) {
//    	
//		// エラーがある場合、自画面遷移する
//		if (result.hasErrors()) {
//			return "record_edit";
//		}
//    	
//		//filePathに既存の画像のパスを代入
//		String filePath =  recForm.getRecPicPath();
//    	
//		//画像を保存 ファイル名filePath で画像を保存
//		if (recForm.getRecPic() != null) {
//			filePath = saveAndGetFilePath(recForm.getRecPic(), "rec");			
//		}
//        
//        //入力されたRecForm を recordに詰めなおす
//    	Record record = new Record();
//    	
//    	record.setComment(recForm.getComment());
//    	record.setRecPic(filePath);
//    	record.setRecDate(recForm.getRecDate());
//    	record.setPetId(recForm.getPetId());
//    	record.setRecId(recForm.getRecId());
//
//    	//DBにpetを登録
//    	recordService.editRecord(record);
//
//		return "record_complete";
//    }
//
//
//	/////////////////////
//	// 成長記録削除
//	/////////////////////
//	@RequestMapping(value = "/record_delete/{petId}/{petName}/{recId}")
//	public String deleteRecord(
//			AccountForm accountForm,
//			@PathVariable("petId") int petId, 
//			@PathVariable("petName") String petName,
//			@PathVariable("recId") int recId,
//			Model model
//			) {
//
//		recordService.deleteByRecId(recId);
//
//		return "complete";
//	}
//
//}
