//package com.example.demo.app.pet;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
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
//import com.example.demo.app.record.RecForm;
//import com.example.demo.entity.Pet;
//import com.example.demo.entity.Record;
//import com.example.demo.service.PetService;
//import com.example.demo.service.RecordService;
//
///**
// * ペット成長記録:ペット情報
// */
//@Controller
//@RequestMapping("/")
//@SessionAttributes("accountForm")
//public class PetController {
//	
//    private final PetService petService;
//    private final RecordService recordService;
//
//    public PetController(PetService petService, RecordService recordService) {
//        this.petService = petService;
//        this.recordService = recordService;
//    }
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
////    /////////////////////
////	//　ペット追加登録
////	/////////////////////
////    
////	@RequestMapping(value = "/pet_add")
////	public String goAddPet(
////			AccountForm accountForm,
////			PetForm petForm, 
////			Model model
////			)  {        
////		return "pet_add";
////	}
//// 
////    @RequestMapping(value = "/pet_confirm", method = RequestMethod.POST)
////    public String confirmAddPet(
////    		AccountForm accountForm,
////    		@Valid @ModelAttribute("petForm") PetForm petForm, 
////    		BindingResult resultPet,
////    		Model model
////    		) {
////    	
////    	// エラーがある場合、自画面遷移する
////    	if (resultPet.hasErrors()) {
////    		return "pet_add";
////    	}
////    	
////		//画像を保存 ファイル名fileName で画像を保存
////		String petIconPath = saveAndGetFilePath(petForm.getPetIcon(), "pet");
////		petForm.setPetIconPath(petIconPath);
////		model.addAttribute("petForm", petForm);
////
////    	return "pet_confirm";
////
////    }
////	
////    @RequestMapping(value = "/pet_complete", method = RequestMethod.POST)
////    public String completeAddPet(
////    		PetForm petForm, 
////    		AccountForm accountForm,
////    		Model model
////    		) {
////        
////        //入力されたPetForm を petに詰めなおす
////    	Pet pet = new Pet();
////    	pet.setPetName(petForm.getPetName());
////    	pet.setKind(petForm.getKind());
////    	pet.setGender(petForm.getGender());
////    	pet.setPetIcon(petForm.getPetIconPath());
////    	pet.setUserId(accountForm.getUserId());
////
////    	//DBにpetを登録
////		petService.addPet(pet);
////
////		return "pet_complete";
////    }
////    
////    @RequestMapping(value = "/pet_add", method = RequestMethod.POST)
////    public String completeAddPet(
////    		AccountForm accountForm,
////    		PetForm petForm, 
////    		Model model
////    		) {
////		return "pet_add";
////    }
////    
////    /////////////////////
////	//　ペット編集
////	/////////////////////
////    
////    @RequestMapping(value = "/pet_edit", method = RequestMethod.POST)
////    public String editPetForm(
////			AccountForm accountForm,
////			PetForm petForm, 
////			Model model
////    		) {
////    	return "pet_edit";
////    }
////    
////    @RequestMapping(value = "/pet_edit_complete", method = RequestMethod.POST)
////    public String completeEditPetForm(
////    		AccountForm accountForm,
////    		@ModelAttribute("petForm") @Validated PetForm petForm, 
////    		BindingResult result, 
////    		Model model
////    		) {
////    	
////		// エラーがある場合、自画面遷移する
////		if (result.hasErrors()) {
////			return "pet_edit";
////		}
////    	
////		//filePathに既存の画像のパスを代入
////		String filePath =  petForm.getPetIconPath();
////    	
////		//画像を保存 ファイル名filePath で画像を保存
////		if (petForm.getPetIcon() != null) {
////			filePath = saveAndGetFilePath(petForm.getPetIcon(), "pet");			
////		}
////        
////        //入力されたPetForm を petに詰めなおす
////    	Pet pet = new Pet();
////    	pet.setPetId(petForm.getPetId());
////    	pet.setPetName(petForm.getPetName());
////    	pet.setKind(petForm.getKind());
////    	pet.setGender(petForm.getGender());
////    	pet.setPetIcon(filePath);
////    	pet.setUserId(petForm.getUserId());
////
////    	//DBにpetを登録
////		petService.editPet(pet);
////
////		return "pet_complete";
////    }
////    
////    /////////////////////
////	//　ペット削除
////	/////////////////////
////    
////    @RequestMapping(value = "/pet_delete_confirm", method = RequestMethod.POST)
////    public String deletePetConfirm(
////    		AccountForm accountForm,
////    		@Validated @ModelAttribute PetForm petForm, 
////    		Model model,
////    		BindingResult result
////    		) {
////    	
////        model.addAttribute("petForm", petForm);
////    	
////    	return "pet_delete_confirm";
////    }
////    
////    @RequestMapping(value = "/pet_delete_complete", method = RequestMethod.POST)
////    public String deletePetComplete(
////    		AccountForm accountForm,
////    		@Validated @ModelAttribute PetForm petForm, 
////    		BindingResult result, 
////    		Model model
////    		) {
////        
////        //入力されたPetFormから削除するpetIdを取り出す
////    	int petId = petForm.getPetId();
////
////    	//petIdのペットを削除
////		petService.deleteByPetId(petId);
////
////		return "pet_complete";
////    }
////    
////	/////////////////////
////	//　成長記録表示
////	/////////////////////
////    @RequestMapping(value="/record_list/{petId}/{petName}")
////    public String viewRecordList(
////    		AccountForm accountForm,
////    		@PathVariable("petId") int petId,
////    		@PathVariable("petName") String petName,
////    		@Validated @ModelAttribute PetForm petForm, 
////    		Model model
////    		) {
////
////    	List<Record> recList = recordService.findByPetId(petId);
////    	
////    	petForm.setRecList(recList);
////    	petForm.setPetId(petId);
////    	petForm.setPetName(petName);
////    	model.addAttribute("petForm", petForm);
////    	
////      return "record_list";
////    }
//    
//}
