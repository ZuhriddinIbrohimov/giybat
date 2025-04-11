package zuhriddinscode.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.*;

public class JwtUtil {

//    private static final int tokenLiveTime = 1000 * 3600 * 24; //1-day
    private static final int tokenLiveTime = 60*60*1000;
    private static final String secretKey = "veryLongSecretmazgillattayevlasharaaxmojonjinnijonsurbetbekkiydirhonuxlatdibekloxovdangasabekochkozjonduxovmashaynikmaydagapchishularnioqiganbolsangizgapyoqaniqsizmazgi";

//    public static String encode(Integer id) { // bir nechta role bo'lsa
//        String strRoles = roleList.stream().map(item -> item.name())
//                .collect(Collectors.joining(","));


        //  kengaytirilgan kodi yuqoeridagini (23-27)
//        List<String> stringList = new LinkedList<>();
//        for (ProfileRole role: roleList){
//            stringList.add(role.name());
//        }
//        String roleString = String.join(",", stringList);

//        Map<String, Object> extraClaims = new HashMap<>();
//        extraClaims.put("roles", strRoles);
//        extraClaims.put("id", String.valueOf(id));

    public static String encode ( Integer id ){
        return Jwts
                .builder()
//                .claims(extraClaims)
                .subject(String.valueOf(id))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (60*60*1000) ))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private static SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


//    public static JwtDTO decode(String token) {
//        Claims claims = Jwts
//                .parser()
//                .verifyWith(getSignInKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//
//        Integer id = Integer.valueOf((String) claims.get("id"));
//        String username = claims.getSubject();
//        String strRole = (String) claims.get("roles");
//        //Role_User, Role_Admin
////        String [] roleArray = strRole.split(",");
////        List<ProfileRole> roleList = new ArrayList<>();
////        for (String role : roleArray ){
////            roleList.add(ProfileRole.valueOf(role));
////        }
//
//        //yuqoridagini boshqacha varianti
//        List<ProfileRole> roleList = Arrays.stream(strRole.split(","))
//                .map(item -> ProfileRole.valueOf(item))
//                .collect(Collectors.toList());

    /// /        ProfileRole role = ProfileRole.valueOf((String) claims.get("role"));
    /// /        String strRoleList = (String) claims.get("role");
//
//        return new JwtDTO ( id, roleList, username);
//    }

}