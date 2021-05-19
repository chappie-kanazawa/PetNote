//package com.example.demo.app.login;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.support.SessionStatus;
//
//import com.example.demo.app.account.AccountForm;
//import com.example.demo.app.pet.PetForm;
//import com.example.demo.app.record.RecForm;
//import com.example.demo.entity.Account;
//import com.example.demo.entity.Login;
//import com.example.demo.entity.Pet;
//import com.example.demo.entity.Record;
//import com.example.demo.service.LoginService;
//import com.example.demo.service.PetService;
//import com.example.demo.service.RecordService;
//
//@Controller
//@RequestMapping("/")
////@SessionAttributesは1つのController内で扱う複数のリクエスト間で
////データを共有する場合に有効。
////types属性にHTTPセッションに格納するオブジェクトクラスを指定する。
//@SessionAttributes("accountForm")
//public class LoginController {
//	
//    private final LoginService loginService;
//    private final PetService petService;
//    private final RecordService recordService;
//
//    public LoginController(
//    		LoginService loginService, 
//    		PetService petService,
//    		RecordService recordService
//    		) {
//        this.loginService = loginService;
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
//	//ログイン
//	/////////////////////
//	@RequestMapping("/login")
//	public String goLogin() {
//		return "login";
//	}
//	
//	/////////////////////
//	//TOPへ戻る
//	/////////////////////
//	@RequestMapping("/index")
//	public String goIndex() {
//		return "index";
//	}
//    
//    //Modelから取得するオブジェクトの属性名は、@ModelAttributeのvalue属性に指定する。
//    @RequestMapping(value = "/login_ok", method = RequestMethod.POST)
//    public String loginCheck(
//    		@ModelAttribute("loginForm") @Validated LoginForm loginForm, 
//    		BindingResult resultLogin,
//    		AccountForm accountForm,
//    		Model model) {
//    	
//    	// 入力値にエラーがある場合、自画面遷移する
//		if (resultLogin.hasErrors()) {
//			return "login";
//		}
//        
//    	//ログイン認証結果をloginResultに代入
//    	String userName = loginForm.getUserName();
//    	String pass = loginForm.getPass();
//    	Login login = new Login(userName, pass); 	
//        boolean loginResult = loginService.execute(login);
//            
//        //ログイン認証OKの場合
//        if(loginResult) {
//        	
//        	//ログインしたユーザーの、アカウント情報を取得
//        	Account account = new Account();
//        	account = loginService.findByUserName(userName);
//        	
//        	//取得したaccount情報をaccountFormにコピー
//        	BeanUtils.copyProperties(account, accountForm);
//        	
//            //petのリストを取得する
//            List<Pet> petList = petService.findByUserId(account.getUserId());
//            
//            //成長記録を各petのrecListに代入
//            List<Record> recList = new ArrayList<Record>();
//            for ( Pet pet: petList ) {
//            	int petId = pet.getPetId();
//            	recList = recordService.findByPetId(petId);
//            	pet.setRecList(recList);
//            }
//            
//            //取得したpetListをaccountFormにセット
//            accountForm.setPetList(petList);
//        	
//            //accountFormをセッションにセット
//        	model.addAttribute("accountForm", accountForm);
//        	
//            return "redirect:/mypage/" + userName;
//            
//        } else {
//        	return "redirect:/login?error";
//        }
//    }
//
//	/////////////////////
//	//　マイページ表示
//	/////////////////////
//    //Modelから取得するオブジェクトの属性名は、@ModelAttributeのvalue属性に指定する。
//    @RequestMapping(value = "/mypage/{userName}")
//    public String goMyPage(
//    		@PathVariable("userName") String userName,
//    		AccountForm accountForm,
//    		Model model) {
//
//    	return "mypage";
//    }
//
//    /////////////////////
//	//　ログアウト
//	/////////////////////
//    
//    @RequestMapping("/logout")
//    public String goLogout(
//    		LoginForm loginForm,
//    		PetForm petForm,
//    		SessionStatus sassionStatus) {
//    	
//    	sassionStatus.setComplete();
//    	return "index";
//    }
//
//    
//}
