package com.theworkshopvlc.chupapuntes.users.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.Date
import javax.servlet.http.HttpServletRequest

@Component
class TokenHelper(private val userDetailsService: UserDetailsService) {

  companion object {
    val APP_NAME = "chupapuntes"
    val SECRET = "SuperChupapuntes;Secret"
    val HEADER = "Authorization"
    val EXPIRES_IN = 604800L
    val SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512
  }

  fun getUsernameFromToken(token: String) =
    getClaimsFromToken(token, Claims::getSubject)

  fun canTokenBeRefreshed(token: String): Boolean {
    val expirationDate = getClaimsFromToken(token, Claims::getExpiration)
    return expirationDate > generateCurrentDate()
  }

  fun refreshToken(token: String): String {
    val claims = getAllClaimsFromToken(token)
    claims.issuedAt = generateCurrentDate()
    return generateToken(claims)
  }

  fun generateToken(username: String): String =
    Jwts.builder()
      .setIssuer(APP_NAME)
      .setSubject(username)
      .setIssuedAt(generateCurrentDate())
      .setExpiration(generateExpirationDate())
      .signWith(SIGNATURE_ALGORITHM, SECRET)
      .compact()

  fun getToken(request: HttpServletRequest): String? {
    val authHeader = request.getHeader(HEADER)

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      return authHeader.substring(7)
    }

    return null
  }

  private inline fun <T> getClaimsFromToken(token: String, claimsResolver: (Claims) -> T): T {
    val claims: Claims = getAllClaimsFromToken(token)
    return claimsResolver(claims)
  }

  private fun getAllClaimsFromToken(token: String): Claims =
    Jwts.parser()
      .setSigningKey(SECRET)
      .parseClaimsJws(token)
      .body

  private fun generateToken(claims: Map<String, Any>): String =
    Jwts.builder()
      .setClaims(claims)
      .setExpiration(generateExpirationDate())
      .signWith(SIGNATURE_ALGORITHM, SECRET)
      .compact()

  private fun generateCurrentDate() = Date(System.currentTimeMillis())
  private fun generateExpirationDate() = Date(System.currentTimeMillis() + EXPIRES_IN * 1000)
}
