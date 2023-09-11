package backend.security.dashboard.utils;


import backend.security.dashboard.model.Otp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

public class OtpUtils {

    private static final int NUMBER_DIGITS = 6;
    private static final Random RANDOM = new Random();

    public static String generateOtp() {
        StringBuilder otpBuilder = new StringBuilder();
        for (int i = 0; i < NUMBER_DIGITS; i++) {
            int digit = RANDOM.nextInt(10);
            otpBuilder.append(digit);
        }
        return otpBuilder.toString();
    }

    public static boolean matches(Otp otp1, Otp otp2){
        if(otp1.getValue().equals(otp2.getValue())){
            LocalDateTime time1 = otp1.getTime();
            LocalDateTime time2 = otp2.getTime();
            Duration duration = Duration.between(time1,time2);
            long minutes = duration.toMinutes();
            return minutes < 5 ;
        }
        return false;
    }

    public static boolean hasExpired(Otp otp){
        LocalDateTime time = otp.getTime();
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(time,now);
        long minutes = duration.toMinutes();
        return minutes > 5 ;
    }

}