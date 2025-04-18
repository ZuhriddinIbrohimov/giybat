package zuhriddinscode.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import zuhriddinscode.dto.JwtDTO;
import zuhriddinscode.enums.ProfileRoles;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final int tokenLiveTime = 1000 * 3600 * 24; //1-day
    private static final String secretKey = "veryLongSecretmazgillattayevlasharaaxmojonjinnijonsurbetbekkiydirhonuxlatdibekloxovdangasabekochkozjonduxovmashaynikmaydagapchishularnioqiganbolsangizgapyoqaniqsizmazgi";

    public static String encode ( Integer id, List<ProfileRoles> roleList ){
        String strRoles = roleList.
                stream().
                map(item -> item.name())
                .collect(Collectors.joining(","));

//        List<String> stringList = new LinkedList<>();
//        for (ProfileRoles roles : roleList){
//            stringList.add(roles.name());
//        }
//        String roleString = String.join(",", stringList);

        Map<String, String>  claims = new HashMap<>();
        claims.put("roles", strRoles);

        return Jwts
                .builder()
                .claims(claims)
                .subject(String.valueOf(id))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (tokenLiveTime) ))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private static SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static Integer decodeRegVerToken(String token){
        Claims claims = Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Integer.valueOf(claims.getSubject());
    }
    public static JwtDTO decode (String token){
        Claims claims = Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws( token )
                .getBody();
        Integer id = Integer.valueOf(claims.getSubject());
//        ProfileRoles role = ProfileRoles.valueOf((String) claims.get("role"));
        String strRole = (String) claims.get("role");

//        String[] roleArray = strRole.split(",");
        //  ROLE_USER, ROLE_ADMIN
//        List<ProfileRoles> rolesList = new ArrayList<>();
//        for( String role : roleArray ){
//            rolesList.add(ProfileRoles.valueOf(role));
//        }
        //
        List<ProfileRoles> roleList2= Arrays.stream(strRole.split(","))
                .map(ProfileRoles::valueOf)
                .toList();
        return new JwtDTO(id,roleList2);
    }
}