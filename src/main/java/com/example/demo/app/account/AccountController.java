//package com.example.demo.app.account;
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
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.demo.app.login.LoginForm;
//import com.example.demo.app.pet.PetForm;
//import com.example.demo.app.record.RecForm;
//import com.example.demo.entity.Account;
//import com.example.demo.service.AccountService;
//
///**
// * ペット成長記録:アカウント
// */
//@Controller
//@RequestMapping("/")
//@SessionAttributes("accountForm")
//public class AccountController {
//
//	private final AccountService accountService;
//
//	public AccountController(AccountService accountService) {
//		this.accountService = accountService;
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
//	/////////////////////
//	// アカウントForm
//	/////////////////////
//
//	@RequestMapping(value="/accountForm", method = RequestMethod.GET)
//	public String goAccountForm(
//			@RequestParam("task") String task,
//			AccountForm accountForm,
//			Model model,
//			SessionStatus sassionStatus
//			) {
//		
//		//アカウント新規登録
//		if(task.equals("register")) {
//			sassionStatus.setComplete();
//			model.addAttribute("title", "アカウント登録");
//			return "accountForm";
//		
//		//アカウント情報編集
//		} else if (task.equals("edit")) {
//			model.addAttribute("title", "アカウント編集");
//			//詰めなおしたaccountFormをセッション(model)にセットする
//			model.addAttribute("accountForm", accountForm);
//			return "accountForm";
//		} else {
//			return "error";
//		}
//		
//	}
//
//	@RequestMapping(value = "/register_confirm", method = RequestMethod.POST)
//	public String confirmRegisterAccount(
//			@Valid @ModelAttribute("accountForm") AccountForm accountForm, 
//			BindingResult resultAccount,
//			Model model
//			) {
//		
//		// エラーがある場合、自画面遷移する
//    	if (resultAccount.hasErrors()) {
//    		return "register";
//    	}		
//    	
//    	//アカウント名重複確認
//		if (accountService.isExist(accountForm.getUserName())) {
//			return "redirect:/register?userName_error";
//		} else {
//		
//			//画像を保存 ファイル名filePath で画像を保存
//			String filePath = saveAndGetFilePath(accountForm.getIcon(), "account");
//			accountForm.setIconPath(filePath);
//			model.addAttribute("accountForm", accountForm);
//			
//			return "/register_confirm";			
//		} 
//
//	}
//
//	@RequestMapping(value = "/register_complete", method = RequestMethod.POST)
//	public String completeRegisterAccount(
//			AccountForm accountForm, 
//			Model model
//			) {
//
//		// 入力されたアカウント情報accountForm を accountに詰めなおす
//		Account account = new Account();
//		account.setUserName(accountForm.getUserName());
//		account.setPass(accountForm.getPass());
//		account.setIcon(accountForm.getIconPath());
//		account.setIntro(accountForm.getIntro());
//
//		// DBにaccountを登録
//		accountService.registerAccount(account);
//
//		return "register_complete";
//	}
//	
////	@RequestMapping(value = "/register", method = RequestMethod.POST)
////	public String modiftyRegisterAccount(
////			AccountForm accountForm, 
////			Model model
////			) {
////
////		return "register";
////	}
//	
//
//	/////////////////////
//	// アカウント情報表示
//	/////////////////////
//
//	@RequestMapping("/account_info/{userId}")
//	public String viewAccountInfo(
//			@PathVariable(name = "userId") int userId, 
//			AccountForm accountForm,
//			Model model
//			) {
//
//		//ユーザーIDからアカウント情報を取得
//		Account account = accountService.findByUserId(userId);
//		
//		//取得したアカウント情報をaccountFormに詰めなおす
//		accountForm.setUserId(userId);
//		accountForm.setUserName(account.getUserName());
//		accountForm.setPass(account.getPass());
//		accountForm.setIconPath(account.getIcon());
//		accountForm.setIntro(account.getIntro());
//
//		//詰めなおしたaccountFormをセッションスコープ(model)にセットする
//		model.addAttribute("accountForm", accountForm);
//
//		return "account_info";
//	}
//
//	/////////////////////
//	// アカウント情報編集
//	/////////////////////
////	@RequestMapping(value = "/accountForm?edit", method = RequestMethod.POST)
////	public String editAccount(
////			AccountForm accountForm, 
////			Model model
////			) {
////		
////		model.addAttribute("title", "アカウント編集");
////		
////		//詰めなおしたaccountFormをセッションスコープ(model)にセットする
////		model.addAttribute("accountForm", accountForm);
////		
////		return "accountForm?edit";
////	}
//	
//    @RequestMapping(value="/account_edit_complete", method = RequestMethod.POST)
//    public String completeEditAccount(
//			@Valid @ModelAttribute("accountForm") AccountForm accountForm, 
//			BindingResult resultAccount,
//			Model model
//    		) {
//    	
//		// エラーがある場合、自画面遷移する
//    	if (resultAccount.hasErrors()) {
//    		return "account_edit";
//    	}
//    	
//		//画像を保存 ファイル名fileName で画像を保存
//		String iconPath = saveAndGetFilePath(accountForm.getIcon(), "account");
//    	
//    	// セッションスコープaccountFormをaccountにつめなおす
//    	Account account = new Account();
//    	account.setUserId(accountForm.getUserId());
//    	account.setUserName(accountForm.getUserName());
//    	account.setPass(accountForm.getPass());
//    	account.setIcon(iconPath);
//    	account.setIntro(accountForm.getIntro());
//
//    	// 変更したaccount情報をDBに上書き実行
//    	accountService.editAccount(account);
//    	
//    	//url用にuserIdを取得
//    	int userId = accountForm.getUserId();
//    	
//    	String url = "redirect:/account_info/" + userId;
//    	
//    	return url;    
//    }
//}
