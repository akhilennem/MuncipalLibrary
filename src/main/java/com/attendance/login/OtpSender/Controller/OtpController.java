package com.attendance.login.OtpSender.Controller;

import com.attendance.login.OtpSender.OtpGenerator.OtpGenarator;
import com.attendance.login.OtpSender.OtpRepository.OtpRepository;
import com.attendance.login.OtpSender.OtpVerifier.OtpVerifier;
import com.attendance.login.OtpSender.Otpmodel.Mail;
import com.attendance.login.OtpSender.service.MailService;
import com.attendance.login.UserPackage.models.User;
import com.attendance.login.UserPackage.repository.DeliveryPerRepo;
import com.attendance.login.UserPackage.repository.RoleRepository;
import com.attendance.login.UserPackage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/mail")
@CrossOrigin
public class OtpController {

    @Autowired
    public OtpGenarator genarator;
    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public User user1;
    @Autowired
    public OtpRepository otpRepository;
    @Autowired
    private MailService mailService;
    String i;
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public DeliveryPerRepo deliveryPerRepo;

@Autowired
public UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder encoder;


@GetMapping("/admin-otp")
    public ResponseEntity<?> webotp(@RequestParam String email) throws Exception

    {
        if
        (userRepository.existsByEmail(email)) {

            String verify = genarator.generateRandom(4);
            System.out.println(verify);
            i = String.valueOf(Integer.parseInt(verify));

            OtpVerifier otpVerifier=new OtpVerifier();
//            String cpy="email";
            otpVerifier.setPhoneoremail(email);
            otpVerifier.setOtp(i);
            otpRepository.save(otpVerifier);

            Mail mail = new Mail();
            mail.setMailFrom("akhilennem@gmail.com");
            mail.setMailTo(email);
            mail.setMailSubject("Muncipal Library Password Reset OTP");
            mail.setMailContent("\uD835\uDC80\uD835\uDC90\uD835\uDC96\uD835\uDC93 \uD835\uDC76\uD835\uDC8F\uD835\uDC86 \uD835\uDC7B\uD835\uDC8A\uD835\uDC8E\uD835\uDC86 \uD835\uDC77\uD835\uDC82\uD835\uDC94\uD835\uDC94\uD835\uDC98\uD835\uDC90\uD835\uDC93\uD835\uDC85 (\uD835\uDC76\uD835\uDC7B\uD835\uDC77) \uD835\uDC70\uD835\uDC94 : "+i);
//            mail.setMailContent(String.valueOf(i));
            mailService.sendEmail(mail);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    
    

//    @GetMapping("/forget-password")
//    public ResponseEntity<?> passwordReset(@RequestParam String otp, String phoneoremail) {
//        if (otpRepository.existsByOtpAndPhoneoremail(otp, phoneoremail)) {
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//
//    }

    @GetMapping("/verify-otp")
    public ResponseEntity<?> userLogin(@RequestParam String otp, String phoneoremail) {
        if (otpRepository.existsByOtpAndPhoneoremail(otp, phoneoremail)) {
otpRepository.deleteByPhoneoremail(phoneoremail);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }


        @GetMapping("/reset-password")
    public String updatePassword(@RequestParam String email, String newpassword) {

   User user1;
   user1=userRepository.getByEmail(email);

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(newpassword);
        user1.setPassword(password);
userRepository.save(user1);

return "password updated successfully";
    }

    @PostMapping("/delete-otp")
    public String Delete(@RequestParam String phoneoremail) {
    otpRepository.deleteByPhoneoremail(phoneoremail);
    return "deleted";
    }



    @GetMapping("otp")
    public List<Object> otp(String phoneoremail){

        String verify = genarator.generateRandom(4);
        System.out.println(verify);
        i = (verify);

        OtpVerifier otpVerifier=new OtpVerifier();
        System.out.printf("OTP.......\n");
        System.out.printf(i+"\n");
        System.out.printf("phone.......\n");
        System.out.println(phoneoremail);
        otpVerifier.setOtp(i);
        otpVerifier.setPhoneoremail(phoneoremail);
        otpRepository.save(otpVerifier);
   String tmp="This is your Otp for muncipal library password reset";
        String url="https://2factor.in/API/V1/81e282e0-464b-11ed-9c12-0200cd936042/SMS/"+phoneoremail+"/"+i+"/"+tmp;
        Object obj=restTemplate.getForObject(url,Object.class);
        return Arrays.asList(obj);

    }

//    @GetMapping("image")
//    public Byte[] image(){
//String uri= String.valueOf(UriComponentsBuilder.newInstance().scheme("https").host("https://w7.pngwing.com/pngs/").port(895).pathSegment());
//
//
//        String url="https://w7.pngwing.com/pngs/895/199/png-transparent-spider-man-heroes-download-with-transparent-background-free-thumbnail.png";
//        System.out.println("/////////////////////");
//        Byte[] bytes=restTemplate.getForObject(url,Byte[].class);
//        System.out.println("images/////////////////////");
//        System.out.println(bytes);
//        return bytes;
//
//    }



        }


