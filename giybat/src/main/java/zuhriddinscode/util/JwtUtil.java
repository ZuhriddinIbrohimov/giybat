package zuhriddinscode.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import zuhriddinscode.dto.JwtDTO;
import zuhriddinscode.enums.ProfileRole;
import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final int tokenLiveTime = 1000 * 3600 * 24; //1-day
    private static final String secretKey = "veryLongSecretmazgillattayevlasharaaxmojonjinnijonsurbetbekkiydirhonuxlatdibekloxovdangasabekochkozjonduxovmashaynikmaydagapchishularnioqiganbolsangizgapyoqaniqsizmazgi";

    public static String encode ( String username,  Integer id, List<ProfileRole> roleList ){
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
        claims.put("id",String.valueOf(id));

        return Jwts
                .builder()
                .claims(claims)
                .subject(username)
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
                .verifyWith(getSignInKey())
//                .setSigningKey(getSignInKey())
                .build()
                .parseSignedClaims(token)
//                .parseClaimsJws( token )
//                .getBody();
                .getPayload();


        String username = claims.getSubject();
        Integer id = Integer.valueOf((String) claims.get("id"));
//        ProfileRoles role = ProfileRoles.valueOf((String) claims.get("role"));
        String strRole = (String) claims.get("roles");

//        String[] roleArray = strRole.split(",");
        //  ROLE_USER, ROLE_ADMIN
//        List<ProfileRoles> rolesList = new ArrayList<>();
//        for( String role : roleArray ){
//            rolesList.add(ProfileRoles.valueOf(role));
//        }
        //
        List<ProfileRole> roleList2 = Arrays.stream(strRole.split(","))
                .map(ProfileRole::valueOf)
                .toList();
        return new JwtDTO(id,roleList2, username);
    }
}